
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ page errorPage="errors.jsp" %>

<html>
<head>
    <title>User mode</title>
    <link rel="stylesheet" type="text/css" href="style_for_general.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>

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

<p>if you wanna get pdf your ticket - click on route then click on close and in textArea will be your json ticket</p>

<div id="content">
    <p><textarea id="textArea" name="textComment" cols="30" rows="5"> </textarea></p>
</div>
<div id="editor"></div>
<button id="cmd">Generate PDF</button>


<script src="jsForGeneral.js"></script>
</body>
</html>
