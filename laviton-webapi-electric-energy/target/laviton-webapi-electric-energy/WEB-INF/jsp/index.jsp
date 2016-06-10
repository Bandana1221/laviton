<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<form:form method="post"  action="fileUpload">
		<table>
			<tr>
				<!-- <td><input type="button"  value="UPLOAD YOUR DATA"> </td>  -->
				<td><input type="text" id="upload" name="file"></td>
			</tr>
			<tr>
				<td></td>
				<td><input id="upl" type="submit" value="Upload" /></td>
				<td></td>
			</tr>
		</table>
	</form:form>

</body>
</html>
