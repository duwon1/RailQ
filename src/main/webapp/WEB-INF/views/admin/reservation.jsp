<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="../../../css/admin/member.css">
		<div class="content-area">
		    <div class="main-container">
		        <div><h1>회원관리</h1></div>
		        <div class="search-box">
		            <span>Search</span><input type="text">
		        </div>
		        <table class="main-table">
		            <thead>
		                <tr class="header-table">
		                    <th>번호</th>
		                    <th>티켓정보</th>
		                    <th>이름</th>
		                    <th>출발지</th>
		                    <th>목적지</th>
		                    <th>비고</th>
		                </tr>
		            </thead>
		            <tbody>
		                <tr class="content-table">
		                    <td>1</td>
							<td>홍길동</td>
		                    <td>test</td>
		                    <td>2022-03-14</td>
		                    <td>2024-05-03</td>
		                    <td class="button-td">
		                        <button type="button" class="table-button">삭제</button>
		                        <button type="button" class="table-button">복구</button>
		                    </td>
		                </tr>
		                <tr class="content-table">
		                    <td>1</td>
		                    <td>홍길동</td>
		                    <td>test</td>
		                    <td>2022-03-14</td>
		                    <td>2024-05-03</td>
		                    <td class="button-td">
		                        <button type="button" class="table-button">삭제</button>
		                        <button type="button" class="table-button">복구</button>
		                    </td>
		                </tr>
		                <tr class="content-table delete-color">
		                    <td>1</td>
		                    <td>홍길동</td>
		                    <td>test</td>
		                    <td>2022-03-14</td>
		                    <td>2024-05-03</td>
		                    <td class="button-td">
		                        <button type="button" class="table-button">삭제</button>
		                        <button type="button" class="table-button">복구</button>
		                    </td>
		                </tr>
		            </tbody>
		        </table>
		    </div>
		</div>    
</body>
</html>