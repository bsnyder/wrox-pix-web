<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Step 1: Select Album</title>
</head>
<body>
<jsp:include flush="true" page="header.jsp"></jsp:include>
<h1>Step 1: Select Album</h1>

<form:form commandName="upload">
	<form:errors path="*"></form:errors>
	<table border="1">
		<tr>
			<th colspan="2">Album Name</th>
			<th>Description</th>
		</tr>
		<c:forEach var="album" items="${albumList}">
			<tr>
				<td><form:radiobutton path="albumId" value="${album.id}" /></td>
				<td>${album.name}</td>
				<td>${album.description}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3" align="right"><input type="submit" value="Next"
				name="_target1" /></td>
		</tr>
	</table>
</form:form>
</body>
</html>