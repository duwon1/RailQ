document.addEventListener("DOMContentLoaded", function () {
    const params = new URLSearchParams(window.location.search);
    const headerNum = params.get("header");

	if (headerNum) {
	    // 1. 모든 .content-circle에서 selected 제거
	    document.querySelectorAll('.content-circle.selected').forEach(el => {
	        el.classList.remove('selected');
	    });

	    // 2. 대상 요소 찾기
		// 작은따옴표 안에 큰따옴표 사용
		const menuItem = document.querySelector(`[data-num="${headerNum}"]`);
	    const menuItem2 = menuItem?.querySelector('.content-circle');

	    // 3. 해당 요소에 클래스 부여
	    if (menuItem) {
	        menuItem.classList.add('button-select');
	    }

	    if (menuItem2) {
	        menuItem2.classList.add('selected');
	    }
	}

});


function header_click(num) {
    if (num === 1) {
        window.location.href = `/admin/member?header=${num}`;
    } else if (num === 2) {
        window.location.href = `/admin/chat?header=${num}`;
    } else if (num === 3) {
        window.location.href = `/admin/board?header=${num}`;
    } else if (num === 4) {
        window.location.href = `/mypage.jsp?header=${num}`;
    } else {
        console.error("잘못된 num 값:", num);
    }
	
}
