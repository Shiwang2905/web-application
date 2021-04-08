<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*, com.shiwang.app.classes.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student page</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h1 style="text-align:center">R.G.P.V University</h1>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
			 
			 <input type="button" value="Add Student" 
			 onclick="window.location.href='addStudent.jsp'; return false;"
			 class="add-student-button"/>
			 <table>
				<tr>
					<th>Student Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="slist" items="${returnedList}">
				
				<c:url var="tempLink" value="ServletController">
					<c:param name="command" value="LOAD" />
					<c:param name="studentId" value="${slist.id }" />
				</c:url>
				
				<c:url var="deleteLink" value="ServletController">
					<c:param name="command" value="DELETE" />
					<c:param name="studentId" value="${slist.id }" />
				</c:url>
				
						<tr>
						<td>${slist.id}</td>
						<td>${slist.firstName}</td>
						<td>${slist.lastName}</td>
						<td>${slist.email}</td>
						
						<td><a href="${tempLink}">Update</a> / <a href="${deleteLink}" onclick="if(!(confirm('are you sure, you want to delete this student'))) return false">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		
		</div>
	
	</div>
</body>
</html>