<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

	<h1>Update Offer Request</h1>
	<form:form method="post" modelAttribute="offerRequest">
		
		<table>
			<tr>
				<td><form:label path="type">Type</form:label></td>
				<td><form:input path="type" /></td>
				<td><form:errors path="type" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="guestsNumber">Guests Number</form:label></td>
				<td><form:input path="guestsNumber" /></td>
				<td><form:errors path="guestsNumber" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="eventDate">Event Date</form:label></td>
				<td><form:input path="eventDate" /></td>
				<td><form:errors path="eventDate" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="mesage">Mesage</form:label></td>
				<td><form:input path="mesage" /></td>
				<td><form:errors path="mesage" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="phone">Phone</form:label></td>
				<td><form:input path="phone" /></td>
				<td><form:errors path="phone" cssClass="error" /></td>
			</tr>
			
			 <tr>
				<td><form:label path="salonId">Ballroom</form:label></td>
				<td>
					<form:select path="salonId" required="true">
						<form:option value="">--Select--</form:option>	
						<form:options items="${salons}" itemLabel="salonName" itemValue="id" />			
					</form:select>
				</td>
				<td><form:errors path="salonId" cssClass="error" /></td>
			</tr>
			<tr><td colspan="2" align="right"><input  type="submit" value="Update" /></td></tr>
		</table>
		<form:errors path="*" cssClass="errorblock" element="div" />
	</form:form>

<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>