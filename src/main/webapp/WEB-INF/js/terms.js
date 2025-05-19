document.addEventListener('DOMContentLoaded', function() {
    // 요소 참조
    const checkAll = document.getElementById('check-all');
    const checkItems = document.querySelectorAll('.check-item');
    const nextButton = document.getElementById('next-button');
    
    // 전체 동의 체크박스 이벤트
    checkAll.addEventListener('change', function() {
      checkItems.forEach(item => {
        item.checked = checkAll.checked;
      });
      validateForm();
    });
    
    // 개별 체크박스 이벤트
    checkItems.forEach(item => {
      item.addEventListener('change', function() {
        validateForm();
        
        // 모든 항목이 체크되었는지 확인
        const allChecked = Array.from(checkItems).every(checkbox => checkbox.checked);
        checkAll.checked = allChecked;
      });
    });
    
    // 필수 항목 체크 여부에 따라 버튼 활성화/비활성화
    function validateForm() {
      const requiredChecked = Array.from(checkItems)
        .filter(item => item.dataset.required === 'true')
        .every(checkbox => checkbox.checked);
        
      nextButton.disabled = !requiredChecked;
    }; 
    // 다음 버튼 클릭 이벤트
    nextButton.addEventListener('click', function() {
      // 여기에 다음 단계로 이동하는 코드 작성
      alert('약관에 동의하셨습니다. 회원가입 페이지로 이동합니다.');
      // 실제로는 아래와 같이 페이지 이동
       window.location.href = "../Join/join.html";
    });
    const cancelButton = document.getElementById('cancelButton');

cancelButton.addEventListener('click', function() {
  window.location.href = "../login.html";
});

  });