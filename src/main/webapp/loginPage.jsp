<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.epam.i18n.text" />
<!DOCTYPE html >
<html lang="${language}">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="css/loginStyles.css" rel="stylesheet" type="text/css" />
<head>

<title>Login Page</title>

</head>
<body onkeydown="return (event.keyCode != 116)">





	<form action="loginPage.jsp">
		<select id="language" name="language" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
			<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
		</select>
	</form>

	<div class="wrapper row2">
		<nav id="mainav" class="hoc clear">
			<ul class="clear">
				<li class="active"><a href="home.jsp"><fmt:message
							key="register.user.home" var="home" /> ${home}</a></li>



			</ul>
		</nav>
	</div>



	<div class="container">
		<div class="login">
			<h1 class="login-heading">
				<strong><fmt:message key="login.header.wc" var="wc" />
					${wc}</strong>
				<fmt:message key="login.header.loginmsg" var="loginmsg" />
				${loginmsg}.
			</h1>
			<form name="login" action="ControllerServlet" method="post" class="labelform">

				<label for="username" style="color:white"><fmt:message
						key="login.label.username" /> : </label> <input type="text"
					name="userName" class="input-txt" placeholder="Email address"
					required autofocus>
					<br> 
				<label style="color:white" for="password"><fmt:message
						key="login.label.password" />:</label> <input type="password"
					name="password" class="input-txt" placeholder="Password" required>
				<div class="login-footer">
					<fmt:message key="login.button.submit" var="buttonValue" />
					<button class="btn btn--right btn-primary btn-block" type="submit">${buttonValue}</button>

				</div>


				<input type="hidden" name="action" value="Login">

			</form>
		</div>
	</div>



	<c:if test="${ errorMessage!=null}">
	<div align="center">
		<div class="alert alert-danger"
			style="margin-top: 150px; width: 474px" align="center">
			<strong><fmt:message key="login.error.fail" var="fail" />
				${fail}</strong>
			<fmt:message key="login.error.message" var="message" />
			<div style="color: #FF0000;">${message}</div>
		</div>
		</div>
	</c:if>


</body>
</html>