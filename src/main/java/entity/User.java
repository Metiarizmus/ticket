package entity;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter @Setter private int id;
    @Getter @Setter private String email;
    @Getter @Setter private String name;
    @Getter @Setter private String password;


    public User() {

    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
