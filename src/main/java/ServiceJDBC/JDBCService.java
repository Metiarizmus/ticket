package ServiceJDBC;

import connectDB.DAOFactory;
import connectDB.PropertyInf;
import entity.Comment;
import entity.Order;
import entity.Ticket;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JDBCService {
    private final Connection daoFactory = DAOFactory.getInstance().getConnection();

    public boolean addUserInDB(User user, String sql) {

        try (Connection connection = daoFactory) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                String[] s = new String[]{user.getEmail(), user.getName(), user.getPassword()};

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

    public boolean addCommentInDB(Comment comment) {
        try (Connection connection = daoFactory) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getSqlQuery().getProperty("INSERT_COMMENT"))) {

                String[] s = new String[]{comment.getCommentary(), String.valueOf(comment.getOrder().getId())};

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

    public boolean addOrderInDB(Order order) {
        try (Connection connection = daoFactory) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getSqlQuery().getProperty("INSERT_ORDER"))) {
                String[] s = new String[]{String.valueOf(order.getStatusOrder()), String.valueOf(order.getDateOrder()),
                                          String.valueOf(order.getTicketId().getId()),String.valueOf(order.getUserId().getEmail())
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

    public Ticket getTicketById(int idOrder) {
        Ticket ticket = null;

        try (Connection connection = daoFactory) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getSqlQuery().getProperty("GET_TICKET_BY_ID"))) {
                statement.setInt(1, idOrder);
                try (ResultSet resultSet = statement.executeQuery()) {
                    ticket = new Ticket();
                    while (resultSet.next()) {
                        ticket.setRoute(resultSet.getString("route"));
                        ticket.setDateTicket(resultSet.getTimestamp("time"));
                        ticket.setPrice(resultSet.getDouble("price"));
                        ticket.setStatus(resultSet.getString("status"));

                    }
                    return ticket;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ticket;
    }

    public boolean getByEmail(String email, String password, String sql) {
        try (Connection conn = DAOFactory.getInstance().getConnection()) {
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

    public int getUserByEmail(String email) {
        int k = 0;

        try (Connection connection = daoFactory) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getSqlQuery().getProperty("GET_USER_BY_EMAIL"))) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {

                    if(resultSet.next()) {
                       k = resultSet.getInt("id");
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return k;
    }

    public List<Ticket> getAllTicket() {
        List<Ticket> list = new ArrayList<>();

        try (Connection connection = DAOFactory.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getSqlQuery().getProperty("GET_ALL_TICKET"))) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        list.add(getInfByTicket(result));
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

    public List<Comment> getAllComment() {
        List<Comment> list = new ArrayList<>();

        try (Connection connection = DAOFactory.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getSqlQuery().getProperty("GET_COMMENT"))) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        list.add(getInfByComment(result));
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

    public List<Order> getAllOrderForUser(String email) {
        List<Order> list = new ArrayList<>();

        try (Connection connection = DAOFactory.getInstance().getConnection()) {
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

    private Ticket getInfByTicket(ResultSet result) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(result.getInt("id"));
        ticket.setRoute(result.getString("route"));
        ticket.setDateTicket(result.getTimestamp("time"));
        ticket.setPrice(result.getDouble("price"));
        ticket.setStatus(result.getString("status"));
        return ticket;
    }


    private Order getInfByOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        Ticket ticket = new Ticket();
        User user = new User();
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

    private Comment getInfByComment(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        Order order = new Order();


        comment.setId(resultSet.getInt("id"));
        comment.setCommentary(resultSet.getString("message"));

        order.setId(resultSet.getInt("order_id"));
        order.setStatusOrder(resultSet.getString("status"));
        order.setDateOrder(resultSet.getTimestamp("date_order"));

        order.setUser_id(resultSet.getInt("user_id"));
        order.setTicket_id(resultSet.getInt("ticket_id"));
        comment.setOrder(order);

        return comment;
    }
    public static void main(String[] args) {

        List<Order> listOrder = (new JDBCService().getAllOrderForUser("admin@mail.ru"));

        System.out.println(listOrder.get(0).getComment().getCommentary());

    }
}

