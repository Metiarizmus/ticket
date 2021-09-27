package ServiceJDBC;

import connectDB.DBConnection;
import connectDB.PropertyInf;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCServiceComment {

    private final Connection daoFactory = DBConnection.getInstance().getConnection();

    public boolean addCommentInDB(String textCommentary, int idOrderForComment) {
        try (Connection connection = daoFactory) {
            try (PreparedStatement statement = connection.prepareStatement(new PropertyInf().getSqlQuery().getProperty("INSERT_COMMENT"))) {

                String[] s = new String[]{textCommentary, String.valueOf(idOrderForComment)};

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



}
