package entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

public class Ticket {

    @Getter @Setter private int id;
    @Getter @Setter private String route;
    @Getter @Setter private Timestamp dateTicket;
    @Getter @Setter private Double price;
    @Getter @Setter private StatusTicket status;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", route='" + route + '\'' +
                ", dateTicket=" + dateTicket +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
