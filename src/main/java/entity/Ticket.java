package entity;

import enums.StatusTicket;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;


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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.forLanguageTag("ru"));
        simpleDateFormat.format(dateTicket);
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
