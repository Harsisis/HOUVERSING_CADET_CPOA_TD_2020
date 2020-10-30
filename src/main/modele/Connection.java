package main.modele;

import main.dao.SQLDAO.SQLCategorieDAO;

import java.sql.*;
public class Connection{
    private static Connection instance;
    private java.sql.Connection connection;

    private Connection() {
        connect();
    }

    private void connect(){
        String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/cadet25u_CPOA";
        url += "?serverTimezone=Europe/Paris";
        String login = "cadet25u_appli";
        String pwd = "Gauthier541609";
        try{
            connection = DriverManager.getConnection(url,login,pwd);
        } catch (SQLException sqle){
            System.out.println("Erreur connexion " + sqle.getMessage());
        }
    }

    public static java.sql.Connection getConnexion() throws SQLException {
        if (instance == null) {
            instance = new Connection();
        }
        if (instance.connection.isClosed()) {
            instance.connect();
        }
        return instance.connection;
    }
}