<%@ page language="java" contentType="text/html;UTF-8"
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
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	"${sessionScope.action}
	<form action="HomeServlet" method="get">
	
  <input type="hidden" name="getBook" value="${sessionScope.action }" />
   <input type="hidden" name="typeOfBook" value="${sessionScope.bookSelectedType }" />

	
		<select id="language" name="language" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
			<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
		</select>
	</form>
	<c:choose>
		<c:when
			test="${sessionScope.userInfo != null && sessionScope.userRole!=null}">
			<div align="right">
				<form name="logout" action="HomeServlet" method="post">






					<fmt:message key="user.button.submit" var="buttonValue" />
					<input type="submit" value="${buttonValue}">
			</div>

			<fmt:message key="user.label.userMessage" var="userMessage" />
			<div style="font-size: 150%">${userMessage}</div>
			<br>


			<c:out value="${userInfo.name}"></c:out>
			<br>

			<input type="hidden" name="action" value="Logout">


			</form>
			<form name="getBook" action="HomeServlet" method="get">
				<input type="hidden" name="getBook" value="gettingBook">
				<TABLE style="background-color: #ECE5B6;" WIDTH="50%">
					<tr width="100%">
					
					</tr>
					<tr>
						<td><fmt:message key="user.book.select" var="selectOneType" /><input
							type="submit" value="${selectOneType}"></td>

						<td><fmt:message key="user.radio.eb" var="electronicBook" /><input
							type="radio" name="typeOfBook" value="EB" >
							${electronicBook}</td>
						<td><fmt:message key="user.radio.pb" var="paperBook" /><input
							type="radio" name="typeOfBook" value="PB">${paperBook}</td>

					</tr>
					<tr>
						<td></td>
						<td></td>

						<td><fmt:message key="user.radio.submit" var="radioValue" /><input
							type="submit" value="${radioValue}"></td>
					</tr>
				</TABLE>

				<c:if test="${ requestScope.bookInfo!= null}">





					<div align="center">
						<table border="1" cellpadding="5">
							<caption>
								<h2>
									<fmt:message key="user.header.msg" var="msg" />
									${msg}
								</h2>
							</caption>
							

							<c:forEach var="bookInfo" items="${bookInfo}">
							
								<c:choose>
									<c:when test="${ not empty bookInfo.title}">

										<tr>


											<td><h1><a
												href="HomeServlet?bookId=${ bookInfo.bookId}&getSelectedBook=gettingSelectedBook"><c:out
														value="${ bookInfo.bookId}" /></a></h1></td>
											<td><c:out value="${ bookInfo.title}" /></td>
											<td><c:out value="${bookInfo.description}" /></td>
											<td><c:out value="${bookInfo.author}" /></td>
											<td><c:out value="${bookInfo.price}" /></td>
											<td><c:out value="${bookInfo.quantity}" /></td>
										</tr>
									</c:when>

									<c:otherwise>
							
								</c:otherwise>
								</c:choose>
							</c:forEach>

						</table>

					</div>

				</c:if>

			</form>

		</c:when>
		<c:otherwise>
			<c:redirect url="/loginPage.jsp" />
		</c:otherwise>
	</c:choose>


</body>
</html>