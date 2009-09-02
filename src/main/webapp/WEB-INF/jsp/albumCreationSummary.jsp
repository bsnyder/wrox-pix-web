<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
    <!-- The spring:message tag extracts a property named title.summary from the
    labels-en.properties file. -->
    <title><spring:message code="title.summary"/></title>
    
    <!-- To correct the unsightly Flash of Unstyled Content. 
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

            <!-- The spring:message tag below extracts a property named
            subheader.summary from the labels-en.properties file. -->
            <h2><span><spring:message code="subheader.summary"/></span></h2>
        </div>

        <div id="quickSummary">
            <p class="p1"><span>
                <spring:message code="message.summary" />
            </span></p>
        </div>
    </div>
    <div id="form">
        <!-- Create a HTML form that is associated with a form-backing object
        named album. -->
        <form:form commandName="album" name="addAlbumLabelForm">

        <!-- Display any errors from the form below -->
        <form:errors path="*" />
            <table>
            <tr>
                <td align="right"><spring:message code="albumName.summary.label" /></td>

                <!-- When dereferencing the ${album.name} property, use the
                name property from the form-backing object named album. -->
                <td><spring:bind path="album.name">${album.name}</spring:bind></td>
            </tr>
            <tr>
                <td align="right"><spring:message code="description.summary.label" /></td>

                <!-- When dereferencing the ${album.description} property, use
                the description property from the form-backing object named album. -->
                <td><spring:bind path="album.description">${album.description}</spring:bind></td>
            </tr>
            <tr>
                <td align="right"><spring:message code="albumLabels.summary.label" /></td>
                <td>
                    <spring:bind path="album.labels">
                        <c:forEach items="${album.labels}" var="label">${label}<br /></c:forEach>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td align="right"><spring:message code="creationDate.summary.label" /></td>
                <td><spring:bind path="album.creationDate">${album.creationDate}</spring:bind></td>
            </tr>
            <tr>
                <td colspan="2">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2">
                    <!-- When this form is submitted tell SWF to populate the 
                    value of this hidden input with the flow execution key. -->
                    <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>

                    <!-- Tell the form to use an event named create. This tells 
                    SWF which flow to execute. -->
                    <input type="submit" class="button" name="_eventId_create" value="Create Album"/>

                    <!-- Offer an alternative event named edit to go back through 
                    the wizard-like functionality to edit the content again. -->
                    <input type="submit" class="button" name="_eventId_edit" value="Edit Attributes"/>
                </td>
            </tr>
            </table>
        </form:form>
    </div>
</div>

</body>
</html>
