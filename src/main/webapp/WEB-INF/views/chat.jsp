<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../css/chat.css">
</head>
<body>
    <div class="chat-container">
        <div class="chat-header">
            <div class="chat_top">
                 <h2>실시간상담</h2>
                 <span class="close"></span>
            </div> 
        </div>
        <div class="chat-main-box">
            <div class="box-txt">
                <span class="box-img"></span>
                <div class="text-hi">
                    <h4><p>안녕하세요? "레일큐"입니다</p></h4>
                    <p>궁금한 사항을 질문해주세요</p>
                </div>
            </div>
            <div class="chat-main">
                <div class="chat-txt user">
                    <div class="main-txt">안녕</div>
                    <div class="clock-txt">오후07:51</div>
                </div>
                <div class="chat-txt admin">
                    <div class="admin-img"></div>
                    <div class="main-txt">내용</div>
                    <div class="clock-txt">오후07:51</div>
                </div>
                <div class="chat-txt user">
                    <div class="main-txt">안녕</div>
                    <div class="clock-txt">오후07:51</div>
                </div>
                <div class="chat-txt user">
                    <div class="main-txt">안녕</div>
                    <div class="clock-txt">오후07:51</div>
                </div>
                <div class="chat-txt admin">
                    <div class="admin-img"></div>
                    <div class="main-txt">내용</div>
                    <div class="clock-txt">오후07:51</div>
                </div>
                <div class="chat-txt admin">
                    <div class="admin-img"></div>
                    <div class="main-txt">내용</div>
                    <div class="clock-txt">오후07:51</div>
                </div>
                <div class="chat-txt admin">
                    <div class="admin-img"></div>
                    <div class="main-txt">내용</div>
                    <div class="clock-txt">오후07:51</div>
                </div>
                <div class="chat-txt admin">
                    <div class="admin-img"></div>
                    <div class="main-txt">내용</div>
                    <div class="clock-txt">오후07:51</div>
                </div>
            </div>
        </div>
        <div class="chat-box">
        	<form action="/member/insertmessage" method="post">
	            <input type="text" name="msg" placeholder="여기에 입력해 주세요.">
	            <button type="submit" class="submit-btn">
	                <span class="submit-img"></span>
	            </button>
	        </form>
        </div>
    </div>
</body>
</html>