document.addEventListener("DOMContentLoaded", () => {
    const btnFindId = document.getElementById("btn-find-id");
    const btnFindPw = document.getElementById("btn-find-password");
    const sectionId = document.getElementById("find-id");
    const sectionPw = document.getElementById("find-password");
    const formFindId = document.getElementById("form-find-id");
    const formFindPw = document.getElementById("form-find-pw");

    // 버튼 클릭 시 폼 보이기 + 버튼 색상 상태 유지
    btnFindId.addEventListener("click", () => {
        sectionId.classList.add("active");
        sectionPw.classList.remove("active");

        btnFindId.classList.add("selected");
        btnFindPw.classList.remove("selected");
    });

    btnFindPw.addEventListener("click", () => {
        sectionPw.classList.add("active");
        sectionId.classList.remove("active");

        btnFindPw.classList.add("selected");
        btnFindId.classList.remove("selected");
    });

    // 아이디 찾기 폼 제출 (클릭 또는 엔터키)
    formFindId.addEventListener("submit", (event) => {
        event.preventDefault(); // 기본 폼 제출 방지
        const name = document.getElementById("name-for-id").value;
        const phone = document.getElementById("phone-for-id").value;
        alert(`아이디는( )입니다.`);
        window.location.href = "../login.html";
    });

    // 비밀번호 찾기 폼 제출 (클릭 또는 엔터키)
    formFindPw.addEventListener("submit", (event) => {
        event.preventDefault(); // 기본 폼 제출 방지
        const id = document.getElementById("id-for-pw").value;
        const phone = document.getElementById("phone-for-pw").value;
        alert(`비밀번호 변경페이지로 이동합니다. `);
        window.location.href = "../Pw_change/pw_change.html";
    });

    // 페이지 로드 시 기본 선택 버튼 스타일 설정
    btnFindId.classList.add("selected");
});