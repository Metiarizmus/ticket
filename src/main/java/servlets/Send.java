package servlets;

import ServiceJDBC.JDBCService;
import com.google.gson.Gson;
import entity.Order;

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

        System.out.println(gson.toJson(orderForSend));

        //------------------------------------------------------------------

        // Recipient's email ID needs to be mentioned.
        String to = email;

        // Sender's email ID needs to be mentioned
        String from = "web@gmail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText(gson.toJson(orderForSend));

            // Send message
            Transport.send(message);
            String title = "Send Email";
            String res = "Sent message successfully....";
            String docType =
                    "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor = \"#f0f0f0\">\n" +
                    "<h1 align = \"center\">" + title + "</h1>\n" +
                    "<p align = \"center\">" + res + "</p>\n" +
                    "</body>"+
                    "</html>"
         );
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
