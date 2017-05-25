<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.epam.i18n.text" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>ADMIN</title>
<style>

</style>
</head>
<body onkeydown="return (event.keyCode != 116)">

	<form action="ControllerServlet" method="get">
		<input type="hidden" name="userName" value="${sessionScope.userName }" />
		<input type="hidden" name="password" value="${sessionScope.password }" />
		<input type="hidden" name="previousCommand" value="${sessionScope.action }" />
		<input type="hidden" name="action" value="languageChanged" />
		<input type="hidden" name="typeOfBook"
			value="${sessionScope.typeOfBook }" /> <select id="language"
			name="language" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
			<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
		</select>
	</form>


	<input
					type="hidden" name="user" value="admin">


	<c:choose>

		<c:when
			test="${sessionScope.userInfo != null && sessionScope.adminRole!=null}">
			<form name="logout" action="ControllerServlet" method="get">


				<div align="right">
					<fmt:message key="user.label.message" var="message" />
					<div style="font-size: 100%">${message}<c:out
							value="${userInfo.firstName}"></c:out>


						<fmt:message key="user.button.submit" var="buttonValue" />
						<input type="submit" value="${buttonValue}">





					</div>
					<br> <input type="hidden" name="action" value="Logout" />
				</div>

			</form>
			<div class="input-group" id="boot-search-box">
				<input type="text" class="form-control"
					placeholder="Click on Arrow..." name="title" />
				<div class="input-group-btn">
					<div class="btn-group" role="group">
						<div class="dropdown dropdown-lg">
							<button type="button" class="btn btn-default dropdown-toggle"
								data-toggle="dropdown" aria-expanded="false">
								<span class="caret"></span>
							</button>
							<div class="dropdown-menu dropdown-menu-right" role="menu">
								<form name="search" action="ControllerServlet" method="get"
									class="form-horizontal" role="form">
									<div class="form-group">
										<label for="filter"><fmt:message
												key="user.radio.narrowSearch" var="narrowSearch" />${narrowSearch}:</label>

										<select name="item" class="form-control">
											<option value="PB" selected><fmt:message
													key="user.radio.pb" var="paperBook" /> ${paperBook}
											</option>
											<option value="EB"><fmt:message key="user.radio.eb"
													var="electronicBook" />${electronicBook}</option>

										</select>


									</div>
									<div class="form-group">
										<label for="contain"><fmt:message
												key="user.label.title" var="title" />${title}:</label> <input
											class="form-control" type="text" name="title" />
									</div>
									<div class="form-group">
										<label for="contain"><fmt:message
												key="user.label.author" var="author" />${author}:</label> <input
											class="form-control" type="text" name="author" />
									</div>


									<div class="form-group">
										<label for="contain"><fmt:message
												key="user.label.desc" var="desc" />${desc}:</label> <input
											class="form-control" type="text" name="description" />
									</div>





									<div class="form-group">
										<label for="password1" class="col-sm-3 control-label"><fmt:message
												key="user.label.defaultPrice" var="defaultPrice" />${defaultPrice}:</label><br>
										<br>
										<div class="col-sm-3">
											<input type="number" class="form-control" id="minPrice"
												name="minPrice" placeholder="Min"> <br /> <br /> <input
												type="number" class="form-control" id="maxPrice"
												name="maxPrice" placeholder="Max">
										</div>
									</div>
									<br>
									<!-- <br /> <br /> <br /> <br /> -->
									<button type="submit" class="btn btn-primary btn-block">
										Search :: <span class="glyphicon glyphicon-search"
											aria-hidden="true"></span>
									</button>
									<input type="hidden" name="action" value="search" /> <input
										type="hidden" name="typeOfBook" value="SearchBook">
								</form>
							</div>
						</div>
						<button type="button" class="btn btn-success ">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						</button>
					</div>
				</div>
			</div>


			<form name="viewAllBooks" action="ControllerServlet" method="get">
				<fmt:message key="add.button.submit" var="addValue" />

				<input type="hidden" name="action" value="gettingBook"> <input
					type="hidden" name="user" value="admin"> <input
					type="hidden" name="typeOfBook" value="ALL"> <input
					class="btn btn-lg btn-primary btn-block" type="submit"
					value="${addValue}"></input>
			</form>


			<form name="viewAllBooks" action="ControllerServlet" method="get">
				<fmt:message key="add.button.PBook" var="addPBook" />
				
				<input type="hidden" name="action" value="gettingPage"> 
				<input type="hidden" name="bookTypeToBeAdded" value="PB">
				
				<input
					class="btn btn-lg btn-primary btn-block" type="submit"
					value="${addPBook}"></input>

			</form>

			<form name="viewAllBooks" action="ControllerServlet" method="get">
			
				<fmt:message key="add.button.EBook" var="addEBook" />
				<input type="hidden" name="action" value="gettingPage">
						<input type="hidden" name="bookTypeToBeAdded" value="EB"> <input
					class="btn btn-lg btn-primary btn-block" type="submit"
					value="${addEBook}"></input>
			</form>

			
			
			<form name="getBook" action="ControllerServlet" method="get">
		
				<input type="hidden" name="action" value="gettingBook">
				<div style="background-color: #ECE5C6; text-align: center">


					<fmt:message key="user.book.editOIption" var="editOIption" />
					<h4>${editOIption}:</h4>


					<fmt:message key="user.radio.eb" var="electronicBook" />
					<input type="radio" name="typeOfBook" value="EB" checked> <label
						for="radio1">${electronicBook}</label>

					<fmt:message key="user.radio.pb" var="paperBook" />
					<input type="radio" name="typeOfBook" value="PB"><label
						for="radio1">${paperBook}</label>

					

					<fmt:message key="user.radio.submit" var="radioValue" />
					<input type="submit" value="${radioValue}">

				</div>
				
				<c:if test="${not empty bookInfo }">
					<c:if test="${ requestScope.bookInfo!= null && isAll!=null}">
				
					<div align="center">
						<TABLE>

							<caption>
								<h2>
									<fmt:message key="user.header.msg" var="msg" />
									${msg}
								</h2>
							</caption>
							<tr>
								<fmt:message key="user.table.id" var="idBook" />
								<th>${idBook}</th>
								<fmt:message key="user.table.title" var="titleBook" />
								<th>${titleBook}</th>
								<fmt:message key="user.table.desc" var="desc" />
								<th>${desc}</th>
								<fmt:message key="user.table.author" var="author" />
								<th>${author}</th>
								<c:if test="${sessionScope.typeOfBook=='EB'}">
								<fmt:message key="user.table.editEBook" var="editEBook" />
							<th>${editEBook}</th></c:if>
							<c:if test="${sessionScope.typeOfBook=='PB'}">
							<fmt:message key="user.table.editPBook" var="editPBook" />
							<th>${editPBook}</th></c:if>
								

							</tr>

							
