<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../css/admin/header.css">
    <script src="../../../js/admin/header.js"></script>
</head>
<body>
    <div class="header-container">
        <div><h1 class="header-logo">관리자 페이지</h1></div>
        <div class="line"></div>
        <div class="login">
            <span>현재로그인 : admin</span>
            <button type="button" class="logout">로그아웃</button>
        </div>
        <div class="line"></div>
        <div class="header-main-container">
            <div class="content-button" data-url="/admin/member">
                <button type="button" class="button-selection">
                    <span class="content-circle selected"></span>
                    <span class="content-title">회원 관리</span>
                </button>
            </div>
            <div class="content-button" data-url="/admin/board">
                <button type="button">
                    <span class="content-circle"></span>
                    <span class="content-title">게시물 관리</span>
                </button>
            </div>
            <div class="content-button">
                <button type="button">
                    <span class="content-circle"></span>
                    <span class="content-title">댓글 관리</span>
                </button>
            </div>
            <div class="content-button">
                <button type="button">
                    <span class="content-circle"></span>
                    <span class="content-title">티켓 관리</span>
                </button>
            </div>
             <div class="content-button">
                <button type="button">
                    <span class="content-circle"></span>
                    <span class="content-title">공지사항 관리</span>
                </button>
            </div>
             <div class="content-button">
                <button type="button">
                    <span class="content-circle"></span>
                    <span class="content-title">예약 관리</span>
                </button>
            </div>
        </div>
    </div>
    
    <div id="main-wrapper"></div>
</body>
</html>