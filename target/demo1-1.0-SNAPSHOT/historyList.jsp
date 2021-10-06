<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ page errorPage="errors.jsp" %>
<html>
<head>
    <title>History</title>
    <link rel="stylesheet" type="text/css" href="style_for_history.css">
    <link rel="stylesheet" type="text/css" href="style_for_general.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="history_mode">
    <table border="3">

        <tr>
            <td style="display:none;">id</td>
            <td>статус заказа</td>
            <td>дата заказа</td>
            <td>маршрут</td>
            <td>дата белета</td>
            <td>цена</td>
            <td>ваш комментарий</td>
        </tr>


        <c:forEach var="history" items="${historyOrder}">

            <tr>
                <td class="item_id"><c:out value="${history.id}"/></td><td class="status" title="click for change status">
                <form action="changeStatus" method="post">
                    <button class="status_btn"><c:out value="${history.statusOrder}"/></button>
                </form>
                </td>
                <td><c:out value="${history.dateOrder}"/></td>
                <td><c:out value="${history.ticketId.route}"/></td>
                <td><c:out value="${history.ticketId.dateTicket}"/></td>
                <td><c:out value="${history.ticketId.price}"/></td>
                <td><c:out value="${history.getComment().getCommentary()}"/></td>
            </tr>

        </c:forEach>

    </table>

</div>


<div id="my_button">
    <form action="general" method="get" >
        <p><input class="btn_back" type="submit" value="back"/></p>
    </form>
</div>


<button id="btn_comment" onclick="commentClick();"> add comment</button>

<button id="btn_order_send" onclick="orderSend();"> send order</button>

<div class="zatem">
    <div class="okno">

    </div>
</div>

<button class="closeBtn">
    Close
</button>

<script src="jsForHistory.js"></script>
</body>
</html>


