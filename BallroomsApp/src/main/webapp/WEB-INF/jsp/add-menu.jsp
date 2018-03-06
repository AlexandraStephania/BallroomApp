<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

	<h1>Add Menu</h1>
	<form:form method="post" modelAttribute="menu">
		
		<table>
			<tr>
				<td><form:label path="menuName">Menu Name</form:label></td>
				<td><form:input path="menuName" /></td>
				<td><form:errors path="menuName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="menuType">Menu Type</form:label></td>
				<td><form:input path="menuType" /></td>
				<td><form:errors path="menuType" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="description">Description</form:label></td>
				<td><form:input path="description" /></td>
				<td><form:errors path="description" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="price">Price</form:label></td>
				<td><form:input path="price" /></td>
				<td><form:errors path="price" cssClass="error" /></td>
			</tr>
			 <tr>
				<td><form:label path="ballroomId">Ballroom</form:label></td>
				<td>
					<form:select path="ballroomId" required="true">
						<form:option value="">--Select--</form:option>	
						<form:options items="${ballrooms}" itemLabel="ballroomName" itemValue="id" />			
					</form:select>
				</td>
				<td><form:errors path="ballroomId" cssClass="error" /></td>
			</tr>
			<tr><td colspan="2" align="right"><input  type="submit" value="Add" /></td></tr>
		</table>
		<form:errors path="*" cssClass="errorblock" element="div" />
	</form:form>

<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>