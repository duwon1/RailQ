function reserv_btn_click() {
    const bar = document.querySelector('.bottom-bar-container');
    bar.classList.toggle('down');
}

function stationModalOpen(type) {
		const modal = document.querySelector('.station-modal-zindex');
		modal.style.display = "block";
		// 기존에 있는 id 제거 (start/last 구분용)
	  modal.removeAttribute("id");
		// id부여
		modal.id = type;
		resetCity()
}

function personModalOpen() {
		const modal = document.querySelector('.person-modal-zindex');
		modal.style.display = "block";
}

// 커스텀 select 박스 열고닫기
document.addEventListener("DOMContentLoaded", function () {
    const selects = document.querySelectorAll(".select");

    selects.forEach(select => {
        const header = select.querySelector("h3 a");
        const option = select.querySelector(".option");

        header.addEventListener("click", function (e) {
            e.preventDefault();

            // 모든 option 숨기고 강조 제거
            document.querySelectorAll(".option").forEach(opt => {
                if (opt !== option) {
                    opt.style.display = "none";
                }
            });
            document.querySelectorAll(".select").forEach(s => {
                if (s !== select) {
                    s.classList.remove("active");
                }
            });

            // 현재 클릭한 항목 토글
            const isOpen = option.style.display === "block";
            option.style.display = isOpen ? "none" : "block";
            select.classList.toggle("active", !isOpen); // 강조 보더 ON/OFF
        });
    });
});

// ✅ 디폴트 citycode=11 (서울특별시) 자동 클릭
function resetCity() {
		const defaultCity = document.querySelector('a.city-link[data-citycode="11"]');
    if (defaultCity) {
        defaultCity.click();
    }
}