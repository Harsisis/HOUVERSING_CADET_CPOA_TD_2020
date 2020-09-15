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
            String request = "INSERT INTO Produit(titre, visuel) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(request);
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
            String request = "UPDATE Produit SET nom = ?, description = ?, tarif = ?, visuel = ? WHERE id_produit = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
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
            String request = "DELETE FROM Produit WHERE id_produit = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, id_prod);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }
    }

    public void all_prod() {
        java.sql.Connection connection = Connection.connect();
        try {
            String request = "SELECT * FROM Produit";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            while (resultSet.next()) {
                int id = resultSet.getInt("id_produit");
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                String tarif = resultSet.getString("tarif");
                String visuel = resultSet.getString("visuel");
                System.out.format("%s, %s, %s, %s, %s\n", id, nom, description, tarif, visuel);
            }
            statement.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL" + sqle.getMessage());
        }
    }
}