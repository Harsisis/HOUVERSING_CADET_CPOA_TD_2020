package main.dao.SQLDAO;

import main.dao.metiersDAO.CommandeDAO;
import main.pojo.Client;
import main.pojo.Commande;
import main.pojo.Produit;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

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
        try {
            java.sql.Connection connection = main.modele.Connection.getConnexion();
            String request = "DELETE FROM Commande WHERE id_commande = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.close();
            clearLigneCom(objet);
            return true;
        } catch (SQLException sqle) {
            System.out.println("Objet n'existe pas");
            System.out.println(sqle.getMessage());
            return false;
        }
    }

    @Override
    public Commande getById(int id) {
        Commande commande = null;
        LocalDate date = null;
        String id_client = null;
        try {
            java.sql.Connection connection = main.modele.Connection.getConnexion();
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
        try {
            java.sql.Connection connection = main.modele.Connection.getConnexion();
            String request = "SELECT * FROM Commande";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                Commande commande = new Commande();
                commande.setId(resultSet.getInt("id_commande"));
                commande.setDate(LocalDate.parse(resultSet.getString("date_commande"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                commande.setClient( SQLClientDAO.getInstance().getById(Integer.parseInt(resultSet.getString("id_client"))));
                String requestProduit = "SELECT * FROM Ligne_commande WHERE id_commande = ?";
                PreparedStatement preparedStatementProduit = connection.prepareStatement(requestProduit);
                preparedStatementProduit.setInt(1, commande.getId());
                ResultSet resultSetProduit = preparedStatementProduit.executeQuery();
                if (resultSetProduit.next()) {
                    commande.addProduit(SQLProduitDAO.getInstance().getById(resultSetProduit.getInt("id_produit")), resultSetProduit.getInt("quantite"));
                }
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
        try {
            java.sql.Connection connection = main.modele.Connection.getConnexion();
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
        try {
            java.sql.Connection connection = main.modele.Connection.getConnexion();
            String request = "UPDATE Commande SET date_commande = ?, id_client = ? WHERE id_commande = ? ";
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
        Statement statement = null;
        try {
            java.sql.Connection connection = main.modele.Connection.getConnexion();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM Commande";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.last();
            objet.setId(resultSet.getInt("id_commande"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            java.sql.Connection connection = main.modele.Connection.getConnexion();
            Iterator iterator = objet.getProduits().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry)iterator.next();
                String request = "INSERT INTO Ligne_commande(id_commande, id_produit, quantite, tarif_unitaire) VALUES(?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(request);
                preparedStatement.setInt(1, objet.getId());
                preparedStatement.setInt(2, ((Produit) pair.getKey()).getId());
                preparedStatement.setInt(3, (Integer) pair.getValue());
                preparedStatement.setFloat(4, ((Produit) pair.getKey()).getTarif());
                preparedStatement.execute();
            }
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }

    public boolean clearLigneCom(Commande objet){
        try {
            java.sql.Connection connection = main.modele.Connection.getConnexion();
            String request = "DELETE FROM Ligne_commande WHERE id_commande = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setInt(1, objet.getId());
            ps.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }
}

