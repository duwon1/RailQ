// register.js
// 성별 선택 기능
function selectGender(gender) {
    document.getElementById('male').classList.remove('selected');
    document.getElementById('female').classList.remove('selected');
    document.getElementById(gender).classList.add('selected');
    document.getElementById('gender').value = gender;
}

// 회원가입 제출 처리
function submitRegister() {
    const form = document.getElementById('registerForm');
    const userid = form.userid.value;
    const password = form.password.value;
    const name = form.name.value;
    const birthdate = form.birthdate.value;
    const phonenumber = form.phonenumber.value;
    const gender = form.gender.value;
    
    // 입력 검증
    if (!userid || !password || !name || !birthdate || !gender) {
        alert('모든 항목을 입력해 주세요.');
        return;
    }

    // 아이디 검증 (한글 6자 이내)
    if (userid.length > 6) {
        alert('아이디는 6자 이내로 입력해 주세요.');
        return;
    }

    // 비밀번호 검증 (영문, 숫자 조합 6-20자)
    if (password.length < 6 || password.length > 20) {
        alert('비밀번호는 6-20자 이내로 입력해 주세요.');
        return;
    }

    if (!/^[a-zA-Z0-9]+$/.test(password)) {
        alert('비밀번호는 영문, 숫자만 사용 가능합니다.');
        return;
    }

    // 생년월일 검증 (8자리)
    if (birthdate.length !== 8 || !/^\d+$/.test(birthdate)) {
        alert('생년월일은 8자리 숫자로 입력해 주세요. (예: 19991231)');
        return;
    }
    // 생년월일 검증 (8자리)
    if (phonenumber.length !== 11 || !/^\d+$/.test(phonenumber)) {
        alert('전화번호는 11자리 숫자로 입력해 주세요. (예: 010-0000-0000)');
        return;
    }
    alert('회원가입이 완료되었습니다.');
    window.location.href = "../login.html";
}