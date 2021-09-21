package servlets;

import ServiceJDBC.JDBCService;
import entity.Comment;
import entity.Order;
import myLogger.Log;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "comment", value = "/comment")
public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idOrderForComment = request.getParameter("id_history_order");
        String textComment = request.getParameter("textComment");

        Log.addLog(CommentServlet.class.getName() + " get text of comment from web to object");

        Comment comment = new Comment();
        Order order = new Order();
        order.setId(Integer.parseInt(idOrderForComment));

        comment.setCommentary(textComment);
        comment.setOrder(order);

        JDBCService service = new JDBCService();

        if (service.addCommentInDB(comment)) {
            Log.addLog(CommentServlet.class.getName() + " add comment in db");
            System.out.println("comment add in db");
        }

        response.sendRedirect("general");
    }
}
