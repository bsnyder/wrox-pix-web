<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title><spring:message code="title.albums" /></title>
</head>
<body>
<jsp:include flush="true" page="header.jsp"></jsp:include>
<table border="1">
	<tr>
		<th><spring:message code="header.album.title" /></th>
		<th><spring:message code="header.album.description" /></th>
		<th><spring:message code="header.album.creationDate" /></th>
		<th>PDF</th>
		<th>RSS</th>
	</tr>
	<c:forEach var="album" items="${albumList}">
		<c:url var="viewAlbumURL" value="albumpictures.htm">
			<c:param name="album" value="${album.id}"></c:param>
		</c:url>
		<c:url var="pdfAlbumURL" value="${viewAlbumURL}">
			<c:param name="view" value="pdf"></c:param>
		</c:url>
		<c:url var="rssAlbumURL" value="${viewAlbumURL}">
			<c:param name="view" value="rss"></c:param>
		</c:url>
		<tr>
			<td><a href="${viewAlbumURL}">${album.name}</a></td>
			<td>${album.description}</td>
			<td><spring:message code="album.creationDate"
				arguments="${album.creationDate}" /></td>
			<td><a href="${pdfAlbumURL}">Download PDF</a></td>
			<td><a href="${rssAlbumURL}">RSS</a></td>
		</tr>
	</c:forEach>
</table>
<a href="pictureupload.htm">Upload Pictures</a>
<a href="createalbum.htm">Create a New Album</a>
</body>
</html>
