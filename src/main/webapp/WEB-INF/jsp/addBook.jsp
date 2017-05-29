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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>Insert title here</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/style.css">
</head>

<body onkeydown="return (event.keyCode != 116)">

<%-- 	<form action="ControllerServlet" method="get">
		<input type="hidden" name="previousCommand"
			value="${sessionScope.action }" /> <input type="hidden"
			name="action" value="languageChanged" /> <input type="hidden"
			name="userName" value="${sessionScope.userName }" /> <input
			type="hidden" name="password" value="${sessionScope.password }" /> <input
			type="hidden" name="typeOfBook" value="${sessionScope.typeOfBook }" />
		<input type="hidden" name="bookId" value="${bookId }" /> <select
			id="language" name="language" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
			<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
		</select>
	</form> --%>

<form name="logout" action="ControllerServlet" method="get">



				<div align="right" style="margin-top: -10px;">
					<fmt:message key="user.button.submit" var="buttonValue" />
					<input type="submit" value="${buttonValue}">

				</div>
				<input type="hidden" name="action" value="Logout" />


			</form>
			
			
	<div class="container">
		<section id="content">
		<form action="ControllerServlet" method="post">
			<input type="hidden" name="action" value="addBook" />

			<c:if
				test="${ sessionScope.bookTypeToBeAdded!=null  and sessionScope.bookTypeToBeAdded=='PB'}">
				<h1>
					<fmt:message key="admin.add.appPBook" var="appPBook" />
					${appPBook}
				</h1>
				<input type="hidden" name="typeOfBookAdded" value="PB" />
			</c:if>
			<c:if
				test="${ sessionScope.bookTypeToBeAdded!=null  and sessionScope.bookTypeToBeAdded=='EB'}">
				<input type="hidden" name="typeOfBookAdded" value="EB" />
				<h1>
					<fmt:message key="admin.add.appEBook" var="appEBook" />
					${appEBook}
				</h1>
			</c:if>
			<div>
				<input type="text" placeholder="Title" required id="title"
					name="title" />
			</div>
			<div>

				<textarea rows="2" cols="35" placeholder="Description..."
					name="description" style="padding: 15px 10px 15px 40px;"></textarea>
			</div>
			<br>

			<div>
				<input type="text" placeholder="Author" required id="author"
					name="author" />
			</div>

			<div>
				<input type="text" placeholder="Quantity" required id="quantity"
					name="quantity" />
			</div>
			<div class="numberField">
				<input class="numberField"
					style="transition: all 0.5s ease; color: #777; font: 13px Helvetica, Arial, sans-serif; margin: 0 0 10px; padding: 15px 10px 15px 40px; width: 80%;"
					type="number" placeholder="Price" required id="price"
					name="price" />
			</div>

			<div>


				<c:if
					test="${ sessionScope.bookTypeToBeAdded!=null  and sessionScope.bookTypeToBeAdded=='PB'}">
					<select name="item"
						style="width: 290px; height: 45px; padding: 15px 10px 15px 40px">
						<option value="2"><fmt:message key="admin.add.hardCover"
								var="hardCover" /> ${hardCover}
						</option>
						<option value="1"><fmt:message key="admin.add.softCover"
								var="softCover" /> ${softCover}
						</option>

					</select>
				</c:if>

				<c:if
					test="${ sessionScope.bookTypeToBeAdded!=null  and sessionScope.bookTypeToBeAdded=='EB'}">
					<select name="item"
						style="width: 290px; height: 45px; padding: 15px 10px 15px 40px">
						<option value="1"><fmt:message key="admin.add.freeTrial"
								var="freeTrial" /> ${freeTrial}
						</option>
						<option value="2"><fmt:message key="admin.add.paid"
								var="paid" /> ${paid}
						</option>





					</select>
				</c:if>


			</div>
			<div>

				<fmt:message key="admin.radio.addBook" var="addBook" />
				<input type="submit" value="${addBook}">


			</div>
		</form>
		<!-- form --> </section>
		<!-- content -->
	</div>
	<!-- container -->
</body>

<script src="js/index.js"></script>



<a href="ControllerServlet?action=gettingBook&user=admin&typeOfBook=ALL">
	<button class="button button1">
		<fmt:message key="user.table.goBack" var="goBack" />
		<h3 style="color:blue;">${goBack}</h3>
	</button>
</a>

</body>
</html>
