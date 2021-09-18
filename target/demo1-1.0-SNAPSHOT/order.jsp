<%@ page import="entity.StatusTicket" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String userid = (String) session.getAttribute("id_user");
    String orderid = request.getParameter("orderTicket");
%>

<p><%=userid%> userid</p>
<p><%=orderid%> orderid</p>


</body>
</html>
