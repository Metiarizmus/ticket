<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Guest mode</title>
    <link rel="stylesheet" type="text/css" href="style_for_guest.css">
</head>
<body>

<div id="guest_mode">
    <table border="3">

        <tr>
            <td>id</td>
            <td>name</td>
            <td>time</td>
            <td>price</td>
            <td>status</td>
        </tr>

        <c:forEach var="ticket" items="${tickets_for_guest}">

            <tr>
                <td><c:out value="${ticket.id}"/></td>
                <td><c:out value="${ticket.route}"/></td>
                <td><c:out value="${ticket.dateTicket}"/></td>
                <td><c:out value="${ticket.price}"/></td>
                <td><c:out value="${ticket.status}"/></td>
            </tr>

        </c:forEach>


    </table>
</div>

<div id="my_button">
    <form method="post" action="index.jsp">
        <p><input class="button-back" type="submit" value="back"/></p>
    </form>
</div>

</body>
</html>

