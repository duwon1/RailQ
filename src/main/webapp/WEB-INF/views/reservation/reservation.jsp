<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RailQ</title>
<link rel="stylesheet" href="../../../css/reservation.css">
<script src="../../../js/reservation.js"></script>
</head>
<body>
    <div class="top-container">
        <h1 class="sm-title">승차권 예매</h1>
    </div>
		<div class="container">
        <div class="date-wrap">
            <a href="#" class="btn-arrow btn-bf cursor"></a>
            <a href="#" class="day-type"><span class="btn-date">0000-00-00(화) 00:00</span></a>
            <a href="#" class="btn-arrow btn-nt cursor"></a>
        </div>
        <div class="selectArea-wrap">
        	<div class="left-wrap">
                <div class="btn-pop cursor">
                    <h3 class="start"><a href="#none" id="start-station" onclick="stationModalOpen('1')">${start}</a></h3>
                </div>
                <div class="btn-box">
                    <button type="button" class="btn-change cursor" ></button>
                </div>
                <div class="btn-pop cursor">
                    <h3 class="last"><a href="#none" id="last-station" onclick="stationModalOpen('2')">${last}</a></h3>
                </div>
                <div class="btn-recom cursor" id="person-pop">
                   <h3><a href="#none" onclick="personModalOpen()">총1명</a></h3>
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
       	<div class="rail-inner">
       	    <div class="li-color"></div>
            <div class="info-inner">
                <div class="flag-wrap">
                   <span class="train_ktx_ticket"></span>
                   <p class="num">600</p>
                </div>
                <div class="date-box">
                   <h2>서울→부산(21:28 ~ 00:04)</h2>
                   <span class="s-txt">소요시간2시간36분</span>
                </div>
            </div>
            <div class="price-inner">
                <div class="price-box cursor">
                    <p>일반실<p>
                    <h3>50,000</h3>
                    <p class="txt-gr">5%적립</p>
                </div>
                <div class="price-box cursor">
                    <p>특실<p>
                    <h3>50,000</h3>
                    <p class="txt-gr">5%적립</p>
                </div>
           </div>
       	</div>
    </div>

    <!-- 더보기 및 다음날 조회하기 -->
    <div class="bottom-container">
        <div class="more cursor">
            <h3 class="page_group">더보기</h3>
        </div>
        <div class="more tomorrow cursor">
            <h3 class="page_group">다음날 (25년05월15일) 조회</h3>
        </div>
    </div>
    
    <div class="bottom-bar-container down">
        <div class="reserv_wrapbtn">
            <button type="button" class="reserv_btn" onclick="reserv_btn_click()"></button>
        </div>
        <div class="sm-container">
            <div class="ticket_reserv">
                <div class="reserv_first">
                    <span>특실</span>
                    <span>자유석2량></span>
                </div>
                <!-- <div class="reserv_first">
                    <span>특실</span>
                    <span>자유석2량></span>
                </div> -->
            </div>
            <div class="reserv_center">
                <span class="reserv-center-item">열차시각</span>
                <span class="reserv-center-item">운임요금</span>
                <span class="reserv-center-item">좌석선택</span>
                <span class="reserv-center-item buy-item">예매</span>
            </div>
        </div>
    </div>
    <c:import url="modal/stationModal.jsp" />
    <c:import url="modal/personModal.jsp" />
</body>
</html>