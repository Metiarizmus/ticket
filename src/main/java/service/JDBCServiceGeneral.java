package service;

import connect.DBConnection;
import entity.Comment;
import entity.Order;
import enums.StatusTicket;
import entity.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCServiceGeneral<E> {

    public List<E> getAll(String sql, Class<E> e, String paramInSql) {
        List<E> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                if (paramInSql != null) {
                    statement.setString(1,paramInSql);
                }
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        if (e.equals(Ticket.class)){
                            list.add((E) getInfByTicket(result));
                        }

                        if (e.equals(Order.class)){
                            list.add((E) getInfByOrder(result));
                        }

                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (list.isEmpty()) {
            System.err.println("list is null");
        }
        return list;
    }

    public boolean addInDB(List<String> entityFields, String sql) {
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                int k = 1;
                for (String value : entityFields) {
                    statement.setString(k++, value);
                }
                int n = statement.executeUpdate();
                if (n > 0)
                    return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private static Order getInfByOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        Ticket ticket = new Ticket();
        Comment comment = new Comment();

        order.setId(resultSet.getInt("id"));
        order.setStatusOrder(resultSet.getString("status"));
        order.setDateOrder(resultSet.getString("date_order"));
        ticket.setRoute(resultSet.getString("route"));
        ticket.setDateTicket(resultSet.getTimestamp("time"));
        ticket.setPrice(resultSet.getDouble("price"));
        order.setTicketId(ticket);
        comment.setCommentary(resultSet.getString("message"));
        order.setComment(comment);

        return order;
    }

    private static Ticket getInfByTicket(ResultSet result) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(result.getInt("id"));
        ticket.setRoute(result.getString("route"));
        ticket.setDateTicket(result.getTimestamp("time"));
        ticket.setPrice(result.getDouble("price"));
        ticket.setStatus(StatusTicket.valueOf(result.getString("status")));
        return ticket;
    }

    public static void main(String[] args) {

        JDBCServiceGeneral serviceGeneral = new JDBCServiceGeneral();



    }
}
