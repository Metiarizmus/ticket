package servlets;

import service.JDBCServiceUser;
import connect.PropertyInf;
import exceptionMy.NotEmailInSystem;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "LogInSystem", value = "/enterToSystem")
public class Login extends HttpServlet {

    private final PropertyInf propertyInf = new PropertyInf();
    private JDBCServiceUser service = new JDBCServiceUser();
    private static final Logger log = Logger.getLogger(Login.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("email " + email + " password: " + password);


        if(service.getByEmail(email,password,propertyInf.getSqlQuery().getProperty("GET_BY_EMAIL_AND_PASSWORD"))) {

            HttpSession session = request.getSession(true);
            session.setAttribute("id_user", email);
            log.info("create session with user by email = " + email);
            response.sendRedirect("general");
        }else {
            throw new NotEmailInSystem("there is no such email in the system or your password not correct");
        }
    }
}

