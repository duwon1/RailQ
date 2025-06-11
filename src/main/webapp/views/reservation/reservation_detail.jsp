<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>승차권 예약</title>
    <link rel="stylesheet" href="/css/reservation_detail.css">
</head>
<body>
    <div class="top-container">
        <h1 class="sm-title">승차권 예약</h1>
    </div>
    <div class="container">        
        <div class="rail-inner">
            <div class="li-color"></div>
            <div class="info-inner">
                <div class="flag-wrap">
                    <span class="train_ktx_ticket" style="background-image: url('/img/${trainName}.png');"></span>
                    <p class="ticket-first">${trainId}</p>
                    <p class="ticket-first">${date} (${dayOfWeek})</p>
                </div>
                <div class="date-box">
                    <h2>${startStation} → ${lastStation} (${startTime} ~ ${lastTime})</h2>
                </div>
                <div class="date-box">
                    <span>1호차 일반실</span>
                </div>
                <div class="date-box">
                    <span>결제기한: ${deadline}</span>
                </div>
            </div>
            <div class="info-price">
            	<h2><fmt:formatNumber value="${price}" type="number" groupingUsed="true" />원</h2>
            </div>
        </div>
        <div class="btnWrap">
            <form action="/reservation/pay" method="post"  onsubmit="return confirmPrice()">
                <input type="hidden" name="trainId" value="${trainId}">
                <input type="hidden" name="date" value="${date}">
                <input type="hidden" name="startStation" value="${startStation}">
                <input type="hidden" name="lastStation" value="${lastStation}">
                <input type="hidden" name="startTime" value="${startTime}">
                <input type="hidden" name="lastTime" value="${lastTime}">
                <input type="hidden" name="price" id="hidden-price" value="${price}">
                <button type="submit" class="pay-btn">결제하기</button>
            </form>
        </div>

        <div class="uf_info-wrap">
            <h2>안내</h2>
            <ul class="uf_list">
                <li>10분 이내 결제하셔야 승차권 구매가 완료됩니다.</li>
                <li>승차권을 발권받은 스마트폰에서만 확인할 수 있습니다.</li>
                <li>할인승차권 이용 시 신분증 또는 증명서가 필요합니다.</li>
            </ul>
            <h2>꼭 알아두세요!</h2>
            <ul class="uf_list">
                <li><a href="#none">승차권 환불 위약금 확인하기</a></li>
                <li>역창구 변경 시 할인 취소될 수 있습니다.</li>
                <li>승차 시 실제 승차권을 소지해야 합니다.</li>
            </ul>
        </div>
    </div>
    
    <script type="text/javascript">
	    function confirmPrice() {
	        const price = document.getElementById('hidden-price').value;
	        const formattedPrice = Number(price).toLocaleString('ko-KR');
	        return confirm("결제금액 " + formattedPrice + " 원이 맞습니까?");
	    }
    </script>
</body>
</html>
