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
        java.sql.Connection connection = Connection.connect();
        try{
            String requete = "INSERT INTO Produit(titre, visuel) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, name_prod);
            ps.setString(2, description_prod);
            ps.setString(3, price_prod);
            ps.setString(4, visual_prod);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL " + sqle.getMessage());
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
        java.sql.Connection connection = Connection.connect();
        try{
            String requete = "UPDATE Produit SET nom = ?, description = ?, tarif = ?, visuel = ? WHERE id_produit = ? ";
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, name_prod);
            ps.setString(2, description_prod);
            ps.setString(3, price_prod);
            ps.setString(4, visual_prod);
            ps.setString(5, id_prod);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }
    }

    public void del_prod(){
        String id_prod = null;
        System.out.println("Wich product would you delete ?\n");
        id_prod = scan.next();
        java.sql.Connection connection = Connection.connect();
        try{
            String requete = "DELETE FROM Produit WHERE id_produit = ? ";
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, id_prod);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }
    }
//
//    public void all_prod(){
//        try{
//            Statement requete = Connection.getInstance().getConnection().createStatement();
//            ResultSet res = requete.executeQuery("SELECT * FROM Produit");
//
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