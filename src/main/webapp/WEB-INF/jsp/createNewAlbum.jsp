<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
	<title><spring:message code="title.createNewAlbum"/></title>
	
	<!-- to correct the unsightly Flash of Unstyled Content. 
		 http://www.bluerobot.com/web/css/fouc.asp -->
	<script type="text/javascript"></script>
    
    <style type="text/css" media="all">
        @import "<c:url value="/css/pixweb.css" />";
    </style>
</head>

<body onLoad="document.createNewAlbumForm._eventId_next.focus();">

<div id="container">
	<div id="intro">
		<div id="pageHeader">
			<img src="<c:url value="/img/pixweb-logo.jpg" />" width="400">
			<h2><span><spring:message code="subheader.createNewAlbum"/></span></h2>
		</div>

		<div id="quickSummary">
			<p class="p1"><span>
				<spring:message code="message.createNewAlbum" />
			</span></p>
		</div>
	</div>
	
	<div id="form">		
		<form:form commandName="album" name="createNewAlbumForm">
    	<form:errors path="*" />
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
			<input type="submit" class="button" name="_eventId_next" value="Next"/>
		</form:form>
	</div>
</div>

</body>
</html>