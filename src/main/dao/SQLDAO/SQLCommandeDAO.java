package main.dao.SQLDAO;

import main.dao.metiersDAO.CommandeDAO;
import main.pojo.Client;
import main.pojo.Commande;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class SQLCommandeDAO implements CommandeDAO {

    private static SQLCommandeDAO instance;

    public static SQLCommandeDAO getInstance() {
        if (instance == null) {
            instance = new SQLCommandeDAO();
        }
        return instance;
    }

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
            String rDate = "SELECT date_commande FROM Commande WHERE id_commande = ?";
            PreparedStatement psDate = connection.prepareStatement(rDate);
            psDate.setInt(1, id);
            ResultSet rsDate = psDate.executeQuery();
            if (rsDate.next()) {
                date = rsDate.getDate("date_commande").toLocalDate();
                //date = LocalDate.parse(rsDate.getDate("date_commande"), DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
            }
            String rClient = "SELECT id_client FROM Commande WHERE id_commande = ?";
            PreparedStatement psClient = connection.prepareStatement(rClient);
            psClient.setInt(1, id);
            ResultSet rsVisuel = psClient.executeQuery();
            if (rsVisuel.next()) {
                id_client = rsVisuel.getString("id_client");
            }
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
                commande.setId(rs.getInt("id_commande"));
                commande.setDate(LocalDate.parse(rs.getString("date_commande"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                commande.setClient( SQLClientDAO.getInstance().getById(Integer.parseInt(rs.getString("id_client"))));
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
            String request = "INSERT INTO Commande(date_commande, id_client) VALUES(?, ?)";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setDate(1, Date.valueOf(objet.getDate()));
            ps.setInt(2, objet.getClient().getId());
            ps.executeUpdate();
            connection.close();
            ligneCom(objet);
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
            String request = "UPDATE Categorie SET date_commande = ?, id_client = ? WHERE id_commande =  ?";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, String.valueOf(objet.getDate()));
            ps.setInt(2, objet.getClient().getId());
            ps.setInt(3, id_com);
            ps.executeUpdate();
            clearLigneCom(objet);
            ligneCom(objet);
            connection.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }

    public boolean ligneCom(Commande objet){
        java.sql.Connection connection = main.modele.Connection.connect();

        try{
            for (int i=0; i<objet.getProduits().size(); i++){
                String request = "INSERT INTO Ligne_Commande(id_commande, id_produit, quantite, tarif_unitaire) VALUES(?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(request);
                ps.setInt(1, objet.getId());
                ps.setInt(2, (Integer) objet.getProduits().keySet().toArray()[i]);
                ps.setInt(3, objet.getProduits().get((Integer) objet.getProduits().keySet().toArray()[i]));
                ps.executeUpdate();
                connection.close();
            }
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }

    public boolean clearLigneCom(Commande objet){
        java.sql.Connection connection = main.modele.Connection.connect();

        try{
            for (int i=0; i<objet.getProduits().size(); i++){
                String request = "DELETE FROM Ligne_commande WHERE id_commande = ? ";
                PreparedStatement ps = connection.prepareStatement(request);
                ps.setInt(1, objet.getId());
                ps.executeUpdate();
                connection.close();
            }
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }
}

