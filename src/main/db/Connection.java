package main.db;
import java.sql.*;

public class Connection {
    private static Connection instance;
    private java.sql.Connection connection;
    private String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/cadet25u_CPOA";
    private String username = "cadet25u_appli";
    private String password = "Gauthier541609";

    private Connection() {
        url += "?serverTimezone=Europe/Paris";
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection succesful");
        } catch (SQLException sqle) {
            System.out.println("Database connection creation failed : " + sqle.getMessage());
        }
    }

    public java.sql.Connection getConnection() {
        return connection;
    }
    public static Connection getInstance() throws SQLException {
        if (instance == null) {
            instance = new Connection();
        } else if (instance.getConnection().isClosed()) {
            instance = new Connection();
        }
        return instance;
    }
}
