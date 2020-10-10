package main.dao.SQLDAO;

import main.dao.metiersDAO.ClientDAO;
import main.pojo.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLClientDAO implements ClientDAO {
    private static SQLClientDAO instance;

    public static ClientDAO getInstance() {
        if (instance == null) {
            instance = new SQLClientDAO();
        }
        return instance;
    }

    public SQLClientDAO() {
    }

    @Override
    public boolean delete(Client objet) {
        int id = objet.getId();
        java.sql.Connection connection = main.modele.Connection.connect();
        try{
            String request = "DELETE FROM Client WHERE id_client = ? ";
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
    public Client getById(int id) {
        java.sql.Connection connection = main.modele.Connection.connect();
        Client client = null;
        String name_client = null;
        String surname_client = null;
        try {
            String rNom = "SELECT nom FROM Client WHERE id_client = ?";
            PreparedStatement psNom = connection.prepareStatement(rNom);
            psNom.setInt(1, id);
            ResultSet rsNom = psNom.executeQuery();
            if(rsNom.next()) {
                name_client = rsNom.getString("nom");
            }
            String rSurname = "SELECT prenom FROM Client WHERE id_client = ?";
            PreparedStatement psSurname = connection.prepareStatement(rSurname);
            psSurname.setInt(1, id);
            ResultSet rsSurname = psSurname.executeQuery();
            if(rsSurname.next()) {
                surname_client = rsSurname.getString("prenom");
            }
            client = new Client(id, name_client, surname_client);
            connection.close();

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return client;
    }

    @Override
    public ArrayList<Client> findAll() {
        ArrayList<Client> clients = new ArrayList<Client>();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "SELECT * FROM Client";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id_client"));
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                System.out.println(client);
                clients.add(client);
            }
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return clients;
    }

    @Override
    public boolean create(Client objet) {
        java.sql.Connection connection = main.modele.Connection.connect();
        String nom = objet.getNom();
        String prenom = objet.getPrenom();
        //new ArrayList<Client>() = objet.getCommandeList();
        try{
            String rAdd = ("INSERT INTO Client(nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays) VALUES(?, ?, 0, 0, 0, 0, 0, 0, 0)");
            PreparedStatement ps = connection.prepareStatement(rAdd);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.executeUpdate();
            connection.close();
            return true;
        }catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Client objet) {
        String surname_client = objet.getNom();
        String name_client = objet.getPrenom();
        int id_client = objet.getId();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "UPDATE Client SET nom = ?, prenom = ?,identifiant = 0, mot_de_passe = 0, adr_numero = 0, adr_voie = 0, adr_code_postal = 0, adr_ville = 0, adr_pays = 0 WHERE id_client = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, surname_client);
            ps.setString(2, name_client);
            ps.setInt(3, id_client);
            ps.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }
}