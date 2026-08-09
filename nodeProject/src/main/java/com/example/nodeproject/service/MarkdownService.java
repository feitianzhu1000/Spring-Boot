package com.example.nodeproject.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MarkdownService {

    private static final Pattern HEADING_PATTERN = Pattern.compile("^(#{1,6})\\s+(.+)$");
    private static final Pattern UNORDERED_ITEM_PATTERN = Pattern.compile("^[-*+]\\s+(.+)$");
    private static final Pattern ORDERED_ITEM_PATTERN = Pattern.compile("^\\d+[.)]\\s+(.+)$");
    private static final Pattern TASK_ITEM_PATTERN = Pattern.compile("^[-*+]\\s+\\[([ xX])]\\s+(.+)$");
    private static final Pattern LINK_PATTERN = Pattern.compile("!?\\[([^]]*)]\\(([^)\\s]+)(?:\\s+\"[^\"]*\")?\\)");
    private static final Pattern TABLE_SEPARATOR_PATTERN = Pattern.compile("^\\s*\\|?\\s*:?-{3,}:?\\s*(\\|\\s*:?-{3,}:?\\s*)+\\|?\\s*$");

    public String render(String markdown) {
        if (markdown == null || markdown.isBlank()) {
            return "<p class=\"empty-preview\">输入 Markdown 后，这里会显示预览。</p>";
        }

        List<String> lines = markdown.replace("\r\n", "\n").replace('\r', '\n').lines().toList();
        StringBuilder html = new StringBuilder();
        StringBuilder paragraph = new StringBuilder();
        List<String> listItems = new ArrayList<>();
        List<String> orderedItems = new ArrayList<>();
        List<String> quoteLines = new ArrayList<>();
        boolean inCodeBlock = false;
        String codeLanguage = "";
        StringBuilder code = new StringBuilder();

        for (int index = 0; index < lines.size(); index++) {
            String line = lines.get(index);
            String trimmed = line.trim();

            if (trimmed.startsWith("```")) {
                if (inCodeBlock) {
                    html.append(renderCodeBlock(code.toString(), codeLanguage));
                    code.setLength(0);
                    codeLanguage = "";
                    inCodeBlock = false;
                } else {
                    flushParagraph(html, paragraph);
                    flushUnorderedList(html, listItems);
                    flushOrderedList(html, orderedItems);
                    flushQuote(html, quoteLines);
                    codeLanguage = trimmed.length() > 3 ? trimmed.substring(3).trim() : "";
                    inCodeBlock = true;
                }
                continue;
            }

            if (inCodeBlock) {
                code.append(line).append('\n');
                continue;
            }

            if (trimmed.isEmpty()) {
                flushParagraph(html, paragraph);
                flushUnorderedList(html, listItems);
                flushOrderedList(html, orderedItems);
                flushQuote(html, quoteLines);
                continue;
            }

            if (isTableStart(lines, index)) {
                flushParagraph(html, paragraph);
                flushUnorderedList(html, listItems);
                flushOrderedList(html, orderedItems);
                flushQuote(html, quoteLines);
                int nextIndex = appendTable(html, lines, index);
                index = nextIndex - 1;
                continue;
            }

            Matcher headingMatcher = HEADING_PATTERN.matcher(trimmed);
            if (headingMatcher.matches()) {
                flushParagraph(html, paragraph);
                flushUnorderedList(html, listItems);
                flushOrderedList(html, orderedItems);
                flushQuote(html, quoteLines);
                int level = headingMatcher.group(1).length();
                html.append("<h").append(level).append(">")
                        .append(renderInline(headingMatcher.group(2)))
                        .append("</h").append(level).append(">");
                continue;
            }

            if (trimmed.startsWith(">")) {
                flushParagraph(html, paragraph);
                flushUnorderedList(html, listItems);
                flushOrderedList(html, orderedItems);
                quoteLines.add(trimmed.substring(1).trim());
                continue;
            }

            Matcher taskMatcher = TASK_ITEM_PATTERN.matcher(trimmed);
            Matcher unorderedMatcher = UNORDERED_ITEM_PATTERN.matcher(trimmed);
            Matcher orderedMatcher = ORDERED_ITEM_PATTERN.matcher(trimmed);
            if (taskMatcher.matches()) {
                flushParagraph(html, paragraph);
                flushOrderedList(html, orderedItems);
                boolean checked = !taskMatcher.group(1).isBlank();
                listItems.add("<li class=\"task-item" + (checked ? " is-done" : "") + "\">"
                        + "<span class=\"task-box\"></span>"
                        + renderInline(taskMatcher.group(2)) + "</li>");
                continue;
            }
            if (unorderedMatcher.matches()) {
                flushParagraph(html, paragraph);
                flushOrderedList(html, orderedItems);
                listItems.add("<li>" + renderInline(unorderedMatcher.group(1)) + "</li>");
                continue;
            }
            if (orderedMatcher.matches()) {
                flushParagraph(html, paragraph);
                flushUnorderedList(html, listItems);
                orderedItems.add("<li>" + renderInline(orderedMatcher.group(1)) + "</li>");
                continue;
            }

            flushUnorderedList(html, listItems);
            flushOrderedList(html, orderedItems);
            flushQuote(html, quoteLines);
            if (!paragraph.isEmpty()) {
                paragraph.append(' ');
            }
            paragraph.append(trimmed);
        }

        if (inCodeBlock) {
            html.append(renderCodeBlock(code.toString(), codeLanguage));
        }
        flushParagraph(html, paragraph);
        flushUnorderedList(html, listItems);
        flushOrderedList(html, orderedItems);
        flushQuote(html, quoteLines);

        return html.toString();
    }

    private boolean isTableStart(List<String> lines, int index) {
        return index + 1 < lines.size()
                && lines.get(index).contains("|")
                && TABLE_SEPARATOR_PATTERN.matcher(lines.get(index + 1)).matches();
    }

    private int appendTable(StringBuilder html, List<String> lines, int index) {
        List<String> headers = splitTableRow(lines.get(index));
        html.append("<table><thead><tr>");
        for (String header : headers) {
            html.append("<th>").append(renderInline(header)).append("</th>");
        }
        html.append("</tr></thead><tbody>");

        int cursor = index + 2;
        while (cursor < lines.size()) {
            String row = lines.get(cursor);
            if (row.isBlank() || !row.contains("|")) {
                break;
            }

            List<String> cells = splitTableRow(row);
            html.append("<tr>");
            for (int cellIndex = 0; cellIndex < headers.size(); cellIndex++) {
                String cell = cellIndex < cells.size() ? cells.get(cellIndex) : "";
                html.append("<td>").append(renderInline(cell)).append("</td>");
            }
            html.append("</tr>");
            cursor++;
        }

        html.append("</tbody></table>");
        return cursor;
    }

    private List<String> splitTableRow(String row) {
        String normalized = row.trim();
        if (normalized.startsWith("|")) {
            normalized = normalized.substring(1);
        }
        if (normalized.endsWith("|")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        String[] columns = normalized.split("\\|", -1);
        List<String> cells = new ArrayList<>();
        for (String column : columns) {
            cells.add(column.trim());
        }
        return cells;
    }

    private void flushParagraph(StringBuilder html, StringBuilder paragraph) {
        if (!paragraph.isEmpty()) {
            html.append("<p>").append(renderInline(paragraph.toString())).append("</p>");
            paragraph.setLength(0);
        }
    }

    private void flushUnorderedList(StringBuilder html, List<String> items) {
        if (!items.isEmpty()) {
            html.append("<ul>").append(String.join("", items)).append("</ul>");
            items.clear();
        }
    }

    private void flushOrderedList(StringBuilder html, List<String> items) {
        if (!items.isEmpty()) {
            html.append("<ol>").append(String.join("", items)).append("</ol>");
            items.clear();
        }
    }

    private void flushQuote(StringBuilder html, List<String> quoteLines) {
        if (!quoteLines.isEmpty()) {
            html.append("<blockquote>");
            for (String quoteLine : quoteLines) {
                html.append("<p>").append(renderInline(quoteLine)).append("</p>");
            }
            html.append("</blockquote>");
            quoteLines.clear();
        }
    }

    private String renderCodeBlock(String code, String language) {
        String cssClass = language == null || language.isBlank()
                ? ""
                : " class=\"language-" + escapeAttribute(language.toLowerCase(Locale.ROOT)) + "\"";
        return "<pre><code" + cssClass + ">" + escapeHtml(code.stripTrailing()) + "</code></pre>";
    }

    private String renderInline(String text) {
        Matcher matcher = LINK_PATTERN.matcher(text);
        StringBuilder result = new StringBuilder();
        int lastEnd = 0;
        while (matcher.find()) {
            result.append(renderSimpleInline(text.substring(lastEnd, matcher.start())));
            boolean image = matcher.group().startsWith("!");
            String label = matcher.group(1);
            String url = sanitizeUrl(matcher.group(2));
            if (image) {
                result.append("<img src=\"").append(escapeAttribute(url)).append("\" alt=\"")
                        .append(escapeAttribute(label)).append("\">");
            } else {
                result.append("<a href=\"").append(escapeAttribute(url))
                        .append("\" target=\"_blank\" rel=\"noreferrer\">")
                        .append(renderSimpleInline(label)).append("</a>");
            }
            lastEnd = matcher.end();
        }
        result.append(renderSimpleInline(text.substring(lastEnd)));
        return result.toString();
    }

    private String renderSimpleInline(String text) {
        String escaped = escapeHtml(text);
        escaped = escaped.replaceAll("`([^`]+)`", "<code>$1</code>");
        escaped = escaped.replaceAll("\\*\\*([^*]+)\\*\\*", "<strong>$1</strong>");
        escaped = escaped.replaceAll("__([^_]+)__", "<strong>$1</strong>");
        escaped = escaped.replaceAll("(?<!\\*)\\*([^*]+)\\*(?!\\*)", "<em>$1</em>");
        escaped = escaped.replaceAll("(?<!_)_([^_]+)_(?!_)", "<em>$1</em>");
        return escaped;
    }

    private String sanitizeUrl(String url) {
        String trimmed = url.trim();
        String lower = trimmed.toLowerCase(Locale.ROOT);
        if (lower.startsWith("http://")
                || lower.startsWith("https://")
                || lower.startsWith("mailto:")
                || lower.startsWith("tel:")
                || lower.startsWith("/")
                || lower.startsWith("./")
                || lower.startsWith("../")
                || !lower.contains(":")) {
            return trimmed;
        }
        return "#";
    }

    private String escapeHtml(String value) {
        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    private String escapeAttribute(String value) {
        return escapeHtml(value);
    }
}
