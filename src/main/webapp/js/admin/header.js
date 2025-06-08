document.addEventListener("DOMContentLoaded", function () {
    const params = new URLSearchParams(window.location.search);
    const headerNum = params.get("header");

    if (headerNum) {
        const menuItem = document.querySelector(`[data-num="${headerNum}"]`);
        if (menuItem) {
            menuItem.classList.add("button-select");
        }
    }
});


function header_click(num) {
    if (num === 1) {
        window.location.href = `/admin/member?header=${num}`;
    } else if (num === 2) {
        window.location.href = `/admin/chat?header=${num}`;
    } else if (num === 3) {
        window.location.href = `/cart.jsp?header=${num}`;
    } else if (num === 4) {
        window.location.href = `/mypage.jsp?header=${num}`;
    } else {
        console.error("잘못된 num 값:", num);
    }
}
