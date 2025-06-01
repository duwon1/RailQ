<%@page import="java.util.List"%>
<%@page import="dto.CityDto"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link rel="stylesheet" href="../../../../css/stationModal.css">
<script src="../../../../js/stationModal.js"></script>

<div class="station-modal-zindex">
    <div class="station-modal-conainer">
        <div class="tit_wrap">
            <div class="tit">
                <span class="title-title">할인쿠폰 조회</span>
                <button type="button" class="modal-btn_close" onclick="stationModalClose()"></button>
            </div>
        </div>
        <div class="con-wrap">
            <div class="sch-box">
                <input type="text" placeholder="역 이름 또는 초성 검색(서울 : ㅅㅇ)">
                <button type="button" class="modal-btn-sch" onclick=""></button>
            </div>
            <div class="tab-content">
                <ul class="tab-bar">
                    <li class="tab-btn s"><button type="button">주요역</button></li>
                    <li class="tab-btn"><button type="button">지역별</button></li>
                </ul>

                <p class="sm-tit">01.지역선택</p>
                <ul class="station-content-ch-list" id="city-list">
                    <c:forEach var="c" items="${cityList}">
                        <li class="content-a cursor">
                            <a href="#" class="city-link" data-citycode="${c.citycode}">${c.cityname}</a>
                        </li>
                    </c:forEach>
                </ul>

                <p class="sm-tit">02.역선택</p>
                <ul class="station-content-ch-list" id="region-list">
                    <c:forEach var="c" items="${regionList}">
                        <li class="content-a cursor">
                            <a href="#" class="region-link" data-nodeid="${c.nodeid}">${c.nodename}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
