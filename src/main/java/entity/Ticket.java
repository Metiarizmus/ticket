package entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Ticket {

    @Getter @Setter private int id;
    @Getter @Setter private String route;
    private Timestamp dateTicket;
    @Getter @Setter private Double price;
    @Getter @Setter private StatusTicket status;

    public Timestamp getDateTicket() {
        return dateTicket;
    }

    public void setDateTicket(Timestamp dateTicket) {
       /* SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormat.format(dateTicket);*/
        this.dateTicket = dateTicket;
    }

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
