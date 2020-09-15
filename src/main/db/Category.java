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



//    public void all_cat(){
//        try{
//            java.sql.Connection connection = Connection.connect();
//            String requete = "SELECT * FROM Categorie";
//            ResultSetMetaData rsmd = res.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
//            while (res.next()) {
//                for (int i = 1; i <= columnsNumber; i++) {
//                    if (i > 1) System.out.print(",  ");
//                    String columnValue = res.getString(i);
//                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
//                }
//                System.out.println("");
//            }
//            if (res != null)
//                res.close();
//            if (requete != null)
//                requete.close();
//            if (Connection.getInstance() != null)
//                Connection.getInstance().getConnection().close();
//        } catch (SQLException sqle) {
//            System.out.println("Pb select" + sqle.getMessage());
//        }
//    }
}
