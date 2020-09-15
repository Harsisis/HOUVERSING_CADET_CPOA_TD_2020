package main.db;
import java.util.Scanner;
import java.sql.*;

public class Product {
    Scanner scan = new Scanner(System.in);

    public void add_prod(){
        String name_prod = null;
        String description_prod = null;
        String price_prod = null;
        String visual_prod = null;
        System.out.println("Prompt the product name :\n");
        name_prod = scan.next();
        System.out.println("Prompt the product description :\n");
        description_prod = scan.next();
        System.out.println("Prompt the product price :\n");
        price_prod = scan.next();
        System.out.println("Prompt the product visual :\n");
        visual_prod = scan.next();
        try{
            Statement requete = Connection.getInstance().getConnection().createStatement();
            ResultSet res = requete.executeQuery("INSERT INTO Produit (nom, description, tarif, visuel) VALUES('" + name_prod + "','" + description_prod + "','" + price_prod + "','" + visual_prod + "')");
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

    public void edit_prod(){
        String name_prod = null;
        String description_prod = null;
        String price_prod = null;
        String visual_prod = null;
        String id_prod = null;
        System.out.println("Wich product would you change ?\n");
        id_prod = scan.next();
        System.out.println("Prompt the product name :\n");
        name_prod = scan.next();
        System.out.println("Prompt the product description :\n");
        description_prod = scan.next();
        System.out.println("Prompt the product price :\n");
        price_prod = scan.next();
        System.out.println("Prompt the product visual :\n");
        visual_prod = scan.next();
        try{
            Statement requete = Connection.getInstance().getConnection().createStatement();
            ResultSet res = requete.executeQuery("UPDATE Produit SET nom='" + name_prod + "', description='" + description_prod +"', tarif='" + price_prod + "', visuel='" + visual_prod +"' WHERE id_produit=" + id_prod);
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

    public void all_prod(){

    }
}