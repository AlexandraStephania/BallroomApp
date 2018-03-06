<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

    <h1>Ballrooms List</h1>
    <c:if test="${not empty message}">
	 <div class="infoblock">${message}</div>
	</c:if>
	
    <c:if test="${not empty errorMessage}">
	 <div class="errorblock">${errorMessage}</div>
	</c:if>

 
    <table border="1" cellpadding="4" cellspacing="0">
    	<tr>
    		<th>Id</th>
    		<th>Name</th>
    		<th>Contact</th>
    		<th>Menus</th>
    		<th>Operations</th>
    	</tr>
	    <c:forEach items="${ballrooms}" var="ballroom">
	     
	    <tr>
    		<td>${ballroom.id}</td>
    		<td>${ballroom.ballroomName}</td>
    		<td>${ballroom.contact.email}</td>
    		 <td>
	    		 <c:forEach items="${ballroom.menus}" var="menu">
	    		 	${menu.menuName}
	    		 </c:forEach>
    		 </td>
    		<td><a href="<c:url value='/ballroom/update?id=${ballroom.id}'/>">Edit</a> | <a href="<c:url value='/ballroom/delete?id=${ballroom.id}'/>">Delete</a></td>
    	</tr>    	
	    </c:forEach>
    </table>  
    
    <a href="ballroom/add">Add New Ballroom</a></div>
    
<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>