<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
	<title><spring:message code="title.login"/></title>
	
	<!-- to correct the unsightly Flash of Unstyled Content. 
		 http://www.bluerobot.com/web/css/fouc.asp -->
	<script type="text/javascript"></script>
    
    <style type="text/css" media="all">
        @import "<c:url value="/css/pixweb.css" />";
    </style>
</head>

<body onLoad="document.loginForm.userName.focus();">

<div id="container">
	<div id="intro">
		<div id="pageHeader">
			<img src="<c:url value="/img/pixweb-logo.jpg" />" width="400">
			<h2><span><spring:message code="subheader.login"/></span></h2>
		</div>

		<div id="quickSummary">
			<p class="p1"><span>
				<spring:message code="message.login" />
			</span></p>
		</div>
	</div>
	<div id="form">
		<form:form commandName="user" name="loginForm">
    	<form:errors path="*" />
			<table>
			<tr>
				<td align="right"><spring:message code="userName.label" /></td>
				<td><form:input id="userName" path="userName" /></td>
		        <td><form:errors id="userName" path="userName" /></td>
			</tr>
			<tr>
				<td align="right"><spring:message code="password.label" /></td>
				<!-- 
				Unfortunately the password entered in the register.jsp is not able 
				to be passed in and displayed here because Spring MVC 2.0.2 does 
				not allow the password to be shown, however, Spring MVC 2.0.3 
				and greater provides the showPassword attribute to facilitate 
				this functionality. I'd switch the version but I'm not sure if 
				this will break other parts of the PixWeb app. 
				-->
				<td><form:password id="password" path="password" /></td>
		        <td><form:errors id="password" path="password" /></td>
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