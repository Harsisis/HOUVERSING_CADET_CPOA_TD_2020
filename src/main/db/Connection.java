package main.db;
import java.sql.*;

public class Connection {
    private static Connection instance;
    private java.sql.Connection connection;
    private String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/cadet25u_CPOA?serverTimezone=Europe/Paris\"";
    private String username = "cadet25u_appli";
    private String password = "Gauthier541609";

    private Connection() throws SQLException{
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
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
