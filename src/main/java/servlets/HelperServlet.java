package servlets;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "helperData", value = "/helperData")
public class HelperServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(HelperServlet.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        StringBuffer sb = new StringBuffer();
        String line = null;

        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        JSONObject jsonObject = new JSONObject(sb.toString());

        int id_order = Integer.parseInt(jsonObject.getString("id_order"));
        System.out.println("id order" + id_order);

//        int id_order_status = Integer.parseInt(jsonObject.getString("id_order_status"));
//        System.out.println("id order status + " + id_order_status);

        if (id_order!=0) {
            request.setAttribute("id_order", id_order);
            request.getRequestDispatcher("/general").forward(request, response);
        }

//        if (id_order_status!=0) {
//            request.setAttribute("id_order_status", id_order_status);
//            request.getRequestDispatcher("/changeStatus").forward(request, response);
//        }

    }
}
