<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="header.jsp" />

<link rel="stylesheet" href="../../css/chat.css">
   <div class="chat-container">
       <div class="chat-header">
           <div class="chat_top">
               <h2>실시간상담 (관리자)</h2>
               <span class="close"></span>
           </div>
       </div>
       <div class="chat-main-box">
           <div class="box-txt">
               <span class="box-img"></span>
               <div class="text-hi">
                   <h4><p>관리자</p></h4>
                   <p>실시간 상담입니다</p>
               </div>
           </div>
           <div class="chat-main" id="chatArea"></div>
       </div>
       <div class="chat-box">
           <input type="text" id="msgInput" placeholder="답변을 입력해 주세요.">
           <button type="button" class="submit-btn" onclick="sendMessage()">
            <span class="submit-img"></span>
        </button>
    </div>
</div>

<script>
    const mnum = 0; // 관리자 mnum 고정
    const targetMnum = 1; // 현재 응답 중인 유저의 mnum (단일 유저 기준)
    const ws = new WebSocket("ws://localhost:8080/chatSocket/" + mnum);

    ws.onmessage = function (e) {
        console.log("📥 서버에서 받은 원본 메시지:", e.data);
        try {
            const data = JSON.parse(e.data);
            const now = new Date().toLocaleTimeString('ko-KR', { hour: '2-digit', minute: '2-digit', hour12: true });
            const chatArea = document.getElementById("chatArea");
            console.log("📦 data.msg 값:", data.msg);
            const html = data.sender === "m"
                ? '<div class="chat-txt user"><div class="main-txt">' + data.msg + '</div><div class="clock-txt">' + now + '</div></div>'
                : '<div class="chat-txt admin"><div class="admin-img"></div><div class="main-txt">' + data.msg + '</div><div class="clock-txt">' + now + '</div></div>';

            chatArea.innerHTML += html;
            chatArea.scrollTop = chatArea.scrollHeight;
        } catch (err) {
            console.error("❌ 메시지 파싱 실패:", err);
        }
    };

    function sendMessage() {
        const input = document.getElementById("msgInput");
        const message = input.value.trim();
        if (message !== "") {
            const data = {
                sender: "a",
                mnum: targetMnum,
                msg: message
            };
            ws.send(JSON.stringify(data));
            input.value = "";
        }
    }

    document.getElementById("msgInput").addEventListener("keyup", function (e) {
        if (e.key === "Enter") sendMessage();
    });
</script>
</body>
</html>
