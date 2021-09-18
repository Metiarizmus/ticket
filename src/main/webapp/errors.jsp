<%@ page import="myException.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>

<html>
<head>
    <title>Sorry! something went wrong...</title>

</head>
<body style="text-align: center">
<div>
    <img src="errorImage.png">
    <p><%= exception%></p>
</div>


</body>
</html>
