package servlets;

import ServiceJDBC.JDBCServiceUser;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "changeStatus", value = "/changeStatus")
public class Change extends HttpServlet {

    private static final Logger log = Logger.getLogger(Change.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCServiceUser service = new JDBCServiceUser();


        int k = (int) request.getAttribute("id_order_status");
        System.out.println(k + " = k");
        log.info("get id witch we get when click on status order in history, id= " + k);

        if (k!=0) {
            if( service.updateStatus(k)) {
                System.out.println("статус для k=" + k);
            }
        }


        response.sendRedirect("general");

    }
}
