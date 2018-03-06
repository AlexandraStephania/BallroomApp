<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->
	<h1>Update admin</h1>
	<form:form method="post" modelAttribute="admin">
				<table>
			<tr>
				<td><form:label path="userName">User Name</form:label></td>
				<td><form:input path="userName" /></td>
				<td><form:errors path="userName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:input path="password" /></td>
				<td><form:errors path="password" cssClass="error" /></td>
			</tr>
			<tr><td colspan="2" align="right"><input  type="submit" value="Update" /></td></tr>
		</table>
	</form:form>

<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>