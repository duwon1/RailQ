<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="/css/discountModal.css">
<script src="/js/discountModal.js"></script>
<div class="station-modal-zindex">
    <div class="station-modal-conainer">
        <div class="tit_wrap">
            <div class="tit">
                <span class="title-title">할인쿠폰 조회</span>
                <button type="button" class="modal-btn_close" onclick="discountModalClose()"></button>
            </div>
        </div>
        <div class="con-wrap">
            <div class="popup-callout">
                <span>유효기간은 <b>열차출발시간</b> 기준입니다.</span>
                <span>할인쿠폰은 다른 할인과 중복 적용하지 않습니다.</span>
                <span>할인쿠폰은 유효기간 내에 출발하는 열차에 한하여 적용 합니다.</span>
            </div>
            <div class="small-tit">
                <h3>할인쿠폰 입력하기</h3>
            </div>
            
            <div class="small-container">
                <div class="popup-form-item">
                    <label for="coupon">쿠폰번호</label>
                    <input type="text" id="coupon" placeholder="쿠폰번호 10자리를 입력하세요" maxlength="10">
                </div>
                <div class="popup-form-item">
                    <label for="pw">비밀번호</label>
                    <input type="password" id="pw" placeholder="비밀번호를 입력하세요">
                </div>
                <div class="popup-form-item">
                    <button type="button" class="submit">적용</button>
                </div>
            </div>
        </div>
    </div> 
</div>