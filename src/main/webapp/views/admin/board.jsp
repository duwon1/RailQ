<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="header.jsp" />
<link rel="stylesheet" href="../../../css/admin/board.css">
<div class="content-area">
    <div class="main-container">
        <div><h1>게시물관리</h1></div>
        <div class="search-box">
        	<button type="button" class="insert-button" onclick="location.href='/admin/boardForm'">게시물 작성</button>
            <span>Search</span><input type="text">
        </div>
        <table class="main-table">
            <thead>
                <tr class="header-table">
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성일</th>
                    <th>조회수</th>
                    <th>비고</th>
                </tr>
            </thead>
            <tbody>
                <tr class="content-table">
                    <td>1</td>
					<td>홍길동</td>
                    <td>2024-05-03</td>
                    <td>30</td>
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