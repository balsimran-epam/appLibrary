<%@ page language="java" contentType="text/html;UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.epam.i18n.text" />
<!DOCTYPE html >
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="./css/styles.css" rel="stylesheet" type="text/css" />
<title>User</title>

</head>
<body onkeydown="return (event.keyCode != 116)">
<c:if test="${sessionScope!=null }">
	<form action="ControllerServlet" method="get">
		<input type="hidden" name="userName" value="${sessionScope.userName }" />
		<input type="hidden" name="previousCommand"
			value="${sessionScope.action }" /> <input type="hidden"
			name="action" value="languageChanged" /><input type="hidden"
			name="userName" value="${sessionScope.userName }" /> <input
			type="hidden" name="password" value="${sessionScope.password }" /> <input
			type="hidden" name="typeOfBook" value="${sessionScope.typeOfBook }" />
		<select id="language" name="language" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
			<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
		</select>
	</form>
	<c:choose>
		<c:when
			test="${sessionScope.userInfo != null && sessionScope.userRole!=null}">

			<form name="logout" action="ControllerServlet" method="get">



				<div align="right" style="margin-top: -10px;">
					<fmt:message key="user.button.submit" var="buttonValue" />
					<input type="submit" value="${buttonValue}">

				</div>
				<input type="hidden" name="action" value="Logout" />


			</form>

			<form name="logout" action="ControllerServlet" method="post">

				<div align="center">
					<fmt:message key="user.label.message" var="message" />
					<h1 style="font-style: oblique;">${message}<c:out
							value="${userInfo.firstName}"></c:out>


						<a
							href="ControllerServlet?userId=${userInfo.userId}&action=editUserForm"><fmt:message
								key="user.label.editMessage" var="editMessage" />
							${editMessage}</a>

					</h1>

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
			<form name="getBook" action="ControllerServlet" method="get">
				<input type="hidden" name="action" value="gettingBook">
				<div style="background-color: #ECE5C6; text-align: center">


					<fmt:message key="user.book.select" var="selectOneType" />
					<h4>${selectOneType}:</h4>


					<fmt:message key="user.radio.eb" var="electronicBook" />
					<input type="radio" name="typeOfBook" value="EB"> <label
						for="radio1">${electronicBook}</label>

					<fmt:message key="user.radio.pb" var="paperBook" />
					<input type="radio" name="typeOfBook" value="PB"><label
						for="radio1">${paperBook}</label>

					<fmt:message key="user.radio.all" var="allBook" />
					<input type="radio" name="typeOfBook" value="ALL" checked><label
						for="radio1">${allBook}</label> <br>

					<fmt:message key="user.radio.submit" var="radioValue" />
					<input type="submit" value="${radioValue}">

				</div>
				<c:if test="${not empty bookInfo }">

					<c:if test="${ requestScope.bookInfo!= null && isAll!=null}">

						<div align="center">
							<h2>
								<fmt:message key="user.header.msg" var="msg" />
								${msg}
							</h2>

							<TABLE>

								<tr>
									<fmt:message key="user.table.id" var="idBook" />
									<th>${idBook}</th>
									<fmt:message key="user.table.title" var="titleBook" />
									<th>${titleBook}</th>
									<fmt:message key="user.table.desc" var="desc" />
									<th>${desc}</th>
									<fmt:message key="user.table.author" var="author" />
									<th>${author}</th>

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
							<h2>
								<fmt:message key="user.header.msg" var="msg" />
								${msg}
							</h2>
							<TABLE>


								<tr>
									<fmt:message key="user.table.id" var="idBook" />
									<th>${idBook}</th>
									<fmt:message key="user.table.title" var="titleBook" />
									<th>${titleBook}</th>
									<fmt:message key="user.table.desc" var="desc" />
									<th>${desc}</th>
									<fmt:message key="user.table.author" var="author" />
									<th>${author}</th>

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
			<c:redirect url="/loginPage.jsp" />
		</c:otherwise>
	</c:choose>


	<c:if test="${ not empty  emptySearch}">
		<div align="center">
			<div class="alert alert-danger"
				style="margin-top: 500px; width: 474px" align="center">
				<strong> <fmt:message key="login.error.searchEmpty"
						var="searchEmpty" /> ${searchEmpty}
				</strong>
			</div>

		</div>

	</c:if>




	<c:if test="${ isUpdatedUser=='true'}">
		<div align="center">
			<div class="alert alert-success"
				style="margin-top: 350px; width: 474px" align="center">
				<strong><fmt:message key="login.error.success"
						var="success" /> ${success}</strong>
				<fmt:message key="login.error.updatedUser" var="updatedUser" />
				${updatedUser}
			</div>

		</div>
	</c:if>

	<c:if test="${ isUpdatedUser=='false' }">
		<div align="center">
			<div class="alert alert-success"
				style="margin-top: 350px; width: 474px" align="center">
				<strong><fmt:message key="login.error.fail" var="fail" />
					${fail}</strong>
				<fmt:message key="login.error.addTranslation" var="addTranslation" />
				${addTranslation}
			</div>
			<h1>
				<a
					href="ControllerServlet?userId=${userInfo.userId}&action=translateUser"><fmt:message
						key="user.label.translate" var="translate" /> ${translate}</a>
			</h1>
		</div>

	</c:if>
	</c:if>
	
<c:if test="${sessionScope==null }">
 <c:redirect url = "/loginPage.jsp"/>
</c:if>
</body>
</html>