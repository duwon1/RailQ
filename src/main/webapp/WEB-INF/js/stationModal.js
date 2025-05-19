document.addEventListener("DOMContentLoaded", function () {
	// 주요역 지역별 버튼
    const tabButtons = document.querySelectorAll(".tab-bar .tab-btn");

    tabButtons.forEach(function (tab) {
      tab.addEventListener("click", function () {
        // 모든 tab-btn에서 클래스 's' 제거
        tabButtons.forEach(function (el) {
          el.classList.remove("s");
        });
        // 클릭한 li에 클래스 's' 추가
        this.classList.add("s");
        });
    });
	
	// 각 각 지역 역선택하면 나오는 버튼
	const contentItems = document.querySelectorAll(".content-a");

    contentItems.forEach(function (item) {
        item.addEventListener("click", function () {
            // 모든 요소의 ID 제거
            contentItems.forEach(el => el.removeAttribute("id"));

            // 클릭한 요소에 ID 추가
            this.id = "content-a-id";
        });
    });
});

// 모달창 닫기
function stationModalClose() {
    const modal = document.querySelector(".station-modal-zindex");
	modal.style.display = "none";
}