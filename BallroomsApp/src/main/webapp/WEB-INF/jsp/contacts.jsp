<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

    <h1>Contacts</h1>
    <c:if test="${not empty message}">
	 <div class="infoblock">${message}</div>
	</c:if>
	
    <c:if test="${not empty errorMessage}">
	 <div class="errorblock">${errorMessage}</div>
	</c:if>

 
    <table border="1" cellpadding="4" cellspacing="0">
    	<tr>
    		<th>Id</th>
    		<th>Email</th>
    		<th>Phone</th>
    		<th>Address</th>
    		<th>Site</th>
    		<th>Operations</th>
    		
    	</tr>
	    <c:forEach items="${contacts}" var="contact">
	    <tr>
    		<td>${contact.id}</td>
    		<td>${contact.email}</td>
    		<td>${contact.telephone}</td>
    		<td>${contact.address}</td>
    		<td>${contact.site}</td>
    		<td><a href="<c:url value='/contact/update?id=${contact.id}'/>">Edit</a> | <a href="<c:url value='/contact/delete?id=${contact.id}'/>">Delete</a></td>
    	</tr>    	
	    </c:forEach>
    </table>  
    
    <a href="contact/add">Add New Contact</a></div>
    
<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>