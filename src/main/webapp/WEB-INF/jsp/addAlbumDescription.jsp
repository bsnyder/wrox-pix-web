<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
	<title><spring:message code="title.albumDescription"/></title>
	
	<!-- to correct the unsightly Flash of Unstyled Content. 
		 http://www.bluerobot.com/web/css/fouc.asp -->
	<script type="text/javascript"></script>
    
    <style type="text/css" media="all">
        @import "<c:url value="/css/pixweb.css" />";
    </style>
</head>

<body onLoad="document.addAlbumDescriptionForm.description.focus();">

<div id="container">
	<div id="intro">
		<div id="pageHeader">
			<img src="<c:url value="/img/pixweb-logo.jpg" />" width="400">
			<h2><span><spring:message code="subheader.albumDescription"/></span></h2>
		</div>

		<div id="quickSummary">
			<p class="p1"><span>
				<spring:message code="message.albumDescription" />
			</span></p>
		</div>
	</div>
	<div id="form">
		<form:form commandName="album" name="addAlbumDescriptionForm">
    	<form:errors path="*" />
    		<table>
			<tr>
				<td align="right"><spring:message code="description.label" /></td>
				<td><form:textarea path="description" rows="3" cols="20" /></td>
		        <td><form:errors path="description" /></td>
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