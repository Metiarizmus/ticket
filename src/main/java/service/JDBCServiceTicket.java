package service;

import connect.DBConnection;
import connect.PropertyInf;
import entity.StatusTicket;
import entity.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCServiceTicket {

    private PropertyInf propertyInf = new PropertyInf();
    private final String getAllTicket = propertyInf.getSqlQuery().getProperty("GET_ALL_TICKET");

    private JDBCServiceGeneral<Ticket> serviceGeneral = new JDBCServiceGeneral<>();

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

        return serviceGeneral.getAll(getAllTicket, Ticket.class,null);

    }




}
