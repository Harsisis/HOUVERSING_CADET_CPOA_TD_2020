package main.dao.SQLDAO;

import main.dao.metiersDAO.ProduitDAO;
import main.pojo.Categorie;
import main.pojo.Produit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLProduitDAO implements ProduitDAO {
    private static SQLProduitDAO instance;

    private SQLProduitDAO() {
    }

    public static ProduitDAO getInstance() {
        if (instance == null) {
            instance = new SQLProduitDAO();
        }
        return instance;
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
        Categorie categorie;
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
            categorie = SQLCategorieDAO.getInstance().getById(idCategory);
            produit = new Produit(id, nom, description, tarif, visuel, categorie);
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
                Categorie categorie = SQLCategorieDAO.getInstance().getById(idCategorie);

                produit.setCategory(categorie);

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
        Categorie categorie = objet.getCategory();
        if (categorie != null)
            id_category = categorie.getId();
        try{
            String rAdd = ("INSERT INTO Produit(nom, description, tarif, visuel, id_categorie) VALUES(?, ?, ?, ?, ?)");
            PreparedStatement ps = connection.prepareStatement(rAdd);
            ps.setString(1, nom);
            ps.setString(2, description);
            ps.setFloat(3, tarif);
            ps.setString(4, visuel);
            ps.setInt(5, id_category);
            ps.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Produit objet) {
        //je pense qu'il ne récupère pas l'objet, il en cherche un autre
        int id_prod = objet.getId();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "UPDATE Produit SET nom = ?, description = ?, tarif = ?, visuel = ?, id_categorie = ? WHERE id_produit = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, objet.getNom());
            ps.setString(2, objet.getDescription());
            ps.setFloat(3, objet.getTarif());
            ps.setString(4, objet.getVisuel());
            ps.setInt(5, objet.getCategory().getId());
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