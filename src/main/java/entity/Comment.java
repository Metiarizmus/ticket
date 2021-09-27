package entity;

import lombok.Getter;
import lombok.Setter;

public class Comment {


    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private Order order;
    @Getter
    @Setter
    private String commentary;



    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", order=" + order +
                ", commentary='" + commentary + '\'' +
                '}';
    }
}
