<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RailQ</title>
<link rel="stylesheet" href="/css/reservation.css">
<script src="/js/reservation.js"></script>
</head>
<body>
    <div class="top-container">
        <h1 class="sm-title">승차권 예매</h1>
    </div>
		<div class="container">
        <div class="date-wrap">
            <a href="#" class="btn-arrow btn-bf cursor"></a>
            <a href="#" class="day-type" onclick="date_modal_open()"><span class="btn-date"></span></a>
            <a href="#" class="btn-arrow btn-nt cursor"></a>
        </div>
        <div class="selectArea-wrap">
        	<div class="left-wrap">
                <div class="btn-pop cursor">
                    <h3 class="start"><a href="#none" id="start-station" data-station="${rMap.start_id}" onclick="stationModalOpen('1')">${rMap.start_name}</a></h3>
                </div>
                <div class="btn-box">
                    <button type="button" class="btn-change cursor" ></button>
                </div>
                <div class="btn-pop cursor">
                    <h3 class="last"><a href="#none" id="last-station" data-station="${rMap.last_id}" onclick="stationModalOpen('2')">${rMap.last_name}</a></h3>
                </div>
                <div class="btn-recom cursor" id="person-pop">
                   <h3><a href="#none" onclick="personModalOpen()" id="total_person">총${pMap.total}명</a></h3>
                </div>
            </div>
            <div class="right-wrap">
                <div class="select cursor">
                    <h3><a href="#none">일반석</a></h3>
                    <div class="option">
                        <ul>
                            <li><a href="#none">일반석</a></li>
                            <li><a href="#none">유아동반석</a></li>
                            <li><a href="#none">휠체어석</a></li>
                            <li><a href="#none">전동휠체어석</a></li>
                            <li><a href="#none">2층석</a></li>
                            <li><a href="#none">자전거거</a></li>
                            <li><a href="#none">대피도우미</a></li>
                        </ul>
                    </div>
                </div>
                <div class="select cursor">
                    <h3><a href="#none">직통</a></h3>
                    <div class="option">
                        <ul>
                            <li><a href="#none">직통</a></li>
                            <li><a href="#none">환승</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="search-option-check-wrap">
            <div class="search-option-bar-check-wrap">
                <input type="checkbox" id="a" class="search-option-bar-checkbox">
                <label for="a">왕복</label>
            </div>
            <div class="search-option-bar-check-wrap">
                <input type="checkbox" id="b" class="search-option-bar-checkbox">
                <label for="b">인접역 포함</label>
            </div>
            <div class="search-option-bar-check-wrap">
                <input type="checkbox" id="c" class="search-option-bar-checkbox">
                <label for="c">SR연계 포함</label>
            </div>
        </div>
		<c:forEach var="r" items="${rList}">
		    <div class="rail-inner">
		        <div class="li-color"></div>
		        <div class="info-inner">
		            <div class="flag-wrap">
                        <span class="train_ktx_ticket" style="background-image: url('../img/${r.trainName}.png');"></span>
		                <p class="num">${r.trainId}</p>
		            </div>
		            <div class="date-box">
		                <h2>${r.start_station}→${r.last_station} (${r.start_time} ~ ${r.last_time})</h2>
		                <span class="s-txt">소요시간 ${r.duration}</span>
		            </div>
		        </div>
		        <div class="price-inner">
		            <div class="price-box cursor"
		                 onclick="selectTicket('${r.trainId}', '${r.start_station}', '${r.last_station}', '${r.start_time}', '${r.last_time}', '${date}', '${r.trainName}', '${r.price}')">
		                <p>일반실</p>
		                <h3><fmt:formatNumber value="${r.price}" type="number" groupingUsed="true" /></h3>
		                <p class="txt-gr">5% 적립</p>
		            </div>
		        </div>
		    </div>
		</c:forEach>
	</div>

    <!-- 더보기 및 다음날 조회하기 -->
    <!-- <div class="bottom-container">
        <div class="more cursor">
            <h3 class="page_group" onclick="nextPage()">더보기</h3>
            <input type="hidden" id="pageNum" value="1">
        </div>
        <div class="more tomorrow cursor">
            <h3 class="page_group">다음날 (25년05월15일) 조회</h3>
        </div>
    </div> -->
    
    <div class="bottom-bar-container down">
        <div class="reserv_wrapbtn">
            <button type="button" class="reserv_btn" onclick="reserv_btn_click()"></button>
        </div>
        <div class="sm-container">
            <div class="ticket_reserv">
                <div class="reserv_first">
                    <span>특실</span>
                    <span id="select-tiket">선택된 열차 정보 없음</span>
                </div>
            </div>
            <div class="reserv_center">
                <span class="reserv-center-item">열차시각</span>
                <span class="reserv-center-item">운임요금</span>
                <span class="reserv-center-item">좌석선택</span>
                <span class="reserv-center-item buy-item" onclick="submitReservation()">예매</span>
            </div>
        </div>
    </div>
    <form id="searchForm" method="GET" action="/reservation">
	    <input type="hidden" name="start_id" id="start_id" value="${rMap.start_id}">
	    <input type="hidden" name="start_name" id="start_name" value="${rMap.start_name}">
	    <input type="hidden" name="last_id" id="last_id" value="${rMap.last_id}">
	    <input type="hidden" name="last_name" id="last_name" value="${rMap.last_name}">
	    <input type="hidden" name="date" id="reservationDate" value="${date}">
	    <input type="hidden" name="time" id="reservationTime" value="${time}">
	    <input type="hidden" name="total" id="total" value="${pMap.total}">
	</form>
	<form id="reservationForm" method="GET" action="/reservation/detail">
	    <input type="hidden" name="trainId" id="form-trainId">
	    <input type="hidden" name="start_station" id="form-startStation">
	    <input type="hidden" name="last_station" id="form-lastStation">
	    <input type="hidden" name="start_time" id="form-startTime">
	    <input type="hidden" name="last_time" id="form-lastTime">
	    <input type="hidden" name="date" id="form-date">
	    <input type="hidden" name="trainName" id="form-trainName">
	    <input type="hidden" name="price" id="form-price">
	</form>

    <c:import url="modal/stationModal.jsp" />
    <c:import url="modal/personModal.jsp" />
    <c:import url="modal/dateModal.jsp" />
</body>
</html>