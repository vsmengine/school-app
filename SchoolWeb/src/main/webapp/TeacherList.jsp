<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.util.ArrayList"%>
<%@page import="logicHandlers.Teacher" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="jquery/jquery.3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<title>MiniSchool Teacher List</title>
</head>
<body>
<h1>Teacher List:</h1>
	<hr/>
	<a href="TeacherEdit.jsp?oper=create">Add new Teacher...</a>
	<table class="table table-striped" >
		<tr>
			<th>Teacher id</th>
			<th>Teacher name</th>
			<th>Designation</th>
			<th></th>
		</tr>
		<%
		ArrayList<Teacher> teacherList = Teacher.getTeacherList();
		for(Teacher teacher : teacherList) {
		%>
		
			<tr>
				<td> <%=teacher.getTeacherId() %> </td>
				<td> <%=teacher.getPersonName() %> </td>
				<td> <%=teacher.getTeacherLevel() %> </td>
				<td> 
					<a href="TeacherEdit.jsp?teacherId=<%=teacher.getTeacherId() %>">edit</a> 
					<a href="TeacherEdit.jsp?oper=delete&teacherId=<%=teacher.getTeacherId() %>">delete</a> 
				</td>
			</tr>
		
		<%	
		}	
		%>	
	</table>

</body>
</html>