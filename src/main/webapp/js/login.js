document.addEventListener("DOMContentLoaded", () => {
    // 폼 요소 참조
    const loginForm = document.getElementById("loginForm");
    
    // 폼 제출 이벤트 리스너 추가
    loginForm.addEventListener("submit", (event) => {
        event.preventDefault(); // 폼 기본 제출 동작 방지
        login();
    });
});

function login() {
    const user = document.getElementById("username").value;
    const pass = document.getElementById("password").value;
    if (user === "1234" && pass === "1234") {
        alert("로그인 성공!");
    } else {
        alert("잘못된 정보입니다. 다시 시도해 주십시오.");
    }
}