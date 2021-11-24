package servlets;

import service.JDBCServiceTicket;
import entity.Ticket;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Guest", value = "/guest")
public class GuestMode extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Ticket> list = new JDBCServiceTicket().getAllTicket();
        request.setAttribute("tickets_for_guest", list);
        getServletContext().getRequestDispatcher("/guest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
