package main.dao;
import java.util.Scanner;
import java.sql.*;

public class CategoryDao {
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
            String request = "INSERT INTO Categorie(titre, visuel) VALUES(?, ?)";
            PreparedStatement ps = connection.prepareStatement(request);
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
        System.out.println("Which category would you like to change ?\n");
        id_cat = scan.next();
        System.out.println("Prompt the new category title :\n");
        title_cat = scan.next();
        System.out.println("Prompt the new category visual :\n");
        visual_cat = scan.next();
        java.sql.Connection connection = Connection.connect();
        try{
            String request = "UPDATE Categorie SET titre = ?, visuel = ? WHERE id_categorie =  ?";
            PreparedStatement ps = connection.prepareStatement(request);
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
        System.out.println("Which category would you like to delete ?\n");
        id_cat = scan.next();
        java.sql.Connection connection = Connection.connect();
        try{
            String request = "DELETE FROM Categorie WHERE id_categorie = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, id_cat);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL " + sqle.getMessage());
        }
    }

    public void all_cat() {
        java.sql.Connection connection = Connection.connect();
        try {
            String request = "SELECT * FROM Categorie";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            while (resultSet.next()) {
                int id = resultSet.getInt("id_categorie");
                String titre = resultSet.getString("titre");
                String visuel = resultSet.getString("visuel");
                System.out.format("%s, %s, %s\n", id, titre, visuel);
            }
            statement.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL " + sqle.getMessage());
        }
    }
}
