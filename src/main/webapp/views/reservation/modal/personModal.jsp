<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="/css/personModal.css">
<script src="/js/personModal.js"></script>
<div class="person-modal-zindex">
    <div class="person-modal-conainer">
       <div class="tit_wrap">
		        <div class="tit">
		            <span class="title-title">인원선택</span>
		            <button type="button" class="modal-btn_close" onclick="personModalClose()"></button>
		        </div>
        </div>
        <div class="con-wrap">
            <ul class="content-ch-list">
                <li class="person-content-a">
                    <p>어른(13세 이상)</p>
                    <div class="flo-right">
                        <button type="button" class="down-btn flo-btn"></button>
                        <span>${pMap.adult}</span>
                        <input type="hidden" id="adult" name="adult" value="${pMap.adult}">
                        <button type="button" class="up-btn flo-btn"></button>
                    </div>
                </li>
                <li class="person-content-a">
                    <p>어린이(6 ~ 12세)</p>
                    <div class="flo-right">
                        <button type="button" class="down-btn flo-btn"></button>
                        <span>${pMap.child}</span>
                        <input type="hidden" id="child" name="child" value="${pMap.child}">
                        <button type="button" class="up-btn flo-btn"></button>
                    </div>
                </li>
                <li class="person-content-a">
                    <p>경로(65세 이상)</p>
                    <div class="flo-right">
                        <button type="button" class="down-btn flo-btn"></button>
                        <span>${pMap.elderly}</span>
                        <input type="hidden" id="elderly" name="elderly" value="${pMap.elderly}">
                        <button type="button" class="up-btn flo-btn"></button>
                    </div>
                </li>
                <li class="person-content-a">
                    <p>국가유공자</p>
                    <div class="flo-right">
                        <button type="button" class="down-btn flo-btn"></button>
                        <span>${pMap.nice}</span>
                        <input type="hidden" id="nice" name="nice" value="${pMap.nice}">
                        <button type="button" class="up-btn flo-btn"></button>
                    </div>
                </li>
            </ul>
        </div>
        <div class="date-sub-btn psn">
        	<input type="hidden" id="person_total" name="total" value="${pMap.total}">
            <button class="close" onclick="personModalClose()">취소</button>
            <button class="submit" id="person_submit">적용</button>
        </div>
    </div> 
</div>
