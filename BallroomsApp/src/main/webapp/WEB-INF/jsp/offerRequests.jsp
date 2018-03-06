<%@ include file="header.jsp"%>
<!-- Page content (Begin)  -->

<h1>Offer Request List</h1>
<c:if test="${not empty message}">
	<div class="infoblock">${message}</div>
</c:if>

<c:if test="${not empty errorMessage}">
	<div class="errorblock">${errorMessage}</div>
</c:if>


<table border="1" cellpadding="4" cellspacing="0">
	<tr>
		<th>Id</th>
		<th>Type</th>
		<th>Guest Number</th>
		<th>Event Date</th>
		<th>Message</th>
		<th>Name</th>
		<th>Email</th>
		<th>Phone</th>
		<th>Salon</th>
		<th>Operations</th>
	</tr>
	<c:forEach items="${offerRequests}" var="offerRequest">
		<tr>
			<td>${offerRequest.id}</td>
			<td>${offerRequest.type}</td>
			<td>${offerRequest.guestsNumber}</td>
			<td>${offerRequest.eventDate}</td>
			<td>${offerRequest.mesage}</td>
			<td>${offerRequest.name}</td>
			<td>${offerRequest.email}</td>
			<td>${offerRequest.phone}</td>
			<td>${offerRequest.salon.salonName}</td>
			<td><a href="<c:url value='/offerRequest/update?id=${offerRequest.id}'/>">Edit</a>
				| <a href="<c:url value='/offerRequest/delete?id=${offerRequest.id}'/>">Delete</a></td>
		</tr>
	</c:forEach>
</table>

<a href="offerRequest/add">Add New Offer Request</a>
</div>

<!-- Page content (End)    -->
<%@ include file="footer.jsp"%>