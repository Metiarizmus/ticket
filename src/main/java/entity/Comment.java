package entity;

public class Comment {

    private int id;
    private Order order;
    private String commentary;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", order=" + order +
                ", commentary='" + commentary + '\'' +
                '}';
    }
}
