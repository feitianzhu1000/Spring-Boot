(function () {
    "use strict";

    var links = Array.prototype.slice.call(document.querySelectorAll(".toc-link"));
    var sections = Array.prototype.slice.call(document.querySelectorAll("section[id], article[id]"));

    function setActiveLink(id) {
        links.forEach(function (link) {
            link.classList.toggle("is-active", link.getAttribute("href") === "#" + id);
        });
    }

    if ("IntersectionObserver" in window) {
        var observer = new IntersectionObserver(function (entries) {
            var visible = entries
                .filter(function (entry) {
                    return entry.isIntersecting;
                })
                .sort(function (first, second) {
                    return second.intersectionRatio - first.intersectionRatio;
                })[0];

            if (visible) {
                setActiveLink(visible.target.id);
            }
        }, {
            rootMargin: "-18% 0px -65% 0px",
            threshold: [0, 0.25, 0.5, 1]
        });

        sections.forEach(function (section) {
            observer.observe(section);
        });
    }

/**
 * 复制文本到剪贴板的函数
 * @param {string} text - 要复制的文本内容
 * @param {HTMLElement} button - 触发复制操作的按钮元素
 */
    function copyText(text, button) {
    // 保存按钮原始文本
        var originalText = button.textContent;
    // 定义复制完成后的回调函数
        var complete = function () {
        // 设置按钮文本为"已复制"
            button.textContent = "已复制";
        // 1.4秒后恢复按钮原始文本
            window.setTimeout(function () {
                button.textContent = originalText;
            }, 1400);
        };

    // 检查是否支持现代 Clipboard API
        if (navigator.clipboard) {
            navigator.clipboard.writeText(text).then(complete).catch(function () {
                copyWithFallback(text, complete);
            });
            return;
        }

        copyWithFallback(text, complete);
    }

/**
 * 复制文本到剪贴板，使用传统方法作为后备方案
 * @param {string} text - 需要复制的文本内容
 * @param {function} complete - 复制操作完成后的回调函数
 */
    function copyWithFallback(text, complete) {
    // 创建一个隐藏的textarea元素用于复制文本
        var helper = document.createElement("textarea");
    // 设置textarea的值为要复制的文本
        helper.value = text;
    // 将textarea设置为固定定位，使其不占用页面空间
        helper.style.position = "fixed";
    // 将textarea设置为完全透明，使其在视觉上不可见
        helper.style.opacity = "0";
    // 将textarea添加到文档的body中
        document.body.appendChild(helper);
    // 聚焦到textarea元素
        helper.focus();
    // 选中textarea中的所有文本
        helper.select();
    // 执行复制命令将选中的文本复制到剪贴板
        document.execCommand("copy");
    // 从文档中移除textarea元素
        document.body.removeChild(helper);
    // 调用完成回调函数
        complete();
    }

    document.querySelectorAll("[data-copy-target]").forEach(function (button) {
        button.addEventListener("click", function () {
            var target = document.getElementById(button.getAttribute("data-copy-target"));
            if (target) {
                copyText(target.textContent, button);
            }
        });
    });
}());
