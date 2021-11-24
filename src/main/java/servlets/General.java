package servlets;

import helper.BaseInServlets;
import service.JDBCServiceTicket;
import com.google.gson.Gson;
import entity.Ticket;
import org.apache.log4j.Logger;
import org.json.JSONObject;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;

@WebServlet(name = "General", value = "/general")
public class General extends HttpServlet {

    private static final Logger log = Logger.getLogger(General.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Ticket> list = new JDBCServiceTicket().getAllTicket();
        request.setAttribute("tickets", list);

        getServletContext().getRequestDispatcher("/general.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        BaseInServlets baseInServlets = new BaseInServlets();

        JSONObject jsonObject;

        BufferedReader br = request.getReader();

        jsonObject = baseInServlets.getRequest(br);


        int id_order = Integer.parseInt(jsonObject.getString("id_order"));
        System.out.println("id order in general = " + id_order);

        log.info("get id witch we get when click on ticket route, id= " + id_order);

        HttpSession session = request.getSession(true);
        session.setAttribute("id_order", id_order);

        log.info("create session with id");
        Ticket orderTicket = new JDBCServiceTicket().getTicketById(id_order);


        String json = new Gson().toJson(orderTicket);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        log.info("create the Ticket of object witch we make from bd and make a response to web");

    }
}
