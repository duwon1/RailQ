<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	MemberDto vo = (MemberDto)request.getAttribute("member");
	%>

	<p>번호 : <%=vo.getNum() %></p>
	<p>아이디 : <%=vo.getId() %></p>
	<p>비밀번호 : <%=vo.getPw() %></p>
	<p>이름 : <%=vo.getName() %></p>
</body>
</html>