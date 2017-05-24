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
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="src/main/resources/layout/styles/layout.css"
	rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
#mainav {
	
}

#mainav ul {
	
}

#mainav ul ul {
	z-index: 9999;
	position: absolute;
	width: 160px;
	text-align: left;
}

#mainav ul ul ul {
	left: 160px;
	top: 0;
}

#mainav li {
	display: block;
	position: relative;
	float: left;
	margin: 0;
	padding: 0;
	text-transform: uppercase;
}

#mainav li:last-child {
	margin-right: 0;
}

#mainav li li {
	width: 100%;
	margin: 0;
	text-transform: none;
}

#mainav ul.clear {
	border: solid;
	border-width: 0 0 0 1px;
}

#mainav li a {
	display: block;
	padding: 20px;
	border: solid;
	border-width: 0 1px 1px 0;
	border-collapse: collapse;
}

#mainav li li a {
	border-width: 0 0 1px 0;
}

#mainav li li:first-child a, #mainav li li:last-child a {
	border-width: 0 0 1px 0;
}

#mainav .drop {
	padding-left: 25px;
}

#mainav li li a, #mainav li li .drop {
	display: block;
	margin: 0;
	padding: 10px 15px;
}

#mainav .drop:after, #mainav li li .drop:after {
	content: "\f0d7";
}

#mainav .drop:after {
	top: 25px;
	left: 15px;
}

#mainav li li .drop:after {
	top: 15px;
	left: 5px;
}

#mainav ul ul {
	visibility: hidden;
	opacity: 0;
}

#mainav ul li:hover>ul {
	visibility: visible;
	opacity: 1;
}

#mainav form {
	display: none;
	margin: 0;
	padding: 20px 0;
}

#mainav form select, #mainav form select option {
	display: block;
	cursor: pointer;
	outline: none;
}

#mainav form select {
	width: 100%;
	padding: 5px;
	border: 1px solid;
}

#mainav form select option {
	margin: 5px;
	padding: 0;
	border: none;
}
/* Breadcrumb */
#breadcrumb {
	padding: 30px 0 20px;
	border-bottom: 1px solid;
	text-align: right;
}

#breadcrumb ul {
	margin: 0;
	padding: 0;
	list-style: none;
}

#breadcrumb li {
	display: inline-block;
	margin: 0 6px 0 0;
	padding: 0;
}

#breadcrumb li a {
	display: block;
	position: relative;
	margin: 0;
	padding: 0 12px 0 0;
	font-size: 12px;
}

#breadcrumb li a::after {
	top: 3px;
	right: 0;
	content: "\f101";
}

#breadcrumb li:last-child a {
	margin: 0;
	padding: 0;
}

#breadcrumb li:last-child a::after {
	display: none;
}

/* Sidebar Navigation */
.sidebar nav {
	display: block;
	width: 100%;
}

.sidebar nav li {
	margin: 0 0 3px 0;
	padding: 0;
}

.sidebar nav a {
	display: block;
	position: relative;
	margin: 0;
	padding: 5px 10px 5px 15px;
	text-decoration: none;
	border: solid;
	border-width: 0 0 1px 0;
}

.sidebar nav a::after {
	top: 9px;
	left: 5px;
	content: "\f101";
}

.sidebar nav ul ul a {
	padding-left: 35px;
}

.sidebar nav ul ul a::after {
	left: 25px;
}

.sidebar nav ul ul ul a {
	padding-left: 55px;
}

.sidebar nav ul ul ul a::after {
	left: 45px;
}

/* Pagination */
.pagination {
	display: block;
	width: 100%;
	text-align: center;
	clear: both;
}

.pagination li {
	display: inline-block;
	margin: 0 2px 0 0;
}

.pagination li:last-child {
	margin-right: 0;
}

.pagination a, .pagination strong {
	display: block;
	padding: 8px 11px;
	border: 1px solid;
	background-clip: padding-box;
	font-weight: normal;
}

/* Back to Top */
#backtotop {
	z-index: 999;
	display: inline-block;
	position: fixed;
	visibility: hidden;
	bottom: 20px;
	right: 20px;
	width: 36px;
	height: 36px;
	line-height: 36px;
	font-size: 16px;
	text-align: center;
	opacity: .2;
}

#backtotop i {
	display: block;
	width: 100%;
	height: 100%;
	line-height: inherit;
}

#backtotop.visible {
	visibility: visible;
	opacity: .5;
}

#backtotop:hover {
	opacity: 1;
}

#mainav ul.clear {
	border-color: #252B34;
}

#mainav li a {
	color: inherit;
	background-color: inherit;
	border-color: #252B34;
}

#mainav .active a, #mainav a:hover, #mainav li:hover>a {
	color: #82B440;
	background-color: inherit;
}

#mainav li li a, #mainav .active li a {
	color: #FFFFFF;
	background-color: rgba(0, 0, 0, .6);
	border-color: rgba(0, 0, 0, .6);
}

#mainav li li:hover>a, #mainav .active .active>a {
	color: #FFFFFF;
	background-color: #82B440;
}

#mainav form select {
	color: #FFFFFF;
	background-color: #394251;
	border-color: #888888;
}

#breadcrumb {
	border-color: #D7D7D7;
}

#breadcrumb a {
	color: inherit;
	background-color: inherit;
}

#breadcrumb li:last-child a {
	
}

.container .sidebar nav a {
	color: inherit;
	border-color: #D7D7D7;
}

.container .sidebar nav a:hover {
	color: #82B440;
}

.pagination a, .pagination strong {
	border-color: #D7D7D7;
}

.pagination .current * {
	color: #FFFFFF;
	background-color: #82B440;
}

#backtotop {
	color: #FFFFFF;
	background-color: #82B440;
}

#header {
	padding: 30px 0;
}

#header #logo {
	margin-top: 8px;
	font-variant: small-caps;
}

#header #logo h1 {
	margin: 0;
	padding: 0;
	font-size: 26px;
}

#header #quickinfo {
	font-size: .8rem;
	text-transform: uppercase;
}

#header #quickinfo li {
	margin-right: 50px;
}

#header #quickinfo li:last-child {
	margin-right: 0;
}

#header #quickinfo strong {
	display: block;
	margin: 0 0 -5px 0;
	padding: 0;
	font-size: .9rem;
}

#header #quickinfo br {
	display: none;
}
</style>
</head>
<body id="top" onkeydown="return (event.keyCode != 116)">



	<form action="home.jsp">
	<input type="hidden" name="action" value="${sessionScope.action}" />
		<select id="language" name="language" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
			<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
		</select>
	</form>

	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<div class="wrapper row0">
		<div id="topbar" class="hoc clear">
			<!-- ################################################################################################ -->

			<!-- ################################################################################################ -->
		</div>
	</div>
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->

	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<div class="wrapper row2">
		<nav id="mainav" class="hoc clear">
			<!-- ################################################################################################ -->
			<ul class="clear">
				<li class="active"><a href="home.jsp"><fmt:message
										key="register.user.home" var="home" />
									${home}</a></li>

				</li>
				<li><a href="loginPage.jsp"><fmt:message
										key="register.user.login" var="login" />
									${login}</a></li>
				<li><a href="#signup" data-toggle="modal"
					data-target=".bs-modal-sm"><fmt:message
										key="register.user.sign" var="sign" />
									${sign}</a></li>
				<li><a href="#"><fmt:message
										key="register.user.au" var="au" />
									${why}</a></li>

			</ul>
			<!-- ################################################################################################ -->
		</nav>
	</div>
	<div class="container">
		<hr class="prettyline">
		<br>
		<div class="modal fade bs-modal-sm" id="myModal" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<br>
					<div class="bs-example bs-example-tabs">
						<ul id="myTab" class="nav nav-tabs">

							<li class=""><a href="#signup" data-toggle="tab"><fmt:message
										key="register.user.header1" var="register" />
									${register}:</a></li>
							<li class=""><a href="#why" data-toggle="tab"><fmt:message
										key="register.user.why" var="why" />
									${why}:</a></li></a></li>
						</ul>
					</div>
					<div class="modal-body">
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade in" id="why">
								<p>We want to provide you our all services without being late.</p>
								<p></p>
								
							</div>

							<div class="tab-pane fade active in" id="signup">
								<form class="form-horizontal" action="ControllerServlet"
									method="get">
									<fieldset>
										<!-- Sign Up Form -->
										<!-- Text input-->

										<div class="control-group">
										
										   
											<label class="control-label" for="firstName"><fmt:message key="home.label.firstName" /> :</label>
											<div class="controls">
												<input id="fName" name="fName" class="form-control"
													type="text" placeholder="First name" class="input-large"
													required="">
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="lastName"><fmt:message key="home.label.lastName" /> :</label>
											<div class="controls">
												<input id="lName" name="lName"  class="form-control"
													type="text" placeholder="Last Nmae" class="input-large"
													required="">
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="email"><fmt:message key="home.label.email" /> :</label>
											<div class="controls">
												<input id="email" name="email" class="form-control"
													type="text" placeholder="JoeSixpack@sixpacksrus.com"
													class="input-large" required="">
											</div>
										</div>

										<!-- Text input-->
										<div class="control-group">
											<label class="control-label" for="userName"><fmt:message key="home.label.userName" /> :</label>
											<div class="controls">
												<input id="userName" name="userName" class="form-control"
													type="text" placeholder="JoeSixpack" class="input-large"
													required="">
											</div>
										</div>

										<!-- Password input-->
										<div class="control-group">
											<label class="control-label" for="password"><fmt:message key="home.label.password" /> :</label>
											<div class="controls">
												<input id="password" name="password" class="form-control"
													type="password" placeholder="********" class="input-large"
													required=""> <em>1-8 Characters</em>
											</div>
										</div>

										<!-- Text input-->
										<div class="control-group">
											<label class="control-label" for="reenterpassword">R<fmt:message key="home.label.reenterpassword" /> :</label>
											<div class="controls">
												<input id="reenterpassword" class="form-control"
													name="reenterpassword" type="password"
													placeholder="********" class="input-large" required="">
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="streetAddress"><fmt:message key="home.label.streetAddress" /> :</label>
											<div class="controls">
												<input id="streetAddress" class="form-control"
													name="streetAddress" type="text" placeholder="add 1"
													class="input-large" required="">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="localityAddress"><fmt:message key="home.label.localityAddress" /> :</label>
											<div class="controls">
												<input id="localityAddress" class="form-control"
													name="localityAddress" type="text" placeholder="Add 2"
													class="input-large" required="">
											</div>
										</div>
										<!-- Multiple Radios (inline) -->
										<br>
										<div class="control-group">
											<label class="control-label" for="humancheck"><fmt:message key="home.label.humancheck" /> :</label>
											<div class="controls">
												<label class="radio inline" for="humancheck-0"> <input
													type="radio" name="humancheck" id="humancheck-0"
													value="robot" checked="checked">I'm a Robot
												</label> <label class="radio inline" for="humancheck-1"> <input
													type="radio" name="humancheck" id="humancheck-1"
													value="human">I'm Human
												</label>
											</div>
										</div>
										<input type="hidden" name="action" value="SignUp">
										<!-- Button -->
										<div class="control-group">
											<label class="control-label" for="confirmsignup"></label>
											<div class="controls">
											<fmt:message key="home.button.submit" var="buttonValue" />
												<button type="submit" id="confirmsignup"
													name="confirmsignup" class="btn btn-success"  value="${buttonValue}"></button>
											</div>
										</div>
									</fieldset>
								</form>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<center>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</center>
					</div>
				</div>
			</div>
		</div>

		<c:if test="${ inserted=='true'}">
<center><h3>registered</h3></center>
			</c:if>

		<c:if test="${not empty exceptionOccured}">
<fmt:message
										key="register.user.msg" var="msg" />
									<center><h3>${msg}</h3></center>
			</c:if>
			
			<c:if test="${not empty humanityCheck}">
			
<fmt:message
										key="register.user.humanMsg" var="humanMsg" />
									<center><h3>${humanMsg}</h3></center>
			</c:if>
			
			
			<c:if test="${not empty passwordMatch}">
			
<fmt:message
										key="register.user.passMsg" var="passMsg" />
									<center><h3>${passMsg}</h3></center>
			</c:if>
			
			
		
		<br>
		<hr class="prettyline">
	</div>
</body>
</html>