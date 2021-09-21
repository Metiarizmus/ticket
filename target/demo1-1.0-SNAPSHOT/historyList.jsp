<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ page errorPage="errors.jsp" %>
<html>
<head>
    <title>History</title>
    <link rel="stylesheet" type="text/css" href="style_for_history.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div id="history_mode">
    <table border="3">

        <tr>
            <td>id</td>
            <td>status order</td>
            <td>date order</td>
            <td>route</td>
            <td>date ticket</td>
            <td>price</td>
            <td>your comment</td>
        </tr>


        <c:forEach var="history" items="${historyOrder}">

            <tr>
                <td class="item_id"><c:out value="${history.id}"/></td><td class="status" title="click for change status"><c:out value="${history.statusOrder}"/>
                <div class="dropdown">
                    <form action="" method="post">
                        <button class="btn_accept" type="submit">Accept</button>
                        <button class="btn_close" type="submit">Close</button>
                    </form>
                </div>
                </td>
                <td><c:out value="${history.dateOrder}"/></td>
                <td><c:out value="${history.ticketId.route}"/></td>
                <td><c:out value="${history.ticketId.dateTicket}"/></td>
                <td><c:out value="${history.ticketId.price}"/></td>
            </tr>

        </c:forEach>

    </table>

</div>


<div id="my_button">
    <form action="general" method="post" >
        <p><input class="btn_back" type="submit" value="back"/></p>
    </form>
</div>


<button id="btn_comment" onclick="commentClick();"> add comment</button>

<div class="zatem">
    <div class="okno">

    </div>
</div>

<%--<button class="closeBtn">
    Close
</button>--%>

<script src="jsForHistory.js"></script>
</body>
</html>


