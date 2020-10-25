<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.ArrayList"%>
<%@page import="logicHandlers.Course"%>
<%@page import="logicHandlers.Teacher"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="jquery/jquery.3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>Course edit page</title>
</head>
<body>

	<%
		String errorMessage = "";
		String pCourseId = request.getParameter("courseId");
		String operation = request.getParameter("oper");
		String pTeacherId = request.getParameter("teacherId");
		if (operation == null) {
			operation = "";
		}
		int courseId = 0;
		try {
			courseId = Integer.parseInt(pCourseId);
		} catch (Exception ex) {
			courseId = -1;
		}

		int teacherId = 0;
		try {
			teacherId = Integer.parseInt(pTeacherId);
		} catch (Exception ex) {
			teacherId = -1;
		}

		String newCourseName = request.getParameter("courseName");

		if (operation.equals("delete")) {
			Course.delete(courseId);
			String redirectURL = "CourseList.jsp";
			response.sendRedirect(redirectURL);
		} else if (operation.equals("create")) {
			Course.insert("...");
			String redirectURL = "CourseList.jsp";
			response.sendRedirect(redirectURL);
		} else if (newCourseName != null && newCourseName.length() > 0) {
			Course.save(courseId, newCourseName, teacherId);
			String redirectURL = "CourseList.jsp";
			response.sendRedirect(redirectURL);
		}

		Course course = Course.getCourse(courseId);
		String errorText = "";
		if (course == null) {
			errorText = "Such course with id=" + courseId + " doesn't exits";
		}
		if (course != null) {
			ArrayList<Teacher> teachers = Teacher.getTeacherList();
	%>


	<div class="container">
		<h2>Edit course</h2>
		<form class="form-horizontal" method="post">

			<div class="form-group">
				<label class="control-label col-sm-2" for="courseName">Course
					id:</label>
				<div class="col-sm-10">
					<p class="form-control-static">
						<%=courseId%>
					</p>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="courseName">Name:</label>
				<div class="col-sm-10">
					<input id="courseName" name="courseName" type="text"
						class="form-control" value="<%=course.getCourseName()%>"
						placeholder="Course name" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="teacher">Teacher:</label>
				<div class="col-sm-10">
					<select name="teacherId">
						<%
							for (Teacher teacher : teachers) {
						%>
						<option value="<%=teacher.getTeacherId()%>"
							<%=(course.getTeacherId() == teacher.getTeacherId() ? "selected" : "")%>>
							<%=teacher.getPersonName()%>
						</option>
						<%
							}
						%>
					</select>
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