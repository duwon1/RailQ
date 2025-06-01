<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../css/reservation_payment.css">
    <script src="../../../js/reservation_payment.js"></script>
</head>
<body>
    <div class="top-container">
        <h1 class="sm-title">결제</h1>
    </div>
    <div class="container">        
       	<div class="rail-inner">
       	    <div class="li-color"></div>
            <div class="info-inner">
                <div class="flag-wrap">
                   <span class="train_ktx_ticket"></span>
                   <p class="ticket-first">1309</p>
                   <p class="ticket-first">2025년05월21일(수)</p>
                </div>
                <div class="date-box">
                   <h2>서울 → 부산 (05:27 ~ 08:15)</h2>
                </div>
                <div class="date-box">
                   <span>일반실 | 역방향 | 10호차 | 4A | 어른</span>
                </div>
            </div>
            <div class="price-inner">
                <div>
                    <span>3000</span><span>원</span>
                </div>
                <div>
                    <button type="button" class="discount" onclick="discountModalOpen()">쿠폰</button>
                </div>
            </div>
       	</div>
        <div class="rail-inner">
       	    <div class="li-color"></div>
            <div class="info-inner">
                <div class="flag-wrap">
                   <span class="train_ktx_ticket"></span>
                   <p class="ticket-first">1309</p>
                   <p class="ticket-first">2025년05월21일(수)</p>
                </div>
                <div class="date-box">
                   <h2>서울 → 부산 (05:27 ~ 08:15)</h2>
                </div>
                <div class="date-box">
                   <span>일반실 | 정방향 | 8호차 | 4B | 어린이</span>
                </div>
            </div>
            <div class="price-inner">
                <div>
                    <span>5000</span><span>원</span>
                </div>
                <div>
                    <button type="button" class="discount" onclick="discountModalOpen()">쿠폰</button>
                </div>
            </div>
       	</div>
        <div class="btnWrap">
            <button type="button" class="">결제하기</button>
        </div>
    </div>
    <%@ include file="./modal/discountModal.jsp" %>
</body>
</html>