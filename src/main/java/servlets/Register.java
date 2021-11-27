package servlets;

import service.JDBCServiceUser;
import entity.User;
import helper.EmailValidator;
import exceptions.EmailYetExist;
import exceptions.NotCorrectEmail;
import org.apache.log4j.Logger;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "Registration", value = "/registration")
public class Register extends HttpServlet {

    private static final Logger log = Logger.getLogger(Register.class);


    private final JDBCServiceUser service = new JDBCServiceUser();
    private final EmailValidator validator = new EmailValidator();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean valid = validator.validate(email);

        if (valid) {
            User user = new User(email, login, password);
            if (service.addUserInDB(user)) {

                HttpSession session = request.getSession(true);
                session.setAttribute("id_user", email);
                log.info("create session with user by email = " + email);
                response.sendRedirect("general");

            } else {
                throw new EmailYetExist("your email yet exist in system");
            }

        } else {
            throw new NotCorrectEmail("your email not correct!");
        }


    }
}
