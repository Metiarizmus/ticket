
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ page errorPage="errors.jsp" %>

<html lang="ru">
<head>
    <title>User mode</title>
    <link rel="stylesheet" type="text/css" href="style_for_general.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.debug.js"></script>

</head>
<body>


<div class="user_mode">
    <table border="3">

        <tr>
            <td>Заказать</td>
            <td style="display:none;">id</td>
            <td>Маршрут</td>
            <td>Время</td>
            <td>Цена</td>
            <td>Статус</td>
        </tr>

        <c:forEach var="ticket" items="${tickets}">
            <tr>
                <td class="id" style="display:none;"><c:out value="${ticket.id}"/></td><td class="order" title="click for order">заказать</td>
                <td class="names"><c:out value="${ticket.route}"/></td>
                <td><c:out value="${ticket.dateTicket}"/></td>
                <td><c:out value="${ticket.price}"/></td>
                <td style="text-transform: lowercase" class="status_ticket"><c:out value="${ticket.status}"/></td>
            </tr>
        </c:forEach>

    </table>

</div>



<div id="historyBtn">
    <form action="history" method="post" >
        <p><input class="button-history" type="submit" value="history order"/></p>
    </form>
</div>



<div class="zatem">
    <div class="okno">

    </div>
</div>


<button class="closeBtn">
    Close
</button>


<input class="pdf" type="button" value="to PDF">

<script src="jsForGeneral.js"></script>
</body>
</html>
