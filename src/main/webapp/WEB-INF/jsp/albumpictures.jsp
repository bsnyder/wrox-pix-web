<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title><spring:message code="title.albums" /></title>
</head>
<body>
<jsp:include flush="true" page="header.jsp"></jsp:include>
<form:form>

<c:choose>
<c:when test="${empty album.pictures}">
	<spring:message code="pictures.notfound" />
</c:when>
<c:otherwise>
<table border="1">
	<c:forEach var="picture" items="${album.pictures}">
		<tr>
			<td>${picture.fileName}</td>
		</tr>
		<tr>
			<td><img src="${picture.location}" /></td>
		</tr>
		<tr>
			<td>${picture.description}</td>
		</tr>	
		<tr>
			<td colspan="3">Rotate: <a
				href="picture/rotateLeft.htm?album=${album.id}&picture=${picture.id}">counter-clockwise</a>
			- <a
				href="picture/rotateRight.htm?album=${album.id}&picture=${picture.id}">clockwise</a></td>
		</tr>
		<tr>
			<td colspan="3">Place : <a
				href="?param=sendOrder&album=${album.id}&picture=${picture.id}">Print Order</a>
				
			</td>
		</tr>
	</c:forEach>
</table>
</c:otherwise>
</c:choose>
</form:form>
</body>
</html>
