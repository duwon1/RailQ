 document.addEventListener("DOMContentLoaded", function () {
    const input = document.getElementById("coupon");
    
    input.addEventListener("input", function () {
		this.value = this.value.replace(/[^0-9]/g, "");
    });
});

// 모달창 닫기
function discountModalClose() {
    const modal = document.querySelector(".station-modal-zindex");
		modal.style.display = "none";
}