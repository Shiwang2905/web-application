<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
			<h3>Enter student details :</h3> <br>

 <form action="ServletController" method="GET">
 
 <input type="hidden" name="command" value="ADD" />
 <table>
 	<tbody>
	 	<tr>
	 		<td><label>First Name</label></td>
	 		<td><input type="text" name="fname"/></td>
	 	</tr>
	 	
	 	<tr>
	 		<td><label>Last Name</label></td>
	 		<td><input type="text" name="lname"/></td>
	 	</tr>
	 	
	 	<tr>
	 		<td><label>Email</label></td>
	 		<td><input type="text" name="email"/></td>
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