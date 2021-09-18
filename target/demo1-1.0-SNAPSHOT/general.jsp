
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>


<html>
<head>
    <title>User mode</title>
    <link rel="stylesheet" type="text/css" href="style_for_general.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>


<div id="user_mode">
    <table border="3">

        <tr>
            <td>id</td>
            <td>name</td>
            <td>time</td>
            <td>price</td>
            <td>status</td>
        </tr>

        <c:forEach var="ticket" items="${tickets}">

            <tr>
                <td class="id"><c:out value="${ticket.id}"/></td><td class="names" title="click for order"><c:out value="${ticket.route}"/></td>
                <td><c:out value="${ticket.dateTicket}"/></td>
                <td><c:out value="${ticket.price}"/></td>
                <td><c:out value="${ticket.status}"/></td>
            </tr>

        </c:forEach>

    </table>

</div>

<%
    String userid = (String) session.getAttribute("id_user");
%>


<h4><%= userid %></h4>



<div id="historyBtn">
    <form method="post" action="">
        <p><input class="button-history" type="submit" value="history order"/></p>
    </form>
</div>



<div class="zatem">
    <div class="okno">
        <h3>Do you really want to order this ticket? </h3>
        <table>
            <tr>
                <td>${orderTicket.route}</td>
                <td>${orderTicket.dateTicket}</td>
                <td>${orderTicket.price}</td>
                <td>${orderTicket.status}</td>
            </tr>
        </table>

        <form action="order" method="post">
            <p><input class="button-order" type="submit" value="yes"/></p>
        </form>
    </div>
</div>


<button class="closeBtn">
    Close
</button>

<script src="jsForGeneral.js"></script>
</body>
</html>
