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
        String nom = null;
        String prenom = null;
        String identifiant = null;
        String mdp = null;
        String numero = null;
        String voie = null;
        String codePostal = null;
        String ville = null;
        String pays = null;

        try {
            String rNom = "SELECT nom FROM Client WHERE id_client = ?";
            PreparedStatement psNom = connection.prepareStatement(rNom);
            psNom.setInt(1, id);
            ResultSet rsNom = psNom.executeQuery();
            if(rsNom.next()) {
                nom = rsNom.getString("nom");
            }
            String rPrenom = "SELECT prenom FROM Client WHERE id_client = ?";
            PreparedStatement psPrenom = connection.prepareStatement(rPrenom);
            psPrenom.setInt(1, id);
            ResultSet rsPrenom = psPrenom.executeQuery();
            if(rsPrenom.next()) {
                prenom = rsPrenom.getString("prenom");
            }
            String rIdentifiant = "SELECT identifiant FROM Client WHERE id_client = ?";
            PreparedStatement psIdentifiant = connection.prepareStatement(rIdentifiant);
            psIdentifiant.setInt(1, id);
            ResultSet rsIdentifiant = psIdentifiant.executeQuery();
            if(rsIdentifiant.next()) {
                identifiant = rsIdentifiant.getString("identifiant");
            }
            String rMdp = "SELECT mot_de_passe FROM Client WHERE id_client = ?";
            PreparedStatement psMdp = connection.prepareStatement(rMdp);
            psMdp.setInt(1, id);
            ResultSet rsMdp = psMdp.executeQuery();
            if(rsMdp.next()) {
                mdp = rsMdp.getString("mot_de_passe");
            }
            String rNumero = "SELECT adr_numero FROM Client WHERE id_client = ?";
            PreparedStatement psNumero = connection.prepareStatement(rNumero);
            psNumero.setInt(1, id);
            ResultSet rsNumero = psNumero.executeQuery();
            if(rsNumero.next()) {
                numero = rsNumero.getString("adr_numero");
            }
            String rVoie = "SELECT adr_voie FROM Client WHERE id_client = ?";
            PreparedStatement psVoie = connection.prepareStatement(rVoie);
            psVoie.setInt(1, id);
            ResultSet rsVoie = psVoie.executeQuery();
            if(rsVoie.next()) {
                voie = rsVoie.getString("adr_voie");
            }
            String rCP = "SELECT adr_code_postal FROM Client WHERE id_client = ?";
            PreparedStatement psCP = connection.prepareStatement(rCP);
            psCP.setInt(1, id);
            ResultSet rsCP = psCP.executeQuery();
            if(rsCP.next()) {
                codePostal = rsCP.getString("adr_code_postal");
            }
            String rVille = "SELECT adr_ville FROM Client WHERE id_client = ?";
            PreparedStatement psVille = connection.prepareStatement(rVille);
            psVille.setInt(1, id);
            ResultSet rsVille = psVille.executeQuery();
            if(rsVille.next()) {
                ville = rsVille.getString("adr_ville");
            }
            String rPays = "SELECT adr_pays FROM Client WHERE id_client = ?";
            PreparedStatement psPays = connection.prepareStatement(rPays);
            psPays.setInt(1, id);
            ResultSet rsPays = psPays.executeQuery();
            if(rsPays.next()) {
                pays = rsPays.getString("adr_pays");
            }
            client = new Client(id, nom, prenom, identifiant, mdp, numero, voie, codePostal, ville, pays);
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
                client.setIdentifiant(rs.getString("identifiant"));
                client.setMdp(rs.getString("mot_de_passe"));
                client.setAdrNumero(rs.getString("adr_numero"));
                client.setAdrVoie(rs.getString("adr_voie"));
                client.setAdrCP(rs.getString("adr_code_postal"));
                client.setAdrVille(rs.getString("adr_ville"));
                client.setAdrPays(rs.getString("adr_pays"));
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
        String identifiant = objet.getIdentifiant();
        String mdp = objet.getMdp();
        String numero = objet.getAdrNumero();
        String voie = objet.getAdrVoie();
        String codePostal = objet.getAdrCP();
        String ville = objet.getAdrVille();
        String pays = objet.getAdrPays();
        //new ArrayList<Client>() = objet.getCommandeList();
        try{
            String rAdd = ("INSERT INTO Client(nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement ps = connection.prepareStatement(rAdd);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, identifiant);
            ps.setString(4, mdp);
            ps.setString(5, numero);
            ps.setString(6, voie);
            ps.setString(7, codePostal);
            ps.setString(8, ville);
            ps.setString(9, pays);
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
        String nom = objet.getNom();
        String prenom = objet.getPrenom();
        String identifiant = objet.getIdentifiant();
        String mdp = objet.getMdp();
        String numero = objet.getAdrNumero();
        String voie = objet.getAdrVoie();
        String codePostal = objet.getAdrCP();
        String ville = objet.getAdrVille();
        String pays = objet.getAdrPays();
        int id_client = objet.getId();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "UPDATE Client SET nom = ?, prenom = ?,identifiant = ?, mot_de_passe = ?, adr_numero = ?, adr_voie = ?, adr_code_postal = ?, adr_ville = ?, adr_pays = ? WHERE id_client = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, identifiant);
            ps.setString(4, mdp);
            ps.setString(5, numero);
            ps.setString(6, voie);
            ps.setString(7, codePostal);
            ps.setString(8, ville);
            ps.setString(9, pays);
            ps.setInt(10, id_client);
            ps.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }
}