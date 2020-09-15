package main.db;
import java.util.Scanner;
import java.sql.*;

public class Category {
    Scanner scan = new Scanner(System.in);

    public void add_cat(){
        String title_cat = null;
        String visual_cat = null;
        System.out.println("Prompt the category title :\n");
        title_cat = scan.next();
        System.out.println("Prompt the category visual :\n");
        visual_cat = scan.next();
        try{
            Statement requete = Connection.getInstance().getConnection().createStatement();
            res = requete.executeUpdate("INSERT INTO `Categorie` (`titre`, `visuel`) VALUES(title_cat, visual_cat)");
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
            Statement requete = Connection.getInstance().getConnection().createStatement();
            ResultSet res = requete.executeQuery("UPDATE Categorie SET titre = '" + title_cat + "',visuel = '" + visual_cat + "' WHERE id_categorie = " + id_cat);
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (Connection.getInstance().getConnection() != null)
                Connection.getInstance().getConnection().close();
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }
    }

    public void all_cat(){
        try{
            Statement requete = Connection.getInstance().getConnection().createStatement();
            ResultSet res = requete.executeQuery("SELECT * FROM Categorie");

            ResultSetMetaData rsmd = res.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (res.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = res.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
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
}
