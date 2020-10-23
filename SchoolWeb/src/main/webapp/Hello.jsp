<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- <h1>Labdien Sampath!</h1> -->
	
	<%Object welTitle = request.getAttribute("title");%> 
	<h1><%=welTitle%></h1>
            
	<!-- 
	<table>
	<tr>
		<th>x</th>
		<th> x<sup>2</sup> </th>
	</tr>
	<%
	  for (int i=0; i<10; i++) {
	%>
		<tr>
			<td> <%= i %> </td>
			<td> <%= i*i %> </td>
		</tr>		
	<%
	}
	%> 
	</table> -->
</body>
</html>