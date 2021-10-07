package servlets;

import ServiceJDBC.JDBCServiceUser;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "changeStatus", value = "/changeStatus")
public class Change extends HttpServlet {

    private static final Logger log = Logger.getLogger(Change.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCServiceUser service = new JDBCServiceUser();

        response.setContentType("application/json;charset=UTF-8");

        StringBuffer sb = new StringBuffer();
        String line = null;

        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        try {
            JSONObject jsonObject = new JSONObject(sb.toString());

            int id_order_status = Integer.parseInt(jsonObject.getString("id_order_status"));
            System.out.println("id order status + " + id_order_status);

            log.info("get id witch we get when click on status order in history, id= " + id_order_status);

            if (id_order_status!=0) {
                if(service.updateStatus(id_order_status)) {
                    System.out.println("статус для k=" + id_order_status);
                }
            }


        }catch (JSONException e) {
            System.err.println("error in change");
        }

        response.sendRedirect("general");
    }
}
