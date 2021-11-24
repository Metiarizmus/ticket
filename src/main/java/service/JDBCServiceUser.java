package service;

import connect.DBConnection;
import connect.PropertyInf;
import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JDBCServiceUser {

    private PropertyInf propertyInf = new PropertyInf();
    private JDBCServiceGeneral<User> serviceGeneral = new JDBCServiceGeneral<>();

    private final String addUserInDB = propertyInf.getSqlQuery().getProperty("INSERT_USER");
    public boolean addUserInDB(User user) {

        List<String> userFields = new ArrayList<>();
        userFields.add(user.getEmail());
        userFields.add(user.getName());
        userFields.add(user.getPassword());

        return serviceGeneral.addInDB(userFields, addUserInDB);
    }

    public boolean getByEmail(String email, String password, String sql) {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);
                stmt.setString(2, password);
                try (ResultSet resultSet = stmt.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("good");
                        return true;
                    }
                }
            } catch (SQLException ignored) {

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


}

