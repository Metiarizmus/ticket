
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errors.jsp" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="style_for_index.css">
</head>
<body>

<div id="registration">
    <form name="data_register" action="registration" onsubmit="return validateForm()" method="post">
        <p><span class="name"> </span><input type="text" name="name" placeholder="Name"></p>
        <p><span class="email"> </span><input type="text" name="email" placeholder="Email"/></p>
        <p><span class="password"> </span><input type="text" name="password" placeholder="Password"/></p>
        <p><input class="button-registr" type="submit" value="REGISTRATION"/></p>
    </form>
    <p><a href="guest" class="guest">As a guest</a> &nbsp;&nbsp;<a href="login.jsp" class="logIn">LogIn</a><span
            class="fontawesome-arrow-right"></span></p>
</div>

<script src="jsForRegistration.js"></script>
</body>
</html>
