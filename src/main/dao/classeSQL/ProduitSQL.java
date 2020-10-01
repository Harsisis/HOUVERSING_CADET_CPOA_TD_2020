package main.dao.classeSQL;

import main.dao.metiersDAO.ProduitDAO;
import main.pojo.Category;
import main.pojo.Produit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ProduitSQL implements ProduitDAO {
    private static ProduitSQL instance;
    Scanner scan = new Scanner(System.in);

    private ProduitSQL() {
    }

    public static ProduitSQL getInstance() {
        if (instance == null) {
            instance = new ProduitSQL();
        }
        return instance;
    }

    public void add_prod() {
        String name_prod = null;
        String description_prod = null;
        float price_prod = 0;
        String visual_prod = null;
        int categ = 1;
        System.out.println("Prompt the product name :\n");
        name_prod = scan.next();
        System.out.println("Prompt the product description :\n");
        description_prod = scan.next();
        System.out.println("Prompt the product price :\n");
        price_prod = scan.nextFloat();
        System.out.println("Prompt the product visual :\n");
        visual_prod = scan.next();
        System.out.println("Prompt the product categorie id :\n");
        categ = scan.nextInt();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "INSERT INTO Produit(nom, description, tarif, visuel, id_categorie) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, name_prod);
            ps.setString(2, description_prod);
            ps.setFloat(3, price_prod);
            ps.setString(4, visual_prod);
            ps.setInt(5, categ);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void edit_prod() {
        String name_prod = null;
        String description_prod = null;
        float price_prod = 0;
        String visual_prod = null;
        int categ = 1;
        int id_prod = 1;
        System.out.println("Which product would you like to change ?\n");
        id_prod = scan.nextInt();
        System.out.println("Prompt the product name :\n");
        name_prod = scan.next();
        System.out.println("Prompt the product description :\n");
        description_prod = scan.next();
        System.out.println("Prompt the product price :\n");
        price_prod = scan.nextFloat();
        System.out.println("Prompt the product visual :\n");
        visual_prod = scan.next();
        System.out.println("Prompt the product categorie id :\n");
        categ = scan.nextInt();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "UPDATE Produit SET nom = ?, description = ?, tarif = ?, visuel = ?, id_categorie = ? WHERE id_produit = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, name_prod);
            ps.setString(2, description_prod);
            ps.setFloat(3, price_prod);
            ps.setString(4, visual_prod);
            ps.setInt(5, categ);
            ps.setInt(6, id_prod);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void del_prod() {
        String id_prod = null;
        System.out.println("Which product would you like to delete ?\n");
        id_prod = scan.next();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "DELETE FROM Produit WHERE id_produit = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, id_prod);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL " + sqle.getMessage());
        }
    }

    public void all_prod() {
        java.sql.Connection connection = main.modele.Connection.connect();
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
                int categorie = resultSet.getInt("id_categorie");
                System.out.format("%s, %s, %s, %s, %s, %s\n", id, nom, description, tarif, visuel, categorie);
            }
            statement.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL " + sqle.getMessage());
        }
    }

    @Override
    public boolean delete(Produit objet) {
        int id = objet.getId();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "DELETE FROM Produit WHERE id_produit = ? ";
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
    public Produit getById(int id) {
        java.sql.Connection connection = main.modele.Connection.connect();
        Produit produit = null;
        String nom = null;
        String description = null;
        float tarif = 0f;
        String visuel = null;
        Category category;
        int idCategory = 2;

        try {
            String rNom = "SELECT nom FROM Produit WHERE id_produit = ?";
            PreparedStatement psNom = connection.prepareStatement(rNom);
            psNom.setInt(1, id);
            ResultSet rsNom = psNom.executeQuery();
            if (rsNom.next()) {
                nom = rsNom.getString("nom");
            }
            String rDescription = "SELECT description FROM Produit WHERE id_produit = ?";
            PreparedStatement psDescription = connection.prepareStatement(rDescription);
            psDescription.setInt(1, id);
            ResultSet rsDescription = psDescription.executeQuery();
            if (rsDescription.next()) {
                description = rsDescription.getString("description");
            }
            String rTarif = "SELECT tarif FROM Produit WHERE id_produit = ?";
            PreparedStatement psTarif = connection.prepareStatement(rTarif);
            psTarif.setInt(1, id);
            ResultSet rsTarif = psTarif.executeQuery();
            if (rsTarif.next()) {
                tarif = rsTarif.getFloat("tarif");
            }
            String rVisuel = "SELECT visuel FROM Produit WHERE id_produit = ?";
            PreparedStatement psVisuel = connection.prepareStatement(rVisuel);
            psVisuel.setInt(1, id);
            ResultSet rsVisuel = psVisuel.executeQuery();
            if (rsVisuel.next()) {
                visuel = rsVisuel.getString("visuel");
            }
            String rCategory = "SELECT id_categorie FROM Produit WHERE id_produit = ?";
            PreparedStatement psCategory = connection.prepareStatement(rCategory);
            psCategory.setInt(1, id);
            ResultSet rsCategory = psCategory.executeQuery();
            if (rsCategory.next()) {
                idCategory = rsCategory.getInt("id_categorie");
            }
            category = CategorySQL.getInstance().getById(idCategory);
            produit = new Produit(id, nom, description, tarif, visuel, category);
            connection.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return produit;
    }

    @Override
    public ArrayList<Produit> findAll() {
        ArrayList<Produit> produits = new ArrayList<Produit>();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "SELECT * FROM Produit";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                Produit produit = new Produit();
                produit.setId(rs.getInt("id_produit"));
                produit.setNom(rs.getString("nom"));
                produit.setDescription(rs.getString("description"));
                produit.setTarif(rs.getFloat("tarif"));
                produit.setVisuel(rs.getString("visuel"));

                int idCategorie = rs.getInt("id_categorie");
                Category category = CategorySQL.getInstance().getById(idCategorie);

                produit.setCategory(category);

                System.out.println(produit);
                produits.add(produit);
            }
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return produits;
    }

    @Override
    public boolean create(Produit objet) {
        int id_category = 0;
        java.sql.Connection connection = main.modele.Connection.connect();
        String nom = objet.getNom();
        String description = objet.getDescription();
        float tarif = objet.getTarif();
        String visuel = objet.getVisuel();
        Category category = objet.getCategory();
        if (category != null)
            id_category = category.getId();
        try{
            String rAdd = ("INSERT INTO Produit(nom, description, tarif, visuel, id_categorie) VALUES(?, ?, ?, ?, ?)");
            PreparedStatement ps = connection.prepareStatement(rAdd);
            ps.setString(1, nom);
            ps.setString(2, description);
            ps.setFloat(3, tarif);
            ps.setString(4, visuel);
            ps.setInt(5, id_category);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Produit objet) {
        //je pense qu'il ne récupère pas l'objet, il en cherche un autre
        int id_prod = objet.getId();

        System.out.println("Prompt the product name :\n");
        String nom = scan.next();
        System.out.println("Prompt the product description :\n");
        String description = scan.next();
        System.out.println("Prompt the product price :\n");
        Float tarif = Float.parseFloat(scan.next());
        System.out.println("Prompt the product visual :\n");
        String visuel = scan.next();
        System.out.println("Prompt the product category :\n");
        int id_category = scan.nextInt();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "UPDATE Produit SET nom = ?, description = ?, tarif = ?, visuel = ?, id_categorie = ? WHERE id_produit = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, nom);
            ps.setString(2, description);
            ps.setFloat(3, tarif);
            ps.setString(4, visuel);
            ps.setInt(5, id_category);
            ps.setInt(6, id_prod);
            ps.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }
}