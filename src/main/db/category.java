package main.db;
import java.util.Scanner;
import java.sql.*;

public class category extends connection {
    Scanner scan = new Scanner(System.in);

    public void add_cat(){
        String title_cat = null;
        String visual_cat = null;
        System.out.println("Prompt the category title :\n");
        title_cat = scan.next();
        System.out.println("Prompt the category visual :\n");
        visual_cat = scan.next();
        try{
            Connection laConnexion = Connect();
            Statement requete = laConnexion.createStatement();
            ResultSet res = requete.executeQuery("INSERT INTO Categorie (titre, visuel) VALUES('" + title_cat + "','" + visual_cat + "')");
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }
    }

    public void edit_cat(){
        String title_cat = null;
        String visual_cat = null;
        String id_cat = null;
        System.out.println("Wich category would you change ?\n");
        id_cat = scan.next();
        System.out.println("Prompt the new category title :\n");
        title_cat = scan.next();
        System.out.println("Prompt the new category visual :\n");
        visual_cat = scan.next();
        try{
            Connection laConnexion = Connect();
            Statement requete = laConnexion.createStatement();
            ResultSet res = requete.executeQuery("UPDATE Categorie SET titre = '" + title_cat + "',visuel = '" + visual_cat + "' WHERE id_categorie = " + id_cat);
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }
    }

    public void all_cat(){

    }
}
