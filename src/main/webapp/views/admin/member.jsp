<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="header.jsp" />
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
                    <th>이름</th>
                    <th>유저ID</th>
                    <th>마지막로그인</th>
                    <th>ID생성일</th>
                    <th>비고</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="m" items="${memberList}">
	                <tr class="content-table">
	                    <td>${m.num}</td>
						<td>${m.name}</td>
	                    <td>${m.id}</td>
	                    <td>${m.last_login}</td>
	                    <td>${m.create_time}</td>
	                    <td class="button-td">
	                        <button type="button" class="table-button">삭제</button>
	                        <button type="button" class="table-button">복구</button>
	                    </td>
	                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>    
</body>
</html>