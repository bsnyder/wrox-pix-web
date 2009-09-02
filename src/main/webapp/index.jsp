<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head><title><fmt:message key="title.welcome" /></title></head>
<body>
<%-- Redirecting to the login page. --%>
<c:redirect url="/flow.htm?_flowId=login-flow" /> 
<%--<c:redirect url="flow/login-flow" />--%>
</body>
</html>