
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errors.jsp" %>
<html>
<head>
    <title>Log in to the system</title>
    <link rel="stylesheet" type="text/css" href="style_for_login.css">
</head>
<body>
<div class="loging">
    <form name="data_register" method="post" action="enterToSystem" onsubmit="return validateForm()">
        <p><span class="email"> </span><input type="text" name="email" placeholder="Email"/></p>
        <p><span class="password"> </span><input type="text" name="password" placeholder="Password"/></p>
        <p><input class="button-logIn" type="submit" value="LOGIN"/></p>
    </form>
</div>

<script src="jsForRegistration.js"></script>
</body>
</html>
