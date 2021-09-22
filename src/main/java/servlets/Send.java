package servlets;

import ServiceJDBC.JDBCService;
import com.google.gson.Gson;
import entity.Order;
import tls.Sender;

import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "sendOrder", value = "/sendOrder")
public class Send extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCService service = new JDBCService();

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

        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String docType = "<!DOCTYPE html>";
        String title = "Send Email Demo";

        String sendEmailResultMessage = "Email is successfully sent!";
        writer.println(docType + "<html>" +
                "<head>" +
                "<title>" + title + "</title>" +
                "</head>" +
                "<body>" +
                "<h1>" + sendEmailResultMessage + "</h1>" +
                "</body>" +
                "</html>");

    }
}
