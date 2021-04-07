<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update students
</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
<div id="wrapper">
		<div id="header">
			<h1 style="text-align:center">R.G.P.V University</h1>
		</div>
	</div>
	<br></br>
<div id="container">
			<h3>Update student details :</h3> <br>

 <form action="ServletController" method="GET">
 
 <input type="hidden" name="command" value="UPDATE" />
 <input type="hidden" name="studentId" value="${UpdateStudent.id }" />
 
 <table>
 	<tbody>
	 	<tr>
	 		<td><label>First Name</label></td>
	 		<td><input type="text" name="fname" value="${UpdateStudent.firstName }"/></td>
	 	</tr>
	 	
	 	<tr>
	 		<td><label>Last Name</label></td>
	 		<td><input type="text" name="lname" value="${UpdateStudent.lastName }"/></td>
	 	</tr>
	 	
	 	<tr>
	 		<td><label>Email</label></td>
	 		<td><input type="text" name="email" value="${UpdateStudent.email}"/></td>
	 	</tr>
	 	
	 	<tr>
	 		<td><label></label></td>
	 		<td><input type="Submit" value="Save" class="save"/></td>
	 	</tr>
 	</tbody>
 </table>
</form>
			
</div>	
<br>
<a href="ServletController"> Back to list</a>
</body>
</html>