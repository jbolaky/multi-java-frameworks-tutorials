<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>

<html>
<body>

<div>
	<c:if test="${not empty param.login_error}">
		<div class="error">
			Your login attempt was not successful, try again.<br /><br />
			Reason: <%= ((AuthenticationException) session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>
		</div>
	</c:if>
	<div id="error" style="display: none"><h3>Bad credential entered</h3></div>
	<form name="f" action="<c:url value="/loginProcess" />" method="post">
			<p>
				<label for="j_username">User:</label>
				<br />
				<input type="text" name="j_username" id="j_username" <c:if test="${not empty param.login_error}">value="<%= session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY) %>"</c:if> />
			</p>
			<p>
				<label for="j_password">Password:</label>
				<br />
				<input type="password" name="j_password" id="j_password" />
			</p>
			<p>
				<button id="submit" type="submit">Login</button>
			</p>
	</form>
</div>


	<script type="text/javascript">
	function checkUrlToDisplayLoginErrMsg()
	{
		var str = location.href.match(/\?error\=|&error\=/i);
		if(str.length>0){
			var ele = document.getElementById("error");
			ele.style.display = "block";
		}
	}
		checkUrlToDisplayLoginErrMsg();
	</script>


</body>
</html>