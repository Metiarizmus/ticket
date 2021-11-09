import ServiceJDBC.JDBCServiceOrder;
import ServiceJDBC.JDBCServiceTicket;
import entity.Order;
import entity.Ticket;

public class Main {
    public static void main(String[] args) {

        JDBCServiceTicket serviceTicket = new JDBCServiceTicket();

//        for (Ticket q : serviceTicket.getAllTicket()){
//            System.out.println(q);
//        }

        JDBCServiceOrder serviceOrder = new JDBCServiceOrder();
        for (Order q : serviceOrder.getAllOrderForUser("admin@mail.ru")){
            System.out.println(q);
        }

    }
}
