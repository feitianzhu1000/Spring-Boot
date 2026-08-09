package com.example.nodeproject.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MarkdownServiceTest {

    private final MarkdownService markdownService = new MarkdownService();

    @Test
    void rendersBasicMarkdown() {
        String html = markdownService.render("""
                # 标题

                **重点** 和 `代码`

                - 第一项
                """);

        assertThat(html).contains("<h1>标题</h1>");
        assertThat(html).contains("<strong>重点</strong>");
        assertThat(html).contains("<code>代码</code>");
        assertThat(html).contains("<ul><li>第一项</li></ul>");
    }

    @Test
    void escapesUnsafeHtml() {
        String html = markdownService.render("<script>alert(1)</script>");

        assertThat(html).doesNotContain("<script>");
        assertThat(html).contains("&lt;script&gt;");
    }

    @Test
    void blocksUnsafeLinks() {
        String html = markdownService.render("[危险](javascript:alert(1))");

        assertThat(html).contains("href=\"#\"");
    }
}
