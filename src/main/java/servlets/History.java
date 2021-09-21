package servlets;

import ServiceJDBC.JDBCService;
import entity.Order;
import entity.Ticket;
import myLogger.Log;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "history", value = "/history")
public class History extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JDBCService service = new JDBCService();

        HttpSession session2 = request.getSession();
        String email = (String) session2.getAttribute("id_user");

        int k = service.getUserByEmail(email);
        Log.addLog(History.class.getName() + ": get email through session and get id user by it email");

        List<Order> listOrder = (service.getAllOrderForUser(k));
        Log.addLog(History.class.getName() + ": get all order for particular user");

        System.out.println("--------------------------------");
        //почему-то ListOrder содержит некорректную dateOrder, хотя если вызвать метод getAllOrderForUser например в методе main класса JDBCService данные будут корректными
        System.out.println(History.class.getName()+ " " + listOrder + "\n");
        System.out.println("--------------------------------");

        List<Integer> listIdOfTicket = new ArrayList<>();
        for (Order q : listOrder) {
            listIdOfTicket.add(q.getTicket_id());
        }
        Log.addLog(History.class.getName() + ": get all ticket_id in order");

        //я делаю так, потому что если я буду получать по одному билету в цикле у меня будет ошибка No operations allowed after connection closed.

        List<Ticket> listAllTicket = (service.getAllTicket());

        List<Ticket> listTicketById = new ArrayList<>();

        for (Ticket q : listAllTicket) {
            for (Integer qq : listIdOfTicket) {
                if(q.getId() == qq) {
                    listTicketById.add(q);
                }
            }
        }

        Log.addLog(History.class.getName() + ": get all ticket in a particular order");

        for (Order q : listOrder) {

            for (Ticket t : listTicketById){

                if(q.getTicket_id() == t.getId()) {

                    q.setTicketId(t);
                }
            }
        }

        Log.addLog(History.class.getName() + ": a ticket was added to the order");

        System.out.println(History.class.getName() + " full order " +listOrder);

        request.setAttribute("historyOrder", listOrder);



        getServletContext().getRequestDispatcher("/historyList.jsp").forward(request, response);
    }
}
