<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
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
                    <th style="justify-content: center;">조회수</th>
                    <th>비고</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty boardList}">
                        <c:forEach var="board" items="${boardList}">
                            <tr class="content-table">
                                <td>${board.num}</td>
                                <td>${board.title}</td>
                                <td><fmt:formatDate value="${board.createAt}" pattern="yyyy-MM-dd"/></td>
                                <td style="justify-content: center;">${board.viewCount}</td>
                                <td class="button-td">
                                    <button type="button" class="table-button" onclick="deleteBoard(${board.num})">삭제</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr><td colspan="5">등록된 게시물이 없습니다.</td></tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>
<script>
    function deleteBoard(num) {
        if (confirm("정말 삭제하시겠습니까?")) {
            location.href = "/admin/deleteBoard?num=" + num;
        }
    }
</script>
</body>
</html>
