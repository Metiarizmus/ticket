package connectDB;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    private static DAOFactory instance;
    private Connection connection;

    private static final PropertyInf inf = new PropertyInf();
    private static final String URL;
    private static final String LOGIN;
    private static final String PASSWORD;

    static {
        URL = inf.getConnectData().getProperty("URL");
        LOGIN = inf.getConnectData().getProperty("LOGIN");
        PASSWORD = inf.getConnectData().getProperty("PASSWORD");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private DAOFactory() {

        try {
            this.connection = DriverManager.getConnection(URL,LOGIN,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("Connection error");
        }
    }

    public Connection getConnection() {

        return connection;
    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new DAOFactory();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return instance;
    }


}