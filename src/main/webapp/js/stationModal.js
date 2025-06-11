document.addEventListener("DOMContentLoaded", function () {
    // 탭 버튼
    const tabButtons = document.querySelectorAll(".tab-bar .tab-btn");
    tabButtons.forEach(tab => {
        tab.addEventListener("click", function () {
            tabButtons.forEach(el => el.classList.remove("s"));
            this.classList.add("s");
        });
    });

    // 지역 선택 시 표시
    const contentItems = document.querySelectorAll(".content-a");
    contentItems.forEach(item => {
        item.addEventListener("click", function () {
            contentItems.forEach(el => el.removeAttribute("id"));
            this.id = "content-a-id";
        });
    });

    // 시/도 클릭 시 AJAX 요청
	const cityLinks = document.querySelectorAll('a.city-link');

	cityLinks.forEach(link => {
	    link.addEventListener("click", function (e) {
	        e.preventDefault();
	        const citycode = this.dataset.citycode;

	        fetch(`/reservation/region?citycode=${encodeURIComponent(citycode)}`)
	            .then(response => response.json())
	            .then(data => {
	                const regionListEl = document.getElementById("region-list");
	                regionListEl.innerHTML = "";

	                if (data.length === 0) {
	                    regionListEl.innerHTML = "<li>역 정보 없음</li>";
	                    return;
	                }

	                data.forEach(region => {
	                    const li = document.createElement("li");
	                    li.className = "content-a cursor";

	                    const a = document.createElement("a");
	                    a.href = "#";
	                    a.textContent = region.nodename;
	                    a.classList.add("region-link");
	                    a.dataset.nodeid = region.nodeid;

	                    li.appendChild(a);
	                    regionListEl.appendChild(li);
	                });
	            })
	            .catch(error => {
	                console.error("❌ 지역 요청 오류:", error);
	            });
	    });
	});

	// ✅ region-link 이벤트는 한 번만 위임 방식으로 바인딩
	document.getElementById("region-list").addEventListener("click", function (e) {
	    const link = e.target.closest("a.region-link");
	    if (!link) return;

	    e.preventDefault();

	    const nodeid = link.dataset.nodeid;
	    const nodename = link.textContent;

	    const modal = document.querySelector(".station-modal-zindex");
	    const modalId = modal.id;

	    const target = modalId === "1"
	        ? document.getElementById("start-station")
	        : document.getElementById("last-station");

	    if (target) {
	        target.innerText = nodename;
	        target.dataset.station = nodeid;
	    }

	    stationModalClose();
	    updateReservationForm();
	});


    // 역 클릭 시 모달 닫고 텍스트 적용
	function bindRegionClickEvents() {
	    document.getElementById("region-list").addEventListener("click", function (e) {
	        const link = e.target.closest("a.region-link");
	        if (!link) return;

	        e.preventDefault();

	        const nodeid = link.dataset.nodeid;
	        const nodename = link.textContent;

	        const modal = document.querySelector(".station-modal-zindex");
	        const modalId = modal.id;

	        let target = modalId === "1"
	            ? document.getElementById("start-station")
	            : document.getElementById("last-station");

	        if (target) {
	            target.innerText = nodename;
	            target.dataset.station = nodeid;
	        }

	        stationModalClose();
	        updateReservationForm();
	    });
	}


    // 예약 정보 hidden input 자동 반영
	function updateReservationForm() {
	    const startStationEl = document.getElementById("start-station");
	    const lastStationEl = document.getElementById("last-station");

	    const startId = startStationEl.dataset.station;
	    const startName = startStationEl.innerText;

	    const lastId = lastStationEl.dataset.station;
	    const lastName = lastStationEl.innerText;

	    const date = document.querySelector(".btn-date").dataset.reservationDay; // "20250611"
	    const time = document.querySelector(".btn-date").dataset.reservationTime; // "22"
	    const formattedDate = `${date.slice(0, 4)}-${date.slice(4, 6)}-${date.slice(6, 8)}`;

	    document.getElementById("start_id").value = startId;
	    document.getElementById("last_id").value = lastId;
	    document.getElementById("reservationDate").value = formattedDate;
	    document.getElementById("reservationTime").value = time;
	    document.getElementById("total").value = "1";

	    // 추가 필드 설정
	    document.getElementById("start_name").value = startName;
	    document.getElementById("last_name").value = lastName;

	    // ✅ 전송
	    document.getElementById("searchForm").submit();
	}



    // 외부에서 호출 가능하도록 등록
    window.updateReservationForm = updateReservationForm;
});

// 모달 닫기
function stationModalClose() {
    const modal = document.querySelector(".station-modal-zindex");
    modal.style.display = "none";
    modal.removeAttribute("id");
}
