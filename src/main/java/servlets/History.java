package servlets;

import ServiceJDBC.JDBCService;
import entity.Order;
import entity.Ticket;
import myLogger.Log;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "history", value = "/history")
public class History extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JDBCService service = new JDBCService();

        HttpSession session2 = request.getSession();
        String email = (String) session2.getAttribute("id_user");

        List<Order> listOrder = (service.getAllOrderForUser(email));

        Log.addLog(History.class.getName() + ": get all order for particular user");

        request.setAttribute("historyOrder", listOrder);

        getServletContext().getRequestDispatcher("/historyList.jsp").forward(request, response);
    }
}
