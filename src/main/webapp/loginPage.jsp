<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.epam.i18n.text" />
<!DOCTYPE html >
<html lang="${language}">
<head>

<title>Insert title here</title>
</head>
<body>

	<table align="center">
		<tr>
			<td><h2><fmt:message key="login.header.name" var="headerName" />
				<div style="color:#0000FF "blue";">${headerName}</h2></td>
		</tr>
	</table>
	
	

			<form action="loginPage.jsp">
				<select id="language" name="language" onchange="submit()">
					<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
					<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
					<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
				</select>
			</form>
			<table width="440px" align="center"
		style="border: 1px solid #000000; background-color: #efefef;">
<form name="login" action="ControllerServlet" method="post">
		
			<tr>
				<td><b><label for="username"><fmt:message key="login.label.username" /> : </label></b></td>
				<td> <input type="text" name="userName"/><small><label for="message"><fmt:message key="login.label.message" /></small></td>
			</tr>
			<tr>
				<td><b>  <label for="password"><fmt:message key="login.label.password" />:</label>
				<td><input type="password" name="password"></td>
			</tr>

			<input type="hidden" name="action" value="Login">

			<tr>
				<td></td>
				<td> <fmt:message key="login.button.submit" var="buttonValue" /><input type="submit" value="${buttonValue}"></td>
			</tr>
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>

	</form>

		</table>
	
	<tr>
		<td><c:if test="${ errorMessage!=null}">
<fmt:message key="login.error.message" var="errorMessage" />
				<div style="color: #FF0000;">${errorMessage}</div>
			</c:if></td>
	</tr>

</body>
</html>