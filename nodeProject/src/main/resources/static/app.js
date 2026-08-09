(function () {
    "use strict";

    var editor = document.getElementById("markdownInput");
    var preview = document.getElementById("markdownPreview");
    var status = document.getElementById("previewStatus");
    var stats = document.getElementById("editorStats");
    var copyButton = document.getElementById("copyMarkdown");
    var clearButton = document.getElementById("clearMarkdown");
    var timer = null;
    var activeRequest = null;

    if (!editor || !preview) {
        return;
    }

    function updateStats() {
        var value = editor.value;
        var lines = value.length === 0 ? 0 : value.split(/\r\n|\r|\n/).length;
        stats.textContent = lines + " 行 · " + value.length + " 字";
    }

    function setStatus(text, className) {
        status.textContent = text;
        status.className = className || "";
    }

    function schedulePreview() {
        updateStats();
        window.clearTimeout(timer);
        timer = window.setTimeout(renderPreview, 260);
    }

    function renderPreview() {
        if (activeRequest) {
            activeRequest.abort();
        }

        activeRequest = new AbortController();
        setStatus("正在预览", "is-busy");

        fetch("/api/markdown/preview", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                markdown: editor.value
            }),
            signal: activeRequest.signal
        })
            .then(function (response) {
                if (!response.ok) {
                    throw new Error("Preview failed");
                }
                return response.json();
            })
            .then(function (data) {
                preview.innerHTML = data.html || "";
                setStatus("已更新", "is-ok");
            })
            .catch(function (error) {
                if (error.name === "AbortError") {
                    return;
                }
                setStatus("预览失败", "is-error");
            });
    }

    function copyText(text, button) {
        var originalText = button.textContent;
        var done = function () {
            button.textContent = "已复制";
            window.setTimeout(function () {
                button.textContent = originalText;
            }, 1300);
        };

        if (navigator.clipboard) {
            navigator.clipboard.writeText(text).then(done).catch(function () {
                copyWithFallback(text, done);
            });
            return;
        }

        copyWithFallback(text, done);
    }

    function copyWithFallback(text, done) {
        var helper = document.createElement("textarea");
        helper.value = text;
        helper.style.position = "fixed";
        helper.style.opacity = "0";
        document.body.appendChild(helper);
        helper.focus();
        helper.select();
        document.execCommand("copy");
        document.body.removeChild(helper);
        done();
    }

    editor.addEventListener("input", schedulePreview);

    copyButton.addEventListener("click", function () {
        copyText(editor.value, copyButton);
    });

    clearButton.addEventListener("click", function () {
        editor.value = "";
        editor.focus();
        schedulePreview();
    });

    document.querySelectorAll("[data-sample-source]").forEach(function (button) {
        button.addEventListener("click", function () {
            var sample = document.getElementById(button.getAttribute("data-sample-source"));
            if (!sample) {
                return;
            }
            editor.value = sample.textContent.trim();
            editor.focus();
            schedulePreview();
        });
    });

    updateStats();
}());
