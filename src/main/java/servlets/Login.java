package servlets;

import ServiceJDBC.JDBCServiceUser;
import connectDB.PropertyInf;
import myException.NotEmailInSystem;
import myLogger.Log;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "LogInSystem", value = "/enterToSystem")
public class Login extends HttpServlet {
    private final PropertyInf propertyInf = new PropertyInf();
    private JDBCServiceUser service = new JDBCServiceUser();


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
            Log.addLog(Login.class.getName() + ": create session with user by email = " + email);
            response.sendRedirect("general");
        }else {
            throw new NotEmailInSystem("there is no such email in the system or your password not correct");
        }
    }

}

