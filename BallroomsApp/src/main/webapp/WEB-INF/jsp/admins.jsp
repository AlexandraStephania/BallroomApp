<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

    <h1>Admins</h1>
    <c:if test="${not empty message}">
	 <div class="infoblock">${message}</div>
	</c:if>
	
    <c:if test="${not empty errorMessage}">
	 <div class="errorblock">${errorMessage}</div>
	</c:if>

 
    <table border="1" cellpadding="4" cellspacing="0">
    	<tr>
    		<th>Id</th>
    		<th>User Name</th>
    		<th>Password</th>
    		<th>Operations</th>
    		
    	</tr>
	    <c:forEach items="${admins}" var="admin">
	    <tr>
    		<td>${admin.id}</td>
    		<td>${admin.userName}</td>
    		<td>${admin.password}</td>
    		<td><a href="<c:url value='/admin/update?id=${admin.id}'/>">Edit</a> | <a href="<c:url value='/admin/delete?id=${admin.id}'/>">Delete</a></td>
    	</tr>    	
	    </c:forEach>
    </table>  
    
    <a href="admin/add">Add New Admin</a></div>
    
<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>