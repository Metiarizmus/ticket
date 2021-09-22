package servlets;

import ServiceJDBC.JDBCService;
import entity.Order;
import myLogger.Log;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "changeStatus", value = "/changeStatus")
public class Change extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCService service = new JDBCService();

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
            Log.addLog(Change.class.getName() + ": get id witch we get when click on status order in history, id= " + id);
            k = Integer.parseInt(id);

            service.updateStatus(k);

        }catch (JSONException e){

        }

        response.sendRedirect("general");

    }
}
