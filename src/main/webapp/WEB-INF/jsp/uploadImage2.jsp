<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
	<title><spring:message code="title.upload"/></title>
	
	<!-- to correct the unsightly Flash of Unstyled Content. 
		 http://www.bluerobot.com/web/css/fouc.asp -->
	<script type="text/javascript"></script>
    
    <style type="text/css" media="all">
        @import "<c:url value="/css/pixweb.css" />";
    </style>
</head>

<body onLoad="document.uploadForm.imageUpload.focus();">

<div id="container">
	<div id="intro">
		<div id="pageHeader">
			<img src="<c:url value="/img/pixweb-logo.jpg" />" width="400">
			<h2><span><spring:message code="subheader.upload"/></span></h2>
		</div>

		<div id="quickSummary">
			<p class="p1"><span>
				<spring:message code="message.upload" />
			</span></p>
		</div>
	</div>
	<div id="form">
		<form:form commandName="upload" enctype="multipart/form-data" name="uploadForm">
    	<form:errors path="*" />
			<table>
			<tr>
				<td colspan="2"></td>
			</tr>
			<tr>
				<td align="right"><spring:message code="upload.label" /></td>
				<td><input type="file" name="imageUpload" /></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
					<input type="submit" class="button" name="_eventId_submit" value="Login"/>
				</td>
			</tr>
			</table>
		</form:form>
	</div>
</div>

</body>
</html>