package servlets;

import ServiceJDBC.JDBCService;
import com.google.gson.Gson;
import connectDB.DAOFactory;
import connectDB.PropertyInf;
import entity.Ticket;
import myLogger.Log;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(name = "General", value = "/general")
public class General extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Ticket> list = new JDBCService().getAllTicket();
        request.setAttribute("tickets", list);

        getServletContext().getRequestDispatcher("/general.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int k = 0;
        response.setContentType("application/json;charset=UTF-8");

        StringBuffer sb = new StringBuffer();
        String line = null;

        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null)
            sb.append(line);

        try {
            JSONObject jsonObject = new JSONObject(sb.toString());
            String id = jsonObject.getString("id_order");
            Log.addLog(General.class.getName() + ": get id witch we get when click on ticket route, id= " + id);
            k = Integer.parseInt(id);

            HttpSession session = request.getSession(true);
            session.setAttribute("id_order", id);
            Log.addLog(General.class.getName() + ": create session with id");

            Ticket orderTicket = new JDBCService().getTicketById(k);

            String json = new Gson().toJson(orderTicket);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            Log.addLog(General.class.getName() + ": create the Ticket of object witch we make from bd and make a response to web");

        } catch (JSONException e) {
            System.out.println("errrrrorr");
        }



    }
}
