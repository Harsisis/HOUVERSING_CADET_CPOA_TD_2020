package main.db;
import java.util.Scanner;
import java.sql.*;

public class Customer {
    Scanner scan = new Scanner(System.in);

    public void add_cust(){
        String name_cust = null;
        String surname_cust = null;
        System.out.println("Prompt the customer name :\n");
        name_cust = scan.next();
        System.out.println("Prompt the customer surname :\n");
        surname_cust = scan.next();
        java.sql.Connection connection = Connection.connect();
        try{
            String request = "INSERT INTO Categorie(nom, prenom) VALUES(?, ?)";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, name_cust);
            ps.setString(2, surname_cust);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }
    }

    public void edit_cust(){
        String name_cust = null;
        String surname_cust = null;
        String id_cust = null;
        System.out.println("Wich customer would you change ?\n");
        id_cust = scan.next();
        System.out.println("Prompt the customer name :\n");
        name_cust = scan.next();
        System.out.println("Prompt the customer surname :\n");
        surname_cust = scan.next();
        java.sql.Connection connection = Connection.connect();
        try{
            String request = "UPDATE Client SET nom = ?, prenom = ? WHERE id_client = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, name_cust);
            ps.setString(2, surname_cust);
            ps.setString(3, id_cust);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }
    }

    public void del_cust(){
        String id_cust = null;
        System.out.println("Wich customer would you delete ?\n");
        id_cust = scan.next();
        java.sql.Connection connection = Connection.connect();
        try{
            String requete = "DELETE FROM Client WHERE id_client = ? ";
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, id_cust);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }
    }
//
//    public void all_cust(){
//
//        try{
//            java.sql.Connection connection = Connection.connect();
//            String requete = "SELECT * FROM Client";
//            PreparedStatement ps = connection.prepareStatement(requete);
//
//            ResultSetMetaData rsmd = ps.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
//            while (.next()) {
//                for (int i = 1; i <= columnsNumber; i++) {
//                    if (i > 1) System.out.print(",  ");
//                    String columnValue = .getString(i);
//                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
//                }
//                System.out.println("");
//            }
//            connection.close();
//        } catch (SQLException sqle) {
//            System.out.println("Pb select" + sqle.getMessage());
//        }
//
//    }
}