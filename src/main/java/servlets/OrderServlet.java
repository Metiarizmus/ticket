package servlets;


import service.JDBCServiceTicket;

import service.JDBCServiceOrder;
import entity.*;
import exceptionMy.TicketNotAvailable;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "order", value = "/order")
public class OrderServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(OrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session1 = request.getSession();
        int k = (int) session1.getAttribute("id_order");

        Ticket orderTicket = new JDBCServiceTicket().getTicketById(k);
        orderTicket.setId(k);
        log.info(": get session id that we click on web and make Ticket by this id, id in orderServlet= " + k);
        System.out.println(orderTicket);

        if (orderTicket.getStatus().equals(StatusTicket.AVAILABLE)) {
            HttpSession session2 = request.getSession();
            String email = (String) session2.getAttribute("id_user");

            if(new JDBCServiceOrder().addOrderInDB(email,k)){
                log.info("create order and put it in db");
                System.out.println("order servlet order is add");
                response.sendRedirect("general");
            }

        } else {
            throw new TicketNotAvailable("sorry you cant order this ticket because it status is UNAVAILABLE");
        }

    }
}
