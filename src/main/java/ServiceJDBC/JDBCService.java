package ServiceJDBC;

import connectDB.DAOFactory;
import connectDB.PropertyInf;
import entity.StatusTicket;
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

    public void addOrderInDB(String idUser, int idOrder, String sql) {
        try (Connection connection = daoFactory) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    public List<Ticket> getAllTicket() {
        List<Ticket> list = new ArrayList<>();

        try (Connection connection = DAOFactory.getInstance().getConnection()){
            try (PreparedStatement statement =connection.prepareStatement("SELECT * FROM ticket.ticket") ){
                try (ResultSet result = statement.executeQuery()){
                    while (result.next()) {
                        list.add(getInfByTicket(result));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (list.isEmpty()){
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
}
