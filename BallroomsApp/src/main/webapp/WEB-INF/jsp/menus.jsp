<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

    <h1>Menus List</h1>
    <c:if test="${not empty message}">
	 <div class="infoblock">${message}</div>
	</c:if>
	
    <c:if test="${not empty errorMessage}">
	 <div class="errorblock">${errorMessage}</div>
	</c:if>

 
    <table border="1" cellpadding="4" cellspacing="0">
    	<tr>
    		<th>Id</th>
    		<th>Menu Name</th>
    		<th>Menu Type</th>
    		<th>Description</th>
    		<th>Price</th>
    		<th>Ballroom</th>
    		<th>Operations</th>
    	</tr>
	    <c:forEach items="${menus}" var="menu">
	    <tr>
    		<td>${menu.id}</td>
    		<td>${menu.menuName}</td>
    		<td>${menu.menuType}</td>
    		<td>${menu.description}</td>
    		<td>${menu.price}</td>
    		<td>${menu.ballroom.ballroomName}</td>
    		<td><a href="<c:url value='/menu/update?id=${menu.id}'/>">Edit</a> | <a href="<c:url value='/menu/delete?id=${menu.id}'/>">Delete</a></td>
    	</tr>    	
	    </c:forEach>
    </table>  
    
    <a href="menu/add">Add New Menu</a></div>
    
<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>