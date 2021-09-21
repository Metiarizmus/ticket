package servlets;


import ServiceJDBC.JDBCService;

import entity.*;
import myException.TicketNotAvailable;
import myLogger.Log;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "order", value = "/order")
public class OrderServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session1 = request.getSession();
        String s = String.valueOf(session1.getAttribute("id_order"));
        int k = Integer.parseInt(s);

        Ticket orderTicket = new JDBCService().getTicketById(k);
        orderTicket.setId(k);
        Log.addLog(OrderServlet.class.getName() + ": get session id that we click on web and make Ticket by this id, id in orderServlet= " + k);
        System.out.println(orderTicket);

        if (orderTicket.getStatus().equals(String.valueOf(StatusTicket.AVAILABLE))) {
            HttpSession session2 = request.getSession();
            String email = (String) session2.getAttribute("id_user");

            User user = new User();
            user.setEmail(email);


            Order order = new Order();
            order.getNowDate();
            order.setTicketId(orderTicket);
            order.setUserId(user);
            order.setStatusOrder(String.valueOf(StatusOrder.ACCEPTED));

            if(new JDBCService().addOrderInDB(order)){
                Log.addLog(OrderServlet.class.getName() + ": create order and put it in db");

                System.out.println(OrderServlet.class.getName() + " " + order);

                response.sendRedirect("general");
            }

        } else {
            throw new TicketNotAvailable("sorry you cant order this ticket because it status is UNAVAILABLE");
        }




    }
}
