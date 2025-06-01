document.addEventListener("DOMContentLoaded", function () {
    const buttons = document.querySelectorAll(".content-button");

    // 🔹 페이지 처음 로드될 때 기본 페이지 불러오기
    fetch("/admin/member")
        .then(response => {
            if (!response.ok) throw new Error("기본 페이지 로딩 실패냥!");
            return response.text();
        })
        .then(html => {
            document.getElementById("main-wrapper").innerHTML = html;

            // ✅ 첫 번째 버튼 선택 효과 주기 (예: 회원관리)
            const defaultButton = document.querySelector('.content-button[data-url="/admin/member"]');
            if (defaultButton) {
                defaultButton.querySelector("button").classList.add("button-selection");
                defaultButton.querySelector(".content-circle").classList.add("selected");
            }
        })
        .catch(error => {
            console.error("기본 페이지 로딩 중 오류:", error);
        });

    // 🔹 나머지 버튼 클릭 이벤트 처리
    buttons.forEach(btn => {
        btn.addEventListener("click", function () {
            const innerBtn = btn.querySelector("button");

            document.querySelectorAll(".content-button button").forEach(b => b.classList.remove("button-selection"));
            document.querySelectorAll(".content-button .content-circle").forEach(c => c.classList.remove("selected"));

            innerBtn.classList.add("button-selection");
            btn.querySelector(".content-circle").classList.add("selected");

            const url = btn.getAttribute("data-url");
            if (!url) return;

            fetch(url)
                .then(response => {
                    if (!response.ok) throw new Error("페이지 로딩 실패");
                    return response.text();
                })
                .then(html => {
                    document.getElementById("main-wrapper").innerHTML = html;
                })
                .catch(error => {
                    console.error("페이지 로딩 오류:", error);
                });
        });
    });
});
