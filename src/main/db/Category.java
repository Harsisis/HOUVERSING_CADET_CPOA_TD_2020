package main.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Category {
    Scanner scan = new Scanner(System.in);

    public void add_cat(){
        String title_cat = null;
        String visual_cat = null;
        System.out.println("Prompt the category title :\n");
        title_cat = scan.next();
        System.out.println("Prompt the category visual :\n");
        visual_cat = scan.next();
        java.sql.Connection connection = Connection.connect();
        try{
            String requete = "INSERT INTO Categorie(titre, visuel) VALUES(?, ?)";
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, title_cat);
            ps.setString(2, visual_cat);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL " + sqle.getMessage());
        }
    }

    public void edit_cat(){
        String title_cat = null;
        String visual_cat = null;
        String id_cat = null;
        System.out.println("Which category would you like change ?\n");
        id_cat = scan.next();
        System.out.println("Prompt the new category title :\n");
        title_cat = scan.next();
        System.out.println("Prompt the new category visual :\n");
        visual_cat = scan.next();
        java.sql.Connection connection = Connection.connect();
        try{
            String requete = "UPDATE Categorie SET titre = ?, visuel = ? WHERE id_categorie =  ?";
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, title_cat);
            ps.setString(2, visual_cat);
            ps.setString(3, id_cat);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL " + sqle.getMessage());
        }
    }

    public void del_cat(){
        String id_cat = null;
        System.out.println("Which category would you like delete ?\n");
        id_cat = scan.next();
        java.sql.Connection connection = Connection.connect();
        try{
            String requete = "DELETE FROM Categorie WHERE id_categorie =  ?";
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, id_cat);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL " + sqle.getMessage());
        }
    }

    public void all_cat(){
        try{
            String requete = Connection.getInstance().getConnection().createStatement();
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

        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }
    }
}
