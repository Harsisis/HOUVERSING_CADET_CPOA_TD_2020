package main.dao.SQLDAO;

import main.dao.metiersDAO.CommandeDAO;
import main.pojo.Categorie;
import main.pojo.Client;
import main.pojo.Commande;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class SQLCommandeDAO implements CommandeDAO {
    @Override
    public boolean delete(Commande objet) {
        int id = objet.getId();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "DELETE FROM Commande WHERE id_commande = ? ";
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
    public Commande getById(int id) {
        java.sql.Connection connection = main.modele.Connection.connect();
        Commande commande = null;
        LocalDate date = null;
        String id_client = null;
        try {
            String rTitre = "SELECT date FROM Commande WHERE id_commande = ?";
            PreparedStatement psTitre = connection.prepareStatement(rTitre);
            psTitre.setInt(1, id);
            ResultSet rsTitre = psTitre.executeQuery();
            if (rsTitre.next()) {
                date = LocalDate.parse(rsTitre.getString("date"), DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
            }
            String rVisuel = "SELECT id_client FROM Commande WHERE id_commande = ?";
            PreparedStatement psVisuel = connection.prepareStatement(rVisuel);
            psVisuel.setInt(1, id);
            ResultSet rsVisuel = psVisuel.executeQuery();
            if (rsVisuel.next()) {
                id_client = rsVisuel.getString("id_client");
            }
            Client client = new Client();
            commande = new Commande(id, date, SQLClientDAO.getInstance().getById(Integer.parseInt(id_client)));
            connection.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return commande;
    }

    @Override
    public ArrayList<Commande> findAll() {
        ArrayList<Commande> commandes = new ArrayList<Commande>();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "SELECT * FROM Commande";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                Commande commande = new Commande();
                commande.setId(rs.getInt("id_categorie"));
                commande.setDate(LocalDate.parse(rs.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
                commande.setClient( SQLClientDAO.getInstance().getById(Integer.parseInt(rs.getString("id_client"))));
                System.out.println(commande);
                commandes.add(commande);
            }
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return commandes;
    }

    @Override
    public boolean create(Commande objet) {
        java.sql.Connection connection = main.modele.Connection.connect();
        try{
            String request = "INSERT INTO Commande(date, id_client) VALUES(?, ?)";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, String.valueOf(objet.getDate()));
            ps.setInt(2, objet.getClient().getId());
            ps.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Commande objet) {
        int id_com = objet.getId();
        java.sql.Connection connection = main.modele.Connection.connect();
        try{
            String request = "UPDATE Categorie SET date = ?, id_client = ? WHERE id_commande =  ?";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, String.valueOf(objet.getDate()));
            ps.setInt(2, objet.getClient().getId());
            ps.setInt(3, id_com);
            ps.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }    }
}
