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
        try{
            Statement requete = Connection.getInstance().getConnection().createStatement();
            ResultSet res = requete.executeQuery("INSERT INTO Client (nom, prenom) VALUES('" + name_cust + "','" + surname_cust + "')");
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (Connection.getInstance() != null)
                Connection.getInstance().getConnection().close();
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
        try{
            Statement requete = Connection.getInstance().getConnection().createStatement();
            ResultSet res = requete.executeQuery("UPDATE Client SET nom='" + name_cust + "', prenom='" + surname_cust + "' WHERE id_produit=" + id_cust);
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (Connection.getInstance() != null)
                Connection.getInstance().getConnection().close();
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }
    }

    public void all_cust(){

    }
}