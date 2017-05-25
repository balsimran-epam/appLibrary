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

 <link href="css/styles.css" rel="stylesheet" type="text/css" />

<script>
function validateForm() {
    var x = document.forms["myForm"]["email"].value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
        alert("Not a valid e-mail address");
        return false;
    }
}
</script>

</head>
<body id="top" onkeydown="return (event.keyCode != 116)">


<div class="wrapper row0">
	<form action="home.jsp" >
	
		<select id="language" name="language" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
			<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
		</select>
	</form>

	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	
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
				<div class="modal-content" style="
    width: 445px;">
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
								<p><fmt:message
										key="register.user.whyMsg" var="whyMsg" />
									${whyMsg}</p>
								<p></p>
								
							</div>

							<div class="tab-pane fade active in" id="signup">
								<form name="myForm" class="form-horizontal" action="ControllerServlet"
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
											<label class="control-label" for="reenterpassword"><fmt:message key="home.label.reenterpassword" /> :</label>
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
													name="streetAddress" type="text" placeholder="Street Address"
													class="input-large" required="">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="localityAddress"><fmt:message key="home.label.localityAddress" /> :</label>
											<div class="controls">
												<input id="localityAddress" class="form-control"
													name="localityAddress" type="text" placeholder="Locality Address"
													class="input-large" required="">
											</div>
										</div>
										<!-- Multiple Radios (inline) -->
										<br>
										<div align="center">
										<div class="control-group">
											<label class="control-label" for="humancheck"><fmt:message key="home.label.humancheck" /> :</label>
											<div class="controls">
												<label class="radio inline" for="humancheck-0"> <input
													type="radio" name="humancheck" id="humancheck-0"
													value="robot" checked="checked"><fmt:message
										key="register.user.robot" var="robot" />
									${robot}
												</label> <label class="radio inline" for="humancheck-1"> <input
													type="radio" name="humancheck" id="humancheck-1"
													value="human"><fmt:message
										key="register.user.human" var="human" />
									${human}
												</label>
											</div>
										</div>
										</div>
										<input type="hidden" name="action" value="SignUp">
										<!-- Button -->
										<div class="control-group">
											<label class="control-label" for="confirmsignup"></label>
											<div class="controls">
											<fmt:message key="home.button.submit" var="buttonValue" />
												<button type="submit" id="confirmsignup"
													name="confirmsignup" class="btn btn-success" style="
    width: 76px;
    height: 36px;">${buttonValue}</button>
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
								data-dismiss="modal"><fmt:message
										key="register.user.close" var="close" />
									${close}</button>
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
			
			<c:if test="${not empty humancheck}">
			
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