package service;

import connect.DBConnection;
import connect.PropertyInf;
import entity.Order;
import enums.StateProperties;
import enums.StatusOrder;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class JDBCServiceOrder {

    private JDBCServiceGeneral serviceGeneral = new JDBCServiceGeneral();

    private PropertyInf propertyInf = new PropertyInf();

    private final String getAllOrdersUser = propertyInf.getProperties(StateProperties.SQL).getProperty("FULL_ORDER_WITH_COMMENT");
    private final String addOrderInDB = propertyInf.getProperties(StateProperties.SQL).getProperty("INSERT_ORDER");

    public boolean addOrderInDB(String email, int ticketId) {
        Locale locale = new Locale("ru");

        List<String> orderFields = new ArrayList<>();
        orderFields.add(String.valueOf(StatusOrder.ACCEPTED));
        orderFields.add(new SimpleDateFormat("yyyy-MM-dd HH:mm", locale).format(Calendar.getInstance().getTime()));
        orderFields.add(String.valueOf(ticketId));
        orderFields.add(email);

        return serviceGeneral.addInDB(orderFields,addOrderInDB);
    }

    public List<Order> getAllOrderForUser(String email) {

        return serviceGeneral.getAll(getAllOrdersUser, Order.class, email);

    }

    public boolean updateStatus(int id) {

        int k = 0;
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getProperties(StateProperties.SQL).getProperty("UPDATE_STATUS_ORDER"))) {
                statement.setInt(1, id);
                k = statement.executeUpdate();

                if (k != 0) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;

    }


}
