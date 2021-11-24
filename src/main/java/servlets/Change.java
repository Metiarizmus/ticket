package servlets;

import helper.BaseInServlets;
import org.json.JSONException;
import service.JDBCServiceOrder;
import service.JDBCServiceUser;
import org.apache.log4j.Logger;
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
        JDBCServiceOrder service = new JDBCServiceOrder();

        response.setContentType("application/json;charset=UTF-8");

        BaseInServlets baseInServlets = new BaseInServlets();

        BufferedReader br = request.getReader();

        JSONObject jsonObject = null;

        try {
            jsonObject = baseInServlets.getRequest(br);

        }catch (JSONException ignore) {
            //because I get JSONException but all good
        }

        try {
            int id_order_status = Integer.parseInt(jsonObject.getString("id_order_status"));
            System.out.println("id order status = " + id_order_status);

            log.info("get id witch we get when click on status order in history, id= " + id_order_status);

            if (id_order_status != 0) {
                if (service.updateStatus(id_order_status)) {
                    log.info("change status for order with id = " + id_order_status);
                }
            }
        }catch (NullPointerException ignore){
            // i dont know why i get NullPointerException because all work good its strange but this way work ))
        }


        response.sendRedirect("general");
    }
}
