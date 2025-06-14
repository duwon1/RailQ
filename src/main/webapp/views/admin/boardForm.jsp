<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="header.jsp" />
<link href="https://cdn.jsdelivr.net/npm/quill@2.0.3/dist/quill.snow.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/highlight.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/quill@2.0.3/dist/quill.js"></script>
<link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/styles/atom-one-dark.min.css"
/>
<script src="https://cdn.jsdelivr.net/npm/katex@0.16.9/dist/katex.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/katex@0.16.9/dist/katex.min.css" />

<link rel="stylesheet" href="/css/admin/boardForm.css">
<form action="/admin/setboard" method="POST" enctype="multipart/form-data" onsubmit="return prepareSubmit();">
<div class="content-area">
    <div class="main-container">
    	<div class="title">
    		<input type="text" name="title" placeholder="제목을 입력하세요.">
    	</div>
    	<input type="file" name="file" id="file">
        <div id="toolbar-container">
            <span class="ql-formats">
                <select class="ql-font"></select>
                <select class="ql-size"></select>
            </span>
            <span class="ql-formats">
                <button class="ql-bold"></button>
                <button class="ql-italic"></button>
                <button class="ql-underline"></button>
                <button class="ql-strike"></button>
            </span>
            <span class="ql-formats">
                <select class="ql-color"></select>
                <select class="ql-background"></select>
            </span>
            <span class="ql-formats">
                <button class="ql-script" value="sub"></button>
                <button class="ql-script" value="super"></button>
            </span>
            <span class="ql-formats">
                <button class="ql-header" value="1"></button>
                <button class="ql-header" value="2"></button>
                <button class="ql-blockquote"></button>
                <button class="ql-code-block"></button>
            </span>
            <span class="ql-formats">
                <button class="ql-list" value="ordered"></button>
                <button class="ql-list" value="bullet"></button>
                <button class="ql-indent" value="-1"></button>
                <button class="ql-indent" value="+1"></button>
            </span>
            <span class="ql-formats">
                <button class="ql-direction" value="rtl"></button>
                <select class="ql-align"></select>
            </span>
            <span class="ql-formats">
                <button class="ql-link"></button>
                <button class="ql-image"></button>
                <button class="ql-video"></button>
                <button class="ql-formula"></button>
            </span>
            <span class="ql-formats">
                <button class="ql-clean"></button>
            </span>
        </div>
        <div id="editor">
        	<textarea name="content" id="hidden-content" style="display:none;"></textarea>
        </div>
        <div style="width: 100%;">
        	 <button type="submit" class="submit-btn">글쓰기</button>
        	 <button class="close-btn">취소</button>
        </div>
       
    </div>
</div>
</form>
<!-- Initialize Quill editor -->
<script>
    const quill = new Quill('#editor', {
        modules: {
            syntax: true,
            toolbar: '#toolbar-container',
        },
        placeholder: 'Compose an epic...',
        theme: 'snow',
    });

    function prepareSubmit() {
        const title = document.querySelector("input[name='title']").value.trim();
        const html = quill.root.innerHTML.trim();
        const plainText = quill.getText().trim(); // 내용이 완전 공백인지 검사하기 위해 사용

        if (!title) {
            alert("제목을 입력해주세요.");
            return false;
        }

        if (!plainText) {
            alert("내용을 입력해주세요.");
            return false;
        }

        document.getElementById('hidden-content').value = html;
        return true; // ✅ 유효성 검사를 통과하면 폼 제출
    }
</script>

</body>
</html>
