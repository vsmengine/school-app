<html>
<body>
	<h2>Hello World! :D ...</h2>
	
	<h2>Factorial Table</h2>
	<%
		int p = 1;
		for (int i = 1; i <= 10; i++) {
			p *= i;
	%>
	<h3>
		<%=i%>! = <%=p%>
	</h3>
	<%
		}
	%>
</body>
</html>