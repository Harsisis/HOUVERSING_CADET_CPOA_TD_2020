package main.dao.SQLDAO;

import main.dao.metiersDAO.CategorieDAO;
import main.pojo.Categorie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLCategorieDAO implements CategorieDAO {
    private static SQLCategorieDAO instance;

    private SQLCategorieDAO() {
    }

    public static CategorieDAO getInstance() {
        if (instance == null) {
            instance = new SQLCategorieDAO();
        }
        return instance;
    }

    @Override
    public boolean delete(Categorie objet) {
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
    public Categorie getById(int id) {
        java.sql.Connection connection = main.modele.Connection.connect();
        Categorie categorie = null;
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
            categorie = new Categorie(id, titre, visuel);
            connection.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return categorie;
    }

    @Override
    public ArrayList<Categorie> findAll() {
        ArrayList<Categorie> categories = new ArrayList<Categorie>();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "SELECT * FROM Categorie";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(rs.getInt("id_categorie"));
                categorie.setTitre(rs.getString("titre"));
                categorie.setVisuel(rs.getString("visuel"));

                categories.add(categorie);
            }
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return categories;
    }

    @Override
    public boolean create(Categorie objet) {
        java.sql.Connection connection = main.modele.Connection.connect();
        try{
            String request = "INSERT INTO Categorie(titre, visuel) VALUES(?, ?)";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, objet.getTitre());
            ps.setString(2, objet.getVisuel());
            ps.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Categorie objet) {
        int id_cat = objet.getId();
        java.sql.Connection connection = main.modele.Connection.connect();
        try{
            String request = "UPDATE Categorie SET titre = ?, visuel = ? WHERE id_categorie =  ?";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, objet.getTitre());
            ps.setString(2, objet.getVisuel());
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
