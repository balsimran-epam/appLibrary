<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.epam.i18n.text" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>

<form action="ControllerServlet" method="get">
	<input type="hidden" name="userName" value="${sessionScope.userName }" />
   <input type="hidden" name="password" value="${sessionScope.password }" />
    <input type="hidden" name="action" value="${sessionScope.action}" />
 
				<select id="language" name="language" onchange="submit()">
					<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
					<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
					<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
				</select>
			</form>

	<c:choose>
	<fmt:message key="user.label.message" var="message" />
			<div style="font-size: 150%">${message}</div>
		<c:when test="${sessionScope.userInfo != null && sessionScope.adminRole!=null}">
			<div align="center">
				<form name="logout" action="ControllerServlet" method="post">
				
					


					<c:out value="${userInfo.name}"></c:out>

<input type="hidden" name="action" value="Logout">

 <fmt:message key="user.button.submit" var="buttonValue" /><input type="submit" value="${buttonValue}">


				</form>

			</div>

		</c:when>
		<c:otherwise>
			<c:redirect url="/loginPage.jsp" />
		</c:otherwise>
	</c:choose>

</body>
</html>