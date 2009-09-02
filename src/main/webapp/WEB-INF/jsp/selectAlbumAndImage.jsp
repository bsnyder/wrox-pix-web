<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.selectAlbum"/></title>
    
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
            <h2><span><spring:message code="subheader.selectAlbum"/></span></h2>
        </div>

        <div id="quickSummary">
            <p class="p1"><span>
                <spring:message code="message.selectAlbum" />
            </span></p>
        </div>
    </div>
    <div id="form">  
  <form:form name="createNewAlbumForm" commandName="imageUpload" 
      method="post" enctype="multipart/form-data">
     <form:errors path="*" />
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
                <td>
                  <input type="radio" 
                      name="<c:out value="albumId"/>" 
                      value="<c:out value="${album.id}"/>" />
                  <a href="${viewAlbumURL}">${album.name}</a>
                </td>
                <td>${album.description}</td>
                <td>
                  <spring:message code="album.creationDate" arguments="${album.creationDate}" />
                </td>
              </tr>
              </c:forEach>
            </table>
            </td></tr></table>
          </div>
          <div id="quickSummary">
            <p class="p1"><span>
                <spring:message code="message.selectImage" />
            </span></p>
            <input type="file" name="image" />
          </div>
            <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
            <input type="submit" class="button" name="_eventId_next" value="Upload the Image"/>
            
            <div id="quickSummary">
              <h2><span>Create a New Album</span></h2>
              <p class="p1"><span>
                 <spring:message code="message.createAlbum" />
                 <br /><br />
                 <input class="button" name="_eventId_create" value="Create an Album" type="submit">
              </span></p>
            </div>
            <br /><br />
        </c:when>
        <c:otherwise>
          <div id="quickSummary">
              <h2><span>Create a New Album</span></h2>
              <p class="p1"><span>
                 <spring:message code="message.noAlbums.createAlbum" />
                 <br /><br />
                 <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
                 <input class="button" name="_eventId_create" value="Create an Album" type="submit">
              </span></p>
            </div>
         </c:otherwise>
     </c:choose>
    </form:form>
 </div>

</div>
</body>
</html>
