<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.util.ArrayList"%>
<%@page import="logicHandlers.Teacher"%>
<%@page import="logicHandlers.Person"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="jquery/jquery.3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<title>Teacher edit page</title>
</head>
<body>

	<%
		String errorMessage = "";
		String pTeacherId = request.getParameter("teacherId");
		String operation = request.getParameter("oper");
		String pTeacherLevel = request.getParameter("teacher");
		if (operation == null) {
			operation = "";
		}
		int teacherId = 0;
		try {
			teacherId = Integer.parseInt(pTeacherId);
		} catch (Exception ex) {
			teacherId = -1;
		}

		String newTeacherName = request.getParameter("teacherName");

		if (operation.equals("delete")) {
			Teacher.delete(teacherId);
			String redirectURL = "TeacherList.jsp";
			response.sendRedirect(redirectURL);
		} else if (operation.equals("create")) {
			Teacher.insert("...");
			String redirectURL = "TeacherList.jsp";
			response.sendRedirect(redirectURL);
		} else if (newTeacherName != null && newTeacherName.length() > 0) {
			Teacher.save(teacherId, newTeacherName);
			String redirectURL = "TeacherList.jsp";
			response.sendRedirect(redirectURL);
		}
		
		Teacher teacher = Teacher.getTeacher(teacherId);
		String errorText = "";
		if (teacher == null) {
			errorText = "Such teacher with id=" + teacherId + " doesn't exits";
		}
		if (teacher != null) {
	%>

	<div class="container">
		<h2>Edit teacher</h2>
		<form class="form-horizontal" method="post">

			<div class="form-group">
				<label class="control-label col-sm-2" for="teacherName">Teacher id:</label>
				<div class="col-sm-10">
					<p class="form-control-static">
						<%=teacher.getTeacherId()%>
					</p>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="teacherName">Name:</label>
				<div class="col-sm-10">
					<input id="teacherName" name="teacherName" type="text"
						class="form-control" value="<%=teacher.getPersonName()%>"
						placeholder="Teacher name" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Save</button>
				</div>
			</div>

		</form>
	</div>


	<%
		} else {
	%>
	<h3><%=errorText%></h3>
	<%
		}
	%>

</body>
</html>