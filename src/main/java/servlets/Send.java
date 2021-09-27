package servlets;

import ServiceJDBC.JDBCServiceOrder;
import ServiceJDBC.JDBCServiceUser;
import com.google.gson.Gson;
import entity.Order;
import tls.Sender;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "sendOrder", value = "/sendOrder")
public class Send extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCServiceOrder service = new JDBCServiceOrder();

        String idOrder = request.getParameter("id_history_order");
        int k  = Integer.parseInt(idOrder);

        HttpSession session2 = request.getSession();
        String email = (String) session2.getAttribute("id_user");

        List<Order> listOrder = (service.getAllOrderForUser(email));

        Order orderForSend = new Order();

        for (Order q : listOrder) {
            if(q.getId() == k){
                orderForSend = q;
            }
        }

        Gson gson = new Gson();

        String jsonOrder = gson.toJson(orderForSend);

        System.out.println(jsonOrder);
        //------------------------------------------------------------------

        String address = request.getParameter("address");
        String password = request.getParameter("password");

        Sender sender = new Sender(address,password);
        sender.send("your ticket order",jsonOrder,address,address);

        getServletContext().getRequestDispatcher("/responseForEmail.jsp").forward(request, response);

    }
}