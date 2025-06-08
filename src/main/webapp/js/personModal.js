// 모달창 닫기
function personModalClose() {
    const modal = document.querySelector(".person-modal-zindex");
    modal.style.display = "none";
}

// 버튼 누르면 숫자 제어
document.addEventListener("DOMContentLoaded", function () {
    const personItems = document.querySelectorAll(".person-content-a");
    const personSubmit = document.getElementById("person_submit"); // person_submit 버튼 요소
    const totalPersonId = document.getElementById("total_person"); // 총 인원 표시하는 요소
    const personTotal = document.getElementById("person_total"); // hidden input

    // 총 인원 계산
    function getTotalCount() {
        let total = 0;
        personItems.forEach(function (item) {
            const count = parseInt(item.querySelector("span").textContent, 10);
            total += count;
        });
        return total;
    }

    // 버튼 상태 갱신 함수
    function updateSubmitButtonState() {
        const total = getTotalCount();
        if (total === 0) {
            personSubmit.classList.add("disable");
        } else {
            personSubmit.classList.remove("disable");
        }
    }

    // 숫자 변경 및 상태 갱신
    personItems.forEach(function (item) {
        const upBtn = item.querySelector(".up-btn");
        const downBtn = item.querySelector(".down-btn");
        const span = item.querySelector("span");
        const input = item.querySelector("input[type='hidden']");

        function handleUpButton() {
            const current = parseInt(span.textContent, 10);
            const total = getTotalCount();

            if (total >= 10) {
                alert("최대 10명까지 선택 가능합니다.");
                return;
            }

            const updated = current + 1;
            span.textContent = updated;
            input.value = updated;
            updateSubmitButtonState();
        }

        function handleDownButton() {
            const current = parseInt(span.textContent, 10);
            if (current > 0) {
                const updated = current - 1;
                span.textContent = updated;
                input.value = updated;
                updateSubmitButtonState();
            }
        }

        upBtn.addEventListener("click", handleUpButton);
        downBtn.addEventListener("click", handleDownButton);
    });

    // 적용 버튼 클릭 시 모달 닫고 총 인원 표시
    personSubmit.addEventListener("click", function () {
        const total = getTotalCount();
        totalPersonId.innerText = `총${total}명`;
        personTotal.value = total;
        personModalClose();
    });

    // 초기 버튼 상태 설정
    updateSubmitButtonState();
});
