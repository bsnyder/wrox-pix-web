<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
	<title><spring:message code="title.albums2"/></title>
	
	<!-- to correct the unsightly Flash of Unstyled Content. 
		 http://www.bluerobot.com/web/css/fouc.asp -->
	<script type="text/javascript"></script>
    
    <style type="text/css" media="all">
        @import "<c:url value="/css/pixweb.css" />";
    </style>
    
</head>

<body>

<div id="container">
	<div id="intro">
		<div id="pageHeader">
			<img src="<c:url value="/img/pixweb-logo.jpg" />" width="400">
			<h2><span><spring:message code="subheader.albums2"/></span></h2>
		</div>

		<div id="quickSummary">
			<p class="p1"><span>
				<spring:message code="message.albums2" />
			</span></p>
		</div>
	</div>
      <c:choose>
        <c:when test="${not empty albumList}">
          <div id="table">
            <table bgcolor="#999999"><tr><td>
            <table bgcolor="#ffffff" border="1" class="albums">
              <tr>
               <th><spring:message code="header.album.title" /></th>
               <th><spring:message code="header.album.description" /></th>
               <th><spring:message code="header.album.creationDate" /></th>
              </tr>
              <c:forEach var="album" items="${albumList}" varStatus="status">
                <c:url var="viewAlbumURL" value="albumpictures.htm">
                  <c:param name="album" value="${album.id}"></c:param>
                </c:url>
                <c:set var="rowColor" value="${(status.index) %2 eq 0 ? 'shaded' : 'unshaded'}" />
              <tr class="${rowColor}"> 
                <td><a href="${viewAlbumURL}">${album.name}</a></td>
                <td>${album.description}</td>
                <td>
                  <spring:message code="album.creationDate" arguments="${album.creationDate}" />
                </td>
              </tr>
              </c:forEach>
            </table>
            </td></tr></table>
          </div>
        </c:when>
        <c:otherwise>
         <div id="message"><spring:message code="message.noAlbums" /></div>
        </c:otherwise>
     </c:choose>
 
	<div id="pageHeader">
    	<h2>Additional Features</h2>
    </div> 
	<div id="quickSummary">
		<p class="p1"><span>
			<spring:message code="message.otherFunctions" />
		</span></p>
	</div>
	<div id="links">
		<h3>
		<a href="albums.htm">Create a New Photo Album</a><br />
		<a href="pictureupload.htm">Upload Photos to a Photo Album</a><br />
		<a href="flow.htm?_flowId=album-creation-flow">Create a New Photo Album Using Spring WebFlow</a><br />
        <!--a href="flow.htm?_flowId=image-upload-flow&userName=${sessionScope.user.userName}">Upload Photos to a Photo Album Using Spring WebFlow</a><br /-->
		</h3>
	</div>
</div>

</body>
</html>