<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Introduction</title>
</head>
<body>
<h2>Library Holidays</h2>
<c:forEach items="${holidays}" var="holiday">
	<fmt:formatDate value="${holiday}" pattern="yyyy-MM-dd" />
	<br />
</c:forEach>
<a href="${flowExecutionUrl}&_eventId=next">Next</a>
<br>
<a href="logout">Logout</a>
</body>
</html>
