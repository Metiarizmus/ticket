package servlets;

import service.JDBCServiceOrder;
import com.google.gson.Gson;
import connect.PropertyInf;
import entity.Order;
import org.apache.log4j.Logger;
import tls.Sender;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "sendOrder", value = "/sendOrder")
public class Send extends HttpServlet {

    private static final Logger log = Logger.getLogger(Send.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCServiceOrder service = new JDBCServiceOrder();

        int k = Integer.parseInt(request.getParameter("id_mail"));


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
        //------------------------------------------------------------------//

        String address = request.getParameter("address");

        String myEmail = new PropertyInf().getDataForEmail().getProperty("MY_EMAIL");
        Sender sender = new Sender();
        sender.send("your ticket order",jsonOrder,myEmail,address);
        log.info("sent order to email");
        response.sendRedirect("responseForEmail.jsp");

    }

}
