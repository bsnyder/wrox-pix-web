<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Step 3: Edit Picture</title>
</head>
<body>
<jsp:include flush="true" page="header.jsp"></jsp:include>
<h1>Step 3: Edit Picture</h1>

<form:form commandName="upload">
	<table border="1">
		<tr>
			<th colspan="2">${upload.picture.fileName}</th>
		</tr>
		<tr>
			<td colspan="2"><img src="${upload.picture.location}" /></td>
		</tr>
		<tr>
			<td colspan="2"><form:textarea path="picture.description" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="Back" name="_target1" /></td>
			<td align="right"><input type="submit" value="Finish"
				name="_finish" /></td>
		</tr>
	</table>
</form:form>
</body>