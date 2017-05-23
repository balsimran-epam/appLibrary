<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.epam.i18n.text" />
<!DOCTYPE html >
<html lang="${language}">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>

<title>Insert title here</title>
<style>

body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}

.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  height: auto;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
mainav {
	
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
			<!-- ################################################################################################ -->
			<ul class="clear">
				<li class="active"><a href="home.jsp"><fmt:message
										key="register.user.home" var="home" />
									${home}</a></li>

				</li>
				

			</ul>
			<!-- ################################################################################################ -->
		</nav>
	</div>
	
	
	   <form class="form-signin" name="login" action="ControllerServlet" method="post">
        <h2 class="form-signin-heading"><fmt:message key="login.header.name" var="headerName" />
				<div style="color:#0000FF "blue";">${headerName}</h2>
        <label for="username"><fmt:message key="login.label.username" /> : </label>
        <input type="text" name="userName" class="form-control" placeholder="Email address" required autofocus><label for="message"><fmt:message key="login.label.message" /></small></label><br>
        <label for="password" ><fmt:message key="login.label.password" />:</label>
        <input type="password" name="password" class="form-control" placeholder="Password" required>
        <input type="hidden" name="action" value="Login">
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <fmt:message key="login.button.submit" var="buttonValue" />
        <input class="btn btn-lg btn-primary btn-block" type="submit" value="${buttonValue}"></input>
      </form>
      
      


	
	
		<c:if test="${ errorMessage!=null}">
<fmt:message key="login.error.message" var="errorMessage" />
				<div style="color: #FF0000;">${errorMessage}</div>
			</c:if>
	

</body>
</html>