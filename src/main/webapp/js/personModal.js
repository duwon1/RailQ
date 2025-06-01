// 모달창 닫기
function personModalClose() {
    const modal = document.querySelector(".person-modal-zindex");
	modal.style.display = "none";
}

// 버튼누르면 숫자제어
document.addEventListener("DOMContentLoaded", function () {
    const personItems = document.querySelectorAll(".person-content-a");

    function getTotalCount() {
        let total = 0;
        personItems.forEach(function (item) {
            const count = parseInt(item.querySelector("span").textContent, 10);
            total += count;
        });
        return total;
    }

    personItems.forEach(function (item) {
        const upBtn = item.querySelector(".up-btn");
        const downBtn = item.querySelector(".down-btn");
        const span = item.querySelector("span");
        const input = item.querySelector("input[type='hidden']");

        upBtn.addEventListener("click", function () {
            const current = parseInt(span.textContent, 10);
            const total = getTotalCount();

            if (total >= 10) {
                alert("최대 10명까지 선택 가능합니다");
                return;
            }

            const updated = current + 1;
            span.textContent = updated;
            input.value = updated;
        });

        downBtn.addEventListener("click", function () {
            const current = parseInt(span.textContent, 10);
            if (current > 0) {
                const updated = current - 1;
                span.textContent = updated;
                input.value = updated;
            }
        });
    });
});
