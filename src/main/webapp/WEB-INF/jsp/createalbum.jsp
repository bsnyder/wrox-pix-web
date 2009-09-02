<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title><spring:message code="title.create" /></title>
</head>
<body>
<jsp:include flush="true" page="header.jsp"></jsp:include>

<div align="left"><spring:message code="${message}" text="" /></div>
<!-- Album Creation Form -->
<form:form>
	<form:errors path="*" />
	<table id="createAlbum">
		<tr>
			<th>Album Name*</th>
			<td><form:input path="name" /></td>
		</tr>
		<tr>
			<th>Description</th>
			<td><form:textarea path="description" /></td>
		</tr>
		<tr>
			<th>Album Labels</th>
			<td><c:forEach items="${labels}" var="label">
				<form:checkbox path="labels" value="${label}" />${label} <br />
			</c:forEach></td>
		</tr>
		<tr>
			<th>Creation Date</th>
			<td><form:input path="creationDate" /></td>
		</tr>
	</table>
	<input type="submit" value="Create" />
</form:form>

</body>
</html>
