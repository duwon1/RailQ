// password_change.js
// 비밀번호 변경 처리
function changePassword() {
    const currentPassword = document.getElementById('current-password').value;
    const newPassword = document.getElementById('new-password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    
    // 입력 검증
    if (!currentPassword || !newPassword || !confirmPassword) {
        alert('모든 항목을 입력해 주세요.');
        return;
    }

    // 새 비밀번호 일치 여부 확인
    if (newPassword !== confirmPassword) {
        alert('새 비밀번호와 확인 비밀번호가 일치하지 않습니다.');
        return;
    }

    // 비밀번호 검증 (영문, 숫자 조합 6-20자)
    if (newPassword.length < 6 || newPassword.length > 20) {
        alert('비밀번호는 6-20자 이내로 입력해 주세요.');
        return;
    }

    if (!/^[a-zA-Z0-9]+$/.test(newPassword)) {
        alert('비밀번호는 영문, 숫자만 사용 가능합니다.');
        return;
    }
    // 임시 성공 메시지
    alert('비밀번호가 성공적으로 변경되었습니다.');
    
    // 성공 후 메인 페이지로 이동 (예시)
    window.location.href = '../login.html';
}