import entity.Ticket;
import service.JDBCServiceGeneral;
import service.JDBCServiceOrder;
import service.JDBCServiceTicket;
import entity.Order;

public class Main {
    public static void main(String[] args) {

        JDBCServiceTicket serviceTicket = new JDBCServiceTicket();

        for (Ticket q : serviceTicket.getAllTicket()) {
            System.out.println(q);
        }

//        JDBCServiceOrder serviceOrder = new JDBCServiceOrder();
//        for (Order q : serviceOrder.getAllOrderForUser("admin@mail.ru")){
//            System.out.println(q);
//        }



    }
}
