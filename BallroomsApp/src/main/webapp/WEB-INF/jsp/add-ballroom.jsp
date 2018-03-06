<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

	<h1>Add Ballroom</h1>
	<form:form method="post" modelAttribute="ballroom">
		
		<table>
			<tr>
				<td><form:label path="ballroomName">Name</form:label></td>
				<td><form:input path="ballroomName" /></td>
				<td><form:errors path="ballroomName" cssClass="error" /></td>
			</tr>
			 <tr>
				<td><form:label path="contactId">Contact</form:label></td>
				<td>
					<form:select path="contactId" required="true">
						<form:option value="">--Select--</form:option>	
						<form:options items="${contacts}" itemLabel="email" itemValue="id" />			
					</form:select>
				</td>
				<td><form:errors path="contactId" cssClass="error" /></td>
			</tr>
			<tr>
			   <td> <a href="<c:url value='/menu/add?id=${ballroom.id}'/>">Add Menu</a></td>
			</tr>
			<tr><td colspan="2" align="right"><input  type="submit" value="Add" /></td></tr>
		</table>
		<form:errors path="*" cssClass="errorblock" element="div" />
	</form:form>

<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>