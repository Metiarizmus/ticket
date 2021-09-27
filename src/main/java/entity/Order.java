package entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

public class Order {

    @Getter @Setter private int id;


    private StatusOrder statusOrder;
    @Getter @Setter private Ticket ticketId;
    @Getter @Setter private Timestamp dateOrder;
    @Getter @Setter private User userId;
    @Getter @Setter private Comment comment;

    @Getter @Setter private int ticket_id;
    @Getter @Setter private int user_id;

    public Order() {
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = StatusOrder.valueOf(statusOrder);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", statusOrder=" + statusOrder +
                ", ticketId=" + ticketId +
                ", dateOrder=" + dateOrder +
                ", userId=" + userId +
                ", comment=" + comment +
                ", ticket_id=" + ticket_id +
                ", user_id=" + user_id +
                '}';
    }


}
