package servlets;

import ServiceJDBC.JDBCService;
import connectDB.DAOFactory;
import connectDB.PropertyInf;
import entity.Ticket;
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

    private int def = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Ticket> list = new JDBCService().getAllTicket();
        request.setAttribute("tickets", list);

        if (def!=0){
            Ticket orderTicket = new JDBCService().getTicketById(def);
            request.setAttribute("orderTicket", orderTicket);
            getServletContext().getRequestDispatcher("/general.jsp").forward(request, response);
        }else
        getServletContext().getRequestDispatcher("/general.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("application/json;charset=UTF-8");

        StringBuffer sb = new StringBuffer();
        String line = null;

        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null)
            sb.append(line);


        try {
            JSONObject jsonObject = new JSONObject(sb.toString());
            String id = jsonObject.getString("id_order");

            def = Integer.parseInt(id);



           /* RequestDispatcher dispatcher = request.getRequestDispatcher("general.jsp");
            request.setAttribute("orderTicket", orderTicket);
            dispatcher.forward(request, response);*/
            System.out.println(id);




        } catch (JSONException e) {
            //throw new IOException("Error parsing JSON request string");
            System.out.println("errrrrorr");
        }


    }

}
