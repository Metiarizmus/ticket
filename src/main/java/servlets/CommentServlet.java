package servlets;

import ServiceJDBC.JDBCServiceComment;
import connectDB.DBConnection;
import entity.Comment;
import entity.Order;
import myLogger.Log;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "comment", value = "/comment")
public class CommentServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(CommentServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idOrderForComment = request.getParameter("id_history_order");
        String textComment = request.getParameter("textComment");

        log.info("get text of comment from web to object");

        JDBCServiceComment service = new JDBCServiceComment();

        if (service.addCommentInDB(textComment, Integer.parseInt(idOrderForComment))) {
            log.info("add comment in db");
            System.out.println("comment add in db");
        }

        response.sendRedirect("general");
    }
}
