package ServiceJDBC;

import connectDB.DBConnection;
import connectDB.PropertyInf;
import entity.Comment;
import entity.Order;
import entity.StatusOrder;
import entity.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCServiceOrder {
    private final Connection daoFactory = DBConnection.getInstance().getConnection();

    public boolean addOrderInDB(String email, int ticketId) {
        try (Connection connection = daoFactory) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getSqlQuery().getProperty("INSERT_ORDER"))) {
                String[] s = new String[]{String.valueOf(StatusOrder.ACCEPTED), String.valueOf(new Timestamp(System.currentTimeMillis())),
                        String.valueOf(ticketId),email
                };

                int k = 1;
                for (String value : s) {
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

    public List<Order> getAllOrderForUser(String email) {
        List<Order> list = new ArrayList<>();

        try (Connection connection = DBConnection.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getSqlQuery().getProperty("FULL_ORDER_WITH_COMMENT"))) {
                statement.setString(1,email);
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        list.add(getInfByOrder(result));
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

    private Order getInfByOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        Ticket ticket = new Ticket();
        Comment comment = new Comment();

        order.setId(resultSet.getInt("id"));
        order.setStatusOrder(resultSet.getString("status"));
        order.setDateOrder(resultSet.getTimestamp("date_order"));
        ticket.setRoute(resultSet.getString("route"));
        ticket.setDateTicket(resultSet.getTimestamp("time"));
        ticket.setPrice(resultSet.getDouble("price"));
        order.setTicketId(ticket);
        comment.setCommentary(resultSet.getString("message"));
        order.setComment(comment);

        return order;
    }
}