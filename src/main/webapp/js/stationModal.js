document.addEventListener("DOMContentLoaded", function () {
    // 주요역 / 지역별 탭 버튼
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

    // 지역 클릭 시 선택 표시 (ID 토글)
    const contentItems = document.querySelectorAll(".content-a");

    contentItems.forEach(function (item) {
        item.addEventListener("click", function () {
            // 모든 요소의 ID 제거
            contentItems.forEach(el => el.removeAttribute("id"));
            // 클릭한 요소에 ID 추가
            this.id = "content-a-id";
        });
    });

    // AJAX로 역명 요청
    const cityLinks = document.querySelectorAll('a.city-link');

    cityLinks.forEach(link => {
        link.addEventListener("click", function (e) {
            e.preventDefault();

            const citycode = this.dataset.citycode;
            console.log("📦 선택된 citycode:", citycode);

            fetch(`/reservation/region?citycode=${encodeURIComponent(citycode)}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error("서버 응답 오류!");
                    }
                    return response.json();
                })
                .then(data => {
                    const regionListEl = document.getElementById("region-list");
                    regionListEl.innerHTML = ""; // 기존 리스트 초기화

                    if (data.length === 0) {
                        regionListEl.innerHTML = "<li>역 정보 없음</li>";
                        return;
                    }

                    // 역 리스트 출력
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
						
						// 역 선택 시 모달 닫고 텍스트 적용 (동적 바인딩)
						bindRegionClickEvents(region.nodeid, region.nodename);
                    });

                    
                })
                .catch(error => {
                    console.error("❌ 오류 발생:", error);
                });
		});
    });

    // region-link 클릭 시 모달 닫고 텍스트 업데이트
	function bindRegionClickEvents(nodeid, nodename) {
	    const regionLinks = document.querySelectorAll('a.region-link');
		
	    regionLinks.forEach(link => {
			link.addEventListener("click", function (e) {
	            e.preventDefault();
	
	            // 현재 열린 모달의 id값 확인 (1 또는 2)
	            const modal = document.querySelector(".station-modal-zindex");
	            const modalId = modal.id; // "1" 또는 "2"일 것으로 기대
	
	            // 역명을 업데이트할 대상 요소 결정
	            let targetElement = null;
	            if (modalId === "1") {
	                targetElement = document.getElementById("start-station");
	            } else if (modalId === "2") {
	                targetElement = document.getElementById("last-station");
	            }
	
	            // 텍스트 업데이트
	            if (targetElement) {
	                targetElement.innerText = nodename;
					targetElement.name = nodeid;
	            }
	            // 모달 닫기
	            stationModalClose();
	        });
	    });
	}
		
});

// 모달창 닫기 함수
function stationModalClose() {
    const modal = document.querySelector(".station-modal-zindex");
    modal.style.display = "none";
	modal.removeAttribute("id");
}
