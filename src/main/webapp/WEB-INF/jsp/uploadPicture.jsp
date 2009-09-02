<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Step 2: Upload Picture</title>
</head>
<body>
<jsp:include flush="true" page="header.jsp"></jsp:include>
<h1>Step 2: Upload Picture</h1>

<form:form commandName="upload" enctype="multipart/form-data">
	<form:errors path="*" />
	<table border="1">
		<tr>
			<td colspan="2">Click "Browse" to select a picture to add to
			your photo album and click upload.</td>
		</tr>
		<tr>
			<td colspan="2"><input type="file" name="upload" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="Back" name="_target0" /></td>
			<td align="right"><input type="submit" value="Next"
				name="_target2" /></td>
		</tr>
	</table>
</form:form>
</body>
</html>