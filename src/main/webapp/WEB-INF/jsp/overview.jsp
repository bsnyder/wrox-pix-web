<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Wrox Pix - Overview</title>
</head>
<body>
<table>
	<tr>
		<th>Album</th>
		<th>Description</th>
		<th>Creation Date</th>
		<th>Action</th>
	</tr>
	<c:forEach var="album" items="${albumList}">
		<tr>
			<td>${album.name}</td>
			<td>${album.description}</td>
			<td>${album.creationDate}</td>
			<td><a
				href="<c:url value="upload.htm">
							<c:param name="id" value="${album.id}" />
						</c:url>">Upload</a>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
