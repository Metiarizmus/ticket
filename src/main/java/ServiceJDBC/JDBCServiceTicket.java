package ServiceJDBC;

import connectDB.DBConnection;
import connectDB.PropertyInf;
import entity.StatusTicket;
import entity.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JDBCServiceTicket {
    private final Connection daoFactory = DBConnection.getConnection();

    public Ticket getTicketById(int idOrder) {
        Ticket ticket = null;

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getSqlQuery().getProperty("GET_TICKET_BY_ID"))) {
                statement.setInt(1, idOrder);
                try (ResultSet resultSet = statement.executeQuery()) {
                    ticket = new Ticket();
                    while (resultSet.next()) {
                        ticket.setRoute(resultSet.getString("route"));
                        ticket.setDateTicket(resultSet.getTimestamp("time"));
                        ticket.setPrice(resultSet.getDouble("price"));
                        ticket.setStatus(StatusTicket.valueOf(resultSet.getString("status")));

                    }
                    return ticket;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ticket;
    }

    public List<Ticket> getAllTicket() {
        List<Ticket> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
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

    private Ticket getInfByTicket(ResultSet result) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(result.getInt("id"));
        ticket.setRoute(result.getString("route"));
        ticket.setDateTicket(result.getTimestamp("time"));
        ticket.setPrice(result.getDouble("price"));
        ticket.setStatus(StatusTicket.valueOf(result.getString("status")));
        return ticket;
    }

    public static void main(String[] args) {

    }
}
