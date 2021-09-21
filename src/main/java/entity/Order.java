package entity;

import java.sql.Timestamp;

public class Order {

    private int id;
    private StatusOrder statusOrder;
    private Ticket ticketId;
    private Timestamp dateOrder;
    private User userId;

    private int ticket_id;
    private int user_id;

    public Order() {
    }

    public void getNowDate() {
        dateOrder = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = StatusOrder.valueOf(statusOrder);
    }

    public Ticket getTicketId() {
        return ticketId;
    }

    public void setTicketId(Ticket ticketId) {
        this.ticketId = ticketId;
    }

    public Timestamp getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Timestamp dateOrder) {
        this.dateOrder = dateOrder;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", statusOrder=" + statusOrder +
                ", ticketId=" + ticketId +
                ", dateOrder=" + dateOrder +
                ", userId=" + userId +
                ", ticket_id=" + ticket_id +
                ", user_id=" + user_id +
                '}';
    }

}
