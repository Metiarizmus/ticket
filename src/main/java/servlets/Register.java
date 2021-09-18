package servlets;

import ServiceJDBC.JDBCService;
import connectDB.PropertyInf;
import entity.User;
import helperForData.EmailValidator;
import myException.EmailYetExist;
import myException.NotCorrectEmail;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "Registration", value = "/registration")
public class Register extends HttpServlet {

    private final JDBCService service = new JDBCService();
    private final PropertyInf propertyInf = new PropertyInf();
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
            if (service.addUserInDB(user, propertyInf.getSqlQuery().getProperty("INSERT"))) {

                HttpSession session = request.getSession(true);
                session.setAttribute("id_user", email);

                response.sendRedirect("general");

            } else {
                throw new EmailYetExist("your email yet exist in system");
            }

        } else {
            throw new NotCorrectEmail("your email not correct!");
        }


    }
}
