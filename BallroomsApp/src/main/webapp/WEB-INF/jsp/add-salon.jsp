<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

	<h1>Add Salon</h1>
	<form:form method="post" modelAttribute="salon">
		
		<table>
			<tr>
				<td><form:label path="salonName">Salon Name</form:label></td>
				<td><form:input path="salonName" /></td>
				<td><form:errors path="salonName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="address">Address</form:label></td>
				<td><form:input path="address" /></td>
				<td><form:errors path="address" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="capacity">Capacity</form:label></td>
				<td><form:input path="capacity" /></td>
				<td><form:errors path="capacity" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="surface">Surface</form:label></td>
				<td><form:input path="surface" /></td>
				<td><form:errors path="surface" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="danceFloor">Dance Floor</form:label></td>
				<td><form:input path="danceFloor" /></td>
				<td><form:errors path="danceFloor" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="terrace">Terrace</form:label></td>
				<td><form:input path="terrace" /></td>
				<td><form:errors path="terrace" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="parking">Parking</form:label></td>
				<td><form:input path="parking" /></td>
				<td><form:errors path="parking" cssClass="error" /></td>
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