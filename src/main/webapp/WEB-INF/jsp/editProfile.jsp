<%@ page language="java" contentType="text/html;UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.epam.i18n.text" />
<!DOCTYPE html>
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>Insert title here</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />


<meta charset="UTF-8">

<link rel="stylesheet" href="css/style.css">

</head>

<body onkeydown="return (event.keyCode != 116)">
	<form action="ControllerServlet" method="get">
		<input type="hidden" name="previousCommand"
			value="${sessionScope.action }" /> <input type="hidden"
			name="action" value="languageChanged" /> <input type="hidden"
			name="userName" value="${sessionScope.userName }" /> <input
			type="hidden" name="password" value="${sessionScope.password }" /> <input
			type="hidden" name="typeOfBook" value="${sessionScope.typeOfBook }" />
			 <input
			type="hidden" name="userId" value="${sessionScope.userId }" />
		<select id="language" name="language" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
			<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
		</select>
	</form>

	<div class="container" >
		<section id="content" style="width:700px;">
		
		<form class="form-horizontal" action="ControllerServlet" method="post">
		<input type="hidden" name="action" value="updateUserInfo" />
		
			<div class="form-group">
				<label class="control-label col-sm-4" for="firstname"><fmt:message
						key="home.label.firstName" /></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="firstName"
						value="${ retrievedUser.firstName}">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="lastname"><fmt:message
						key="home.label.lastName" /></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="lastName"
						value="${ retrievedUser.lastName}">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="email"><fmt:message
						key="home.label.email" /></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="email" name="email"
						value="${ retrievedUser.email}">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="streetaddress"><fmt:message
						key="home.label.streetAddress" /></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="email" name="streetAddress"
						value="${ retrievedUser.streetAddress}">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="localityAddress"><fmt:message
						key="home.label.localityAddress" /></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="email" name="localityAddress"
						value="${ retrievedUser.localityAddress}">
				</div>
			</div>
			<fmt:message key="admin.radio.updateUser" var="updateUser" />
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">${updateUser}</button>
				</div>
			</div>
		</form>
		<!-- form --> </section>
		<!-- content -->
	</div>
	<!-- container -->
</body>

<script src="js/index.js"></script>



<a href="ControllerServlet?action=gettingBook&typeOfBook=ALL">
	<button class="button button1">
		<fmt:message key="user.table.goBack" var="goBack" />
		<h3 style="color:blue;">${goBack}</h3>
	</button>
</a>

</body>
</html>