package entity;

import java.sql.Timestamp;

public class Ticket {

    private int id;
    private String route;
    private Timestamp dateTicket;
    private Double price;
    private StatusTicket status;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Timestamp getDateTicket() {
        return dateTicket;
    }

    public void setDateTicket(Timestamp dateTicket) {
        this.dateTicket = dateTicket;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status.name();
    }

    public void setStatus(String status) {
        this.status = StatusTicket.valueOf(status);
    }


}
