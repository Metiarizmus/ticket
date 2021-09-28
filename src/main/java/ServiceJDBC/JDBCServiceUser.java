package ServiceJDBC;

import connectDB.DBConnection;
import connectDB.PropertyInf;
import entity.*;

import java.sql.*;



public class JDBCServiceUser {
    private final Connection daoFactory = DBConnection.getInstance().getConnection();

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

    public boolean getByEmail(String email, String password, String sql) {
        try (Connection conn = DBConnection.getInstance().getConnection()) {
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

    public boolean updateStatus(int id) {

        int k = 0;
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getSqlQuery().getProperty("UPDATE_STATUS_ORDER"))) {
                statement.setInt(1,id);
                 k = statement.executeUpdate();

                 if(k!=0) {
                     return true;
                 }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;

    }


}

