package main.dao.SQLDAO;

import main.dao.metiersDAO.CategoryDAO;
import main.pojo.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class CategorySQLDAO implements CategoryDAO {
    Scanner scan = new Scanner(System.in);

    private static CategorySQLDAO instance;

    private CategorySQLDAO() {
    }

    public static CategorySQLDAO getInstance() {
        if (instance == null) {
            instance = new CategorySQLDAO();
        }
        return instance;
    }

    public void add_cat(){
        String title_cat = null;
        String visual_cat = null;
        System.out.println("Prompt the category title :\n");
        title_cat = scan.next();
        System.out.println("Prompt the category visual :\n");
        visual_cat = scan.next();
        java.sql.Connection connection = main.modele.Connection.connect();
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
        java.sql.Connection connection = main.modele.Connection.connect();
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
        java.sql.Connection connection = main.modele.Connection.connect();
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
        java.sql.Connection connection = main.modele.Connection.connect();
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

    @Override
    public boolean delete(Category objet) {
        int id = objet.getId();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "DELETE FROM Categorie WHERE id_categorie = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println("Objet n'existe pas");
            System.out.println(sqle.getMessage());
            return false;
        }
    }

    @Override
    public Category getById(int id) {
        java.sql.Connection connection = main.modele.Connection.connect();
        Category category = null;
        String titre = null;
        String visuel = null;
        try {
            String rTitre = "SELECT titre FROM Categorie WHERE id_categorie = ?";
            PreparedStatement psTitre = connection.prepareStatement(rTitre);
            psTitre.setInt(1, id);
            ResultSet rsTitre = psTitre.executeQuery();
            if (rsTitre.next()) {
                titre = rsTitre.getString("titre");
            }
            String rVisuel = "SELECT visuel FROM Categorie WHERE id_categorie = ?";
            PreparedStatement psVisuel = connection.prepareStatement(rVisuel);
            psVisuel.setInt(1, id);
            ResultSet rsVisuel = psVisuel.executeQuery();
            if (rsVisuel.next()) {
                visuel = rsVisuel.getString("visuel");
            }
            category = new Category(id, titre, visuel);
            connection.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return category;
    }

    @Override
    public ArrayList<Category> findAll() {
        ArrayList<Category> categories = new ArrayList<Category>();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "SELECT * FROM Categorie";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id_categorie"));
                category.setTitre(rs.getString("titre"));
                category.setVisuel(rs.getString("visuel"));

                System.out.println(category);
                categories.add(category);
            }
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return categories;
    }

    @Override
    public boolean create(Category objet) {
        String title_cat = null;
        String visual_cat = null;
        System.out.println("Prompt the category title :\n");
        title_cat = scan.next();
        System.out.println("Prompt the category visual :\n");
        visual_cat = scan.next();
        java.sql.Connection connection = main.modele.Connection.connect();
        try{
            String request = "INSERT INTO Categorie(titre, visuel) VALUES(?, ?)";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, title_cat);
            ps.setString(2, visual_cat);
            ps.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Category objet) {
        String title_cat = null;
        String visual_cat = null;
        int id_cat = objet.getId();
        System.out.println("Prompt the new category title :\n");
        title_cat = scan.next();
        System.out.println("Prompt the new category visual :\n");
        visual_cat = scan.next();
        java.sql.Connection connection = main.modele.Connection.connect();
        try{
            String request = "UPDATE Categorie SET titre = ?, visuel = ? WHERE id_categorie =  ?";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, title_cat);
            ps.setString(2, visual_cat);
            ps.setInt(3, id_cat);
            ps.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }
}
