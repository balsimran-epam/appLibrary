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
	<form name="HomeServlet" method="get">
	
   <input type="hidden" name="getSelectedBook" value="${sessionScope.action }" />
  <input type="hidden" name="bookId" value="${bookId }" />
		<select id="language" name="language" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
			<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
		</select>
	</form>
	<c:choose>
		<c:when
			test="${requestScope.selectedBookInfo != null && sessionScope.userRole!=null}">
			<div align="right">
				<form name="logout" action="HomeServlet" method="post">






					<fmt:message key="user.button.submit" var="buttonValue" />
					<input type="submit" value="${buttonValue}">
			</div>

			

			<input type="hidden" name="action" value="Logout">


			</form>
			

				<c:if test="${ requestScope.selectedBookInfo!= null}">



	<c:forEach var="bookInfo" items="${selectedBookInfo}">

					<div align="center">
						<table border="1" cellpadding="5">
							<caption>
								<h2><fmt:message key="user.header.msg" var="msg" />
								${msg}</h2>
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
						<c:if
         test="${bookInfo['class'] == 'class com.epam.library.domain.ElectronicBook' }">
								<fmt:message key="user.table.version" var="version" />
								<th>${version}</th></c:if>
								
        	<c:if
         test="${bookInfo['class'] == 'class com.epam.library.domain.PaperBook' }">
									<fmt:message key="user.table.cover" var="cover" />
								<th>${cover}</th></c:if>
							</tr>
							
						
						<c:choose>
						<c:when test="${ not empty bookInfo.title}">

								<tr>
								
								
							<td><c:out value="${ bookInfo.bookId}" /></td>
									
							<td><c:out value="${ bookInfo.title}" /></td>
							
									<td><c:out value="${bookInfo.description}" /></td>
									<td><c:out value="${bookInfo.author}" /></td>
									<td><c:out value="${bookInfo.price}" /></td>
									<td><c:out value="${bookInfo.quantity}" /></td>
								
									<c:if
         test="${bookInfo['class'] == 'class com.epam.library.domain.ElectronicBook' }">
        <td><c:out value="${bookInfo.version}" /></td>
        </c:if>
        
        				<c:if
         test="${bookInfo['class'] == 'class com.epam.library.domain.PaperBook' }">
        <td>	<c:out value="${bookInfo.typeOfCover}" /></td></c:if>
        
								</tr>
								</c:when>
									
								<c:otherwise>
								sorry
								</c:otherwise>
								</c:choose>
							
						
						</table>

					</div>
	</c:forEach>
				</c:if>

			</form>

		</c:when>
		<c:otherwise>
			<c:redirect url="/loginPage.jsp" />
		</c:otherwise>
	</c:choose>


</body>
</html>