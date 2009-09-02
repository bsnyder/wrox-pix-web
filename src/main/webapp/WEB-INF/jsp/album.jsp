<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Wrox Pix - Photo Album</title>
</head>
<body>
<form:form>
	<form:errors path="*"></form:errors>
	<table>
		<tr>
			<td colspan="2"><form:errors path="description"/></td>
		</tr>
		<tr>
			<td>Album Name:</td>
			<td><form:input path="name" /></td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><form:textarea path="description" /></td>
		</tr>
	</table>
	<c:choose>
		<c:when test="${command.isNew}">
			<input type="submit" value="Create" />
		</c:when>
		<c:otherwise>
			<input type="submit" value="Update" />
		</c:otherwise>
	</c:choose>
</form:form>
</body>
</html>
