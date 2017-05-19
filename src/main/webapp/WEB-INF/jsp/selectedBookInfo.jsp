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
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style>
.button {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 6px 15px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
}

.button1 {
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
}
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 50%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</style>
</head>
<body>
	<form name="ControllerServlet" method="get">
	
   <input type="hidden" name="action" value="${action }" />
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
				<form name="logout" action="ControllerServlet" method="get">






					<fmt:message key="user.button.submit" var="buttonValue" />
					<input type="submit" value="${buttonValue}">
			</div>

			

			<input type="hidden" name="action" value="Logout">


			</form>
			

				<c:if test="${ requestScope.selectedBookInfo!= null}">

<div align="center">
						<table border="1" cellpadding="5">

	<c:forEach var="bookInfo" items="${selectedBookInfo}">




					
							<caption>
							<c:if
        test="${(bookInfo['class'] == 'class com.epam.library.domain.ElectronicBook') }">
								<h2><fmt:message key="user.header.Emsg" var="Emsg" />
								${Emsg}</h2></c:if>				
								<c:if
         test="${(bookInfo['class'] == 'class com.epam.library.domain.PaperBook')  }"><h2><fmt:message key="user.header.Pmsg" var="Pmsg" />
								${Pmsg}</h2></c:if>
									<c:if
         test="${(bookInfo['class'] == 'class com.epam.library.domain.Book')  }"><h2><fmt:message key="user.header.Amsg" var="Amsg" />
								${Amsg}</h2></c:if>
							</caption>
							<tr>
								<fmt:message key="user.table.id" var="idBook" />
								<td>${idBook}</td>		<td><c:out value="${ bookInfo.bookId}" /></td></tr>
								<tr><fmt:message key="user.table.title" var="titleBook" />
								<td>${titleBook}</td><td><c:out value="${ bookInfo.title}" /></td></tr>
								<tr><fmt:message key="user.table.desc" var="desc" />
								<td>${desc}</td>	<td><c:out value="${bookInfo.description}" /></td>
								<fmt:message key="user.table.author" var="author" /></tr>
								<tr><td>${author}</td><td><c:out value="${bookInfo.author}" /></td>
								<fmt:message key="user.table.price" var="price" /></tr>
								<tr><td>${price}</td><td><c:out value="${bookInfo.price}" /></td>
								<fmt:message key="user.table.quantity" var="quantity" />
								<tr><td>${quantity}</td><td><c:out value="${bookInfo.quantity}" /></td></tr>
						<tr>				<c:if
        test="${(bookInfo['class'] == 'class com.epam.library.domain.ElectronicBook')  }">
								<fmt:message key="user.table.version" var="version" />
								<td>${version}</td>  <td><c:out value="${bookInfo.version}" /></td></c:if></tr>
								
        	<tr>	<c:if
         test="${(bookInfo['class'] == 'class com.epam.library.domain.Paper')  }">
									<fmt:message key="user.table.cover" var="cover" />
								<td>${cover}</td> <td>	<c:out value="${bookInfo.typeOfCover}" /></td></c:if>
							</tr>
							
					
							</c:forEach>
						
						</table>

					</div>
	
				</c:if>

	<tr>
			</form>
<a href="ControllerServlet?action=gettingBook">
<button class="button button1" align="center"><fmt:message key="user.table.goBack" var="goBack" />
								${goBack}</button></a>
		</c:when>
		<c:otherwise>
			<c:redirect url="/loginPage.jsp" />
		</c:otherwise>
	</c:choose>


</body>
</html>