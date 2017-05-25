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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>Insert title here</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />


</head>
<body onkeydown="return (event.keyCode != 116)">

	<form name="ControllerServlet" method="get">

		<input type="hidden" name="previousCommand"
			value="${sessionScope.action }" /> <input type="hidden"
			name="action" value="languageChanged" /> <input type="hidden"
			name="bookId" value="${bookId }" /> <select id="language"
			name="language" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="hi" ${language == 'hi' ? 'selected' : ''}>Hindi</option>
			<option value="be" ${language == 'be' ? 'selected' : ''}>Belarusian</option>
		</select>
	</form>

	<div align="right">
		<form name="logout" action="ControllerServlet" method="get">






			<fmt:message key="user.button.submit" var="buttonValue" />
			<input type="submit" value="${buttonValue}">
	</div>



	<input type="hidden" name="action" value="Logout">


	</form>




	<c:if test="${ selectedBookInfo!= null }">


		<table border="1" cellpadding="5" align="center">







			<caption>
				<c:if
					test="${(selectedBookInfo['class'] == 'class com.epam.library.domain.ElectronicBook') }">
					<h2>
						<fmt:message key="user.header.Emsg" var="Emsg" />
						${Emsg}
					</h2>
				</c:if>
				<c:if
					test="${(selectedBookInfo['class'] == 'class com.epam.library.domain.PaperBook')  }">
					<h2>
						<fmt:message key="user.header.Pmsg" var="Pmsg" />
						${Pmsg}
					</h2>
				</c:if>
				<c:if
					test="${(selectedBookInfo['class'] == 'class com.epam.library.domain.Book')  }">
					<h2>
						<fmt:message key="user.header.Amsg" var="Amsg" />
						${Amsg}
					</h2>
				</c:if>
			</caption>
			<tr>
				<fmt:message key="user.table.id" var="idBook" />
				<td>${idBook}</td>
				<td><c:out value="${ selectedBookInfo.bookId}" /></td>
			</tr>
			<tr>
				<fmt:message key="user.table.title" var="titleBook" />
				<td>${titleBook}</td>
				<td><c:out value="${ selectedBookInfo.title}" /></td>
			</tr>
			<tr>
				<fmt:message key="user.table.desc" var="desc" />
				<td>${desc}</td>
				<td><c:out value="${selectedBookInfo.description}" /></td>
				<fmt:message key="user.table.author" var="author" />
			</tr>
			<tr>
				<td>${author}</td>
				<td><c:out value="${selectedBookInfo.author}" /></td>
				<fmt:message key="user.table.price" var="price" />
			</tr>
			<tr>
				<td>${price}</td>
				<td><c:out value="${selectedBookInfo.price}" /></td>
				<fmt:message key="user.table.quantity" var="quantity" />
			<tr>
				<td>${quantity}</td>
				<td><c:out value="${selectedBookInfo.quantity}" /></td>
			</tr>
			<tr>
				<c:if
					test="${(selectedBookInfo['class'] == 'class com.epam.library.domain.ElectronicBook')  }">
					<fmt:message key="user.table.version" var="version" />
					<td>${version}</td>
					<td><c:out value="${selectedBookInfo.version}" /></td>
				</c:if>
			</tr>

			<tr>
				<c:if
					test="${(selectedBookInfo['class'] == 'class com.epam.library.domain.PaperBook')  }">
					<fmt:message key="user.table.cover" var="cover" />
					<td>${cover}</td>
					<td><c:out value="${selectedBookInfo.typeOfCover}" /></td>
				</c:if>
			</tr>




		</table>


	</c:if>

	<tr>
		</form>
		<a href="ControllerServlet?action=gettingBook">
			<button class="button button1" align="center">
				<fmt:message key="user.table.goBack" var="goBack" />
				<h3 style=""color:blue;">${goBack}</h3>
			</button>
		</a>

		<%-- <c:otherwise>
			<c:redirect url="/loginPage.jsp" />
		</c:otherwise> --%>
</body>
</html>