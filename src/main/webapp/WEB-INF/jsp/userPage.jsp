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
<style>

</style>
</head>
<body>

	
	<form action="HomeServlet" method="get">
	<input type="hidden" name="userName" value="${sessionScope.userName }" />
   <input type="hidden" name="password" value="${sessionScope.password }" />
    <input type="hidden" name="action" value="${sessionScope.action}" />
   <input type="hidden" name="typeOfBook" value="${sessionScope.typeOfBook }" />

	
		<select id="language" name="language" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
			<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
		</select>
	</form>
	<c:choose>
		<c:when
			test="${sessionScope.userInfo != null && sessionScope.userRole!=null}">
			
				<form name="logout" action="HomeServlet" method="post">


<div align="right">



					<fmt:message key="user.button.submit" var="buttonValue" />
					<input type="submit" value="${buttonValue}">
			</div>

			<fmt:message key="user.label.userMessage" var="userMessage" />
			<div style="font-size: 150%">${userMessage}</div>
			<br>


			<c:out value="${userInfo.name}"></c:out>
			<br>

			<input type="hidden" name="action" value="Logout"/>


			</form>
			<form name="getBook" action="HomeServlet" method="get">
				<input type="hidden" name="action" value="gettingBook"> 
				<TABLE style="background-color: #ECE5B6;" WIDTH="50%">
					
					<tr>
						<td><fmt:message key="user.book.select" var="selectOneType" /><div style="font-size: 150%">${selectOneType}</div>

						<td><fmt:message key="user.radio.eb" var="electronicBook" /><input
							type="radio" name="typeOfBook" value="EB" checked>
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
<c:if test="${ exceptionOccured !=null}">
		Exception Occured: ${exceptionOccured}</c:if>
	<tr>
				<c:if test="${ requestScope.bookInfo!= null}">





					<div align="center">
						<table border="1" cellpadding="5" align="center" WIDTH="50%">
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
								<fmt:message key="user.table.price" var="price" />
								<th>${price}</th>
								<fmt:message key="user.table.quantity" var="quantity" />
								<th>${quantity}</th>
								</tr>

							<c:forEach var="bookInfo" items="${bookInfo}">
							
								<c:choose>
									<c:when test="${ not empty bookInfo.title}">

										<tr>


											<td><a
												href="HomeServlet?bookId=${ bookInfo.bookId}&action=gettingSelectedBook" class="ClickCell"><c:out
														value="${ bookInfo.bookId}" /></a></td>
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