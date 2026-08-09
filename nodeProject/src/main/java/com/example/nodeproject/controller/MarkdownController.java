package com.example.nodeproject.controller;

import com.example.nodeproject.service.MarkdownService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class MarkdownController {

    private static final String DEFAULT_MARKDOWN = """
            # 今天的学习日志

            ## 重点

            - 使用 Thymeleaf 渲染页面
            - 在编辑框中测试 Markdown
            - 右侧实时查看预览效果

            > 记录越清楚，复盘越轻松。

            ```java
            System.out.println("Hello Markdown");
            ```

            | 项目 | 状态 |
            | --- | --- |
            | 页面 | 已完成 |
            | 数据库 | 暂不接入 |
            """;

    private final MarkdownService markdownService;

    public MarkdownController(MarkdownService markdownService) {
        this.markdownService = markdownService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("markdown", DEFAULT_MARKDOWN);
        model.addAttribute("previewHtml", markdownService.render(DEFAULT_MARKDOWN));
        return "index";
    }

    @PostMapping("/api/markdown/preview")
    @ResponseBody
    public Map<String, String> preview(@RequestBody MarkdownPreviewRequest request) {
        return Map.of("html", markdownService.render(request == null ? "" : request.markdown()));
    }

    public record MarkdownPreviewRequest(String markdown) {
    }
}
