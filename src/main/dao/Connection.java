package main.dao;

import java.sql.*;
public class Connection{
    public static java.sql.Connection connect(){
        String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/cadet25u_CPOA";
        url += "?serverTimezone=Europe/Paris";
        String login = "cadet25u_appli";
        String pwd = "Gauthier541609";
        java.sql.Connection maConnexion = null;

        try{
            maConnexion = DriverManager.getConnection(url,login,pwd);
        } catch (SQLException sqle){
            System.out.println("Erreur connexion" + sqle.getMessage());
        }
        return maConnexion;
    }
}