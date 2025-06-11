<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="/css/dateModal.css">
<script src="/js/dateModal.js"></script>
<div class="date-wrap-z-index">
    <div class="date-wrap-now">
        <div class="date-tit_wrap">
            <h1>날짜선택</h1>
            <button class="close-btn" onclick="date_modal_close()"></button>
        </div>
        <div class="date-line"></div>
        <div class="date-con_Wrap">
            <div class="date-active">
                <p class="info_day"></p>
                <p class="info_time"></p>
            </div>
            <div class="datepicker">
                <div class="date">
                    <button class="left-btn date-date-btn" id="left-day"></button>
                    <p class="date-date">2025.05</p>
                    <button class="rigth-btn date-date-btn" id="right-day"></button>
                </div>

                <div class="tableWrap">
                    <table>
                        <colgroup>
                            <col style="width: 14.2858%;">
                            <col style="width: 14.2858%;">
                            <col style="width: 14.2858%;">
                            <col style="width: 14.2858%;">
                            <col style="width: 14.2858%;">
                            <col style="width: 14.2858%;">
                            <col style="width: 14.2858%;">
                        </colgroup>
                        <thead>
                            <tr>
                                <th>일</th>
                                <th>월</th>
                                <th>화</th>
                                <th>수</th>
                                <th>목</th>
                                <th>금</th>
                                <th>토</th>
                            </tr>
                        </thead>
                        <tbody id="calendar-body"></tbody>
                    </table>
                </div>
        	</div>
        	
        	<div class="time-select">
           		<h2>시간선택</h2>
            	<div class="slick-list">
            		<button class="slick-list-left-btn"></button>
            		<div class="slick-list-box">
	            		<div class="slick-rack">
							<div class="slick-item"><button>00시</button></div>
							<div class="slick-item"><button>01시</button></div>
							<div class="slick-item"><button>02시</button></div>
							<div class="slick-item"><button>03시</button></div>
							<div class="slick-item"><button>04시</button></div>
	            		</div>
	            		<div class="slick-rack">
							<div class="slick-item"><button>05시</button></div>
							<div class="slick-item"><button>06시</button></div>
							<div class="slick-item"><button>07시</button></div>
							<div class="slick-item"><button>08시</button></div>
							<div class="slick-item"><button>09시</button></div>
	            		</div>
	            		<div class="slick-rack">
							<div class="slick-item"><button>10시</button></div>
							<div class="slick-item"><button>11시</button></div>
							<div class="slick-item"><button>12시</button></div>
							<div class="slick-item"><button>13시</button></div>
							<div class="slick-item"><button>14시</button></div>
	            		</div>
	            		<div class="slick-rack">
							<div class="slick-item"><button>15시</button></div>
							<div class="slick-item"><button>16시</button></div>
							<div class="slick-item"><button>17시</button></div>
							<div class="slick-item"><button>18시</button></div>
							<div class="slick-item"><button>19시</button></div>
	            		</div>
	            		<div class="slick-rack">
	            			<div class="slick-item"><button>20시</button></div>
							<div class="slick-item"><button>21시</button></div>
							<div class="slick-item"><button>22시</button></div>
							<div class="slick-item"><button>23시</button></div>
	            		</div>
            		</div>
            		<button class="slick-list-right-btn"></button>
            	</div>
             </div>
             <div class="date-sub-btn">
	             <button class="close" onclick="date_modal_close()">취소</button>
	             <button class="submit" onclick="date_submit()">적용</button>
	             <input type="hidden" id="getDate" value="${date}">
	             <input type="hidden" id="gettime" value="${time}">
             </div>
        </div>
    </div>
</div>