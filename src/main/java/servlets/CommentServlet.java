package servlets;

import entity.Comment;
import entity.Order;
import service.JDBCServiceComment;

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

        String idOrderForComment = request.getParameter("id_comment");
        System.out.println("id comment " + idOrderForComment);

        String textComment = request.getParameter("textComment");
        System.out.println("text " + textComment);

        log.info("get text of comment from web to object");

        JDBCServiceComment service = new JDBCServiceComment();

        Comment comment = new Comment();
        comment.setCommentary(textComment);
        Order order = new Order();
        order.setId(Integer.parseInt(idOrderForComment));
        comment.setOrder(order);

        if (service.addCommentInDB(comment)) {
            log.info("add comment in db");
            System.out.println("comment add in db");
        }else {
            log.error("comment doesnt add in db");
        }


    }
}