<c:forEach var="bookInfo" items="${bookInfo}">
<c:forEach var="bookInfo" items="${bookInfo}">

		<c:choose>
									<c:when test="${ not empty bookInfo.title}">

										<tr>


											<td><a
												href="ControllerServlet?bookId=${ bookInfo.bookId}&action=gettingSelectedBook"
												class="ClickCell"><c:out value="${ bookInfo.bookId}" /></a></td>
											<td><c:out value="${ bookInfo.title}" /></td>
											<td><c:out value="${bookInfo.description}" /></td>
											<td><c:out value="${bookInfo.author}" /></td>
									</c:when>

									<c:otherwise>

									</c:otherwise>
								</c:choose>
							</c:forEach>
	</c:forEach>
						</TABLE>

					</div>
					</c:if>
					</c:if>
					<c:if test="${not empty bookInfo }">
					<c:if test="${ requestScope.bookInfo!= null && isAll==null}">





					<div align="center">
						<TABLE>

							<caption>
								<h2>
									<fmt:message key="user.header.msg" var="msg" />
									${msg}
								</h2>
							</caption>
							<tr>
								<fmt:message key="user.table.id" var="idBook" />
								<th>${idBook}</th>
								<fmt:message key="user.table.title" var="titleBook" />
								<th>${titleBook}</th>
								<fmt:message key="user.table.desc" var="desc" />
								<th>${desc}</th>
								<fmt:message key="user.table.author" var="author" />
								<th>${author}</th>
								
								<c:if test="${sessionScope.typeOfBook=='EB'}">
								<fmt:message key="user.table.editEBook" var="editEBook" />
							<th>${editEBook}</th></c:if>
							<c:if test="${sessionScope.typeOfBook=='PB'}">
							<fmt:message key="user.table.editPBook" var="editPBook" />
							<th>${editPBook}</th></c:if>

							</tr>

							
<c:forEach var="bookInfo" items="${bookInfo}">

		<c:choose>
									<c:when test="${ not empty bookInfo.title}">

										<tr>


											<td><a
												href="ControllerServlet?bookId=${ bookInfo.bookId}&action=gettingSelectedBook"
												class="ClickCell"><c:out value="${ bookInfo.bookId}" /></a></td>
											<td><c:out value="${ bookInfo.title}" /></td>
											<td><c:out value="${bookInfo.description}" /></td>
											<td><c:out value="${bookInfo.author}" /></td>
											
											<c:if test="${sessionScope.typeOfBook=='PB'}">
										
										<td><a
										href="ControllerServlet?bookId=${ bookInfo.bookId}&action=editBook&bookTypeToBeEdited=PB&bookId=${bookInfo.bookId }"
										class="ClickCell"><fmt:message key="user.table.edit" var="edit" /><c:out value="${edit}" /></a></td>
				
										</c:if>
										<c:if test="${sessionScope.typeOfBook=='EB'}">
										<td><a
										href="ControllerServlet?bookId=${ bookInfo.bookId}&action=editBook&bookTypeToBeEdited=EB"
										class="ClickCell"><fmt:message key="user.table.edit" var="edit" /><c:out value="${edit}" /></a></td>
					
										</c:if>
										</tr>
									</c:when>

									<c:otherwise>

									</c:otherwise>
								</c:choose>
							</c:forEach>

						</TABLE>

					</div>

				</c:if>
				</c:if>
					 
			</form>
		</c:when>
		<c:otherwise>
			</c:otherwise>
	</c:choose>
<c:if test="${ not empty  emptySearch}">
	<div align="center">
		<div class="alert alert-danger" style="
    margin-top: 500px;
    width: 474px" align="center">
			<strong> <fmt:message key="login.error.searchEmpty"
					var="searchEmpty" />
				${searchEmpty}</strong></div>

		</div>

	</c:if>
</body>

</html>