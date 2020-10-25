<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.util.ArrayList"%>
<%@page import="logicHandlers.Course"%>
<%@page import="listners.SessionCntListnr"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="jquery/jquery.3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<title>MiniSchool Course List</title>
</head>
<body>
	<%="session count: "  + SessionCntListnr.getTotalActiveSession() %>
	<hr/>
	<h1>Kursu liste:</h1>
	<hr/>
	<a href="CourseEdit.jsp?oper=create">Add new course...</a>
	<table class="table table-striped" >
		<tr>
			<th>Course id</th>
			<th>Course name</th>
			<th>Teacher</th>
			<th></th>
		</tr>
		<%
		ArrayList<Course> courseList = Course.getCourseList();
		for(Course course : courseList) {
			String teacherName=course.getTeacherName();
			teacherName = teacherName==null ? "n/a" : teacherName;
		%>
		
			<tr>
				<td> <%=course.getCourseId() %> </td>
				<td> <%=course.getCourseName() %> </td>
				<td> <%=teacherName %> </td>
				<td> 
					<a href="CourseEdit.jsp?courseId=<%=course.getCourseId() %>">edit</a> 
					<a href="CourseEdit.jsp?oper=delete&courseId=<%=course.getCourseId() %>">delete</a> 
				</td>
			</tr>
		
		<%	
		}	
		%>	
	</table>

</body>
</html>