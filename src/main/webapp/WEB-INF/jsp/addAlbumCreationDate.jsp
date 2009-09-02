<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
	<title><spring:message code="title.albumCreationDate"/></title>
	
	<!-- to correct the unsightly Flash of Unstyled Content. 
		 http://www.bluerobot.com/web/css/fouc.asp -->
	<script type="text/javascript"></script>
    
    <style type="text/css" media="all">
        @import "<c:url value="/css/pixweb.css" />";
    </style>
</head>

<body onLoad="document.addAlbumCreationDateForm.creationDate.focus();">

<div id="container">
	<div id="intro">
		<div id="pageHeader">
			<img src="<c:url value="/img/pixweb-logo.jpg" />" width="400">
			<h2><span><spring:message code="subheader.albumCreationDate"/></span></h2>
		</div>

		<div id="quickSummary">
			<p class="p1"><span>
				<spring:message code="message.albumCreationDate" />
			</span></p>
		</div>
	</div>
	<div id="form">
		<form:form commandName="album" name="addAlbumCreationDateForm">
    	<form:errors path="*" />
    		<table>
			<tr>
				<td align="right"><spring:message code="creationDate.label" /></td>
				<td><form:input path="creationDate" size="35" /></td>
		        <td><form:errors path="creationDate" /></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
					<input type="submit" class="button" name="_eventId_next" value="Next"/>
				</td>
			</tr>
			</table>
		</form:form>
	</div>
</div>

</body>
</html>