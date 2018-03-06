<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

    <h1>Salons List</h1>
    <c:if test="${not empty message}">
	 <div class="infoblock">${message}</div>
	</c:if>
	
    <c:if test="${not empty errorMessage}">
	 <div class="errorblock">${errorMessage}</div>
	</c:if>

 
    <table border="1" cellpadding="4" cellspacing="0">
    	<tr>
    		<th>Id</th>
    		<th>Salon Name</th>
    		<th>Address</th>
    		<th>Capacity</th>
    		<th>Surface</th>
    		<th>Dance floor</th>
    		<th>Terrace</th>
    		<th>Parking</th>
    		<th>Ballroom</th>
    		<th>Operations</th>
    	</tr>
	    <c:forEach items="${salons}" var="salon">
	    <tr>
    		<td>${salon.id}</td>
    		<td>${salon.salonName}</td>
    		<td>${salon.address}</td>
    		<td>${salon.capacity}</td>
    		<td>${salon.surface}</td>
    		<td>${salon.danceFloor}</td>
    		<td>${salon.terrace}</td>
    		<td>${salon.parking}</td>
    		<td>${salon.ballroom.ballroomName}</td>
    		<td><a href="<c:url value='/salon/update?id=${salon.id}'/>">Edit</a> | <a href="<c:url value='/salon/delete?id=${salon.id}'/>">Delete</a></td>
    	</tr>    	
	    </c:forEach>
    </table>  
    
    <a href="salon/add">Add New Salon</a></div>
    
<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>