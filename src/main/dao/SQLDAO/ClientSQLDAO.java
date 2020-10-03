package main.dao.SQLDAO;

import main.dao.metiersDAO.ClientDAO;
import main.pojo.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientSQLDAO implements ClientDAO {
    Scanner scan = new Scanner(System.in);

    private static ClientSQLDAO instance;

    public static ClientSQLDAO getInstance() {
        if (instance == null) {
            instance = new ClientSQLDAO();
        }
        return instance;
    }

    public ClientSQLDAO() {
    }

    public void add_client(){
        String name_client = null;
        String surname_client = null;
        System.out.println("Prompt the client name :\n");
        name_client = scan.next();
        System.out.println("Prompt the client surname :\n");
        surname_client = scan.next();
        java.sql.Connection connection = main.modele.Connection.connect();
        try{
            String request = "INSERT INTO Client(nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays) VALUES(?, ?, 0, 0, 0, 0, 0, 0, 0)";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, name_client);
            ps.setString(2, surname_client);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL " + sqle.getMessage());
        }
    }

    public void edit_client(){
        String name_client = null;
        String surname_client = null;
        String id_client = null;
        System.out.println("Which client would you like to change ?\n");
        id_client = scan.next();
        System.out.println("Prompt the client name :\n");
        name_client = scan.next();
        System.out.println("Prompt the client surname :\n");
        surname_client = scan.next();
        java.sql.Connection connection = main.modele.Connection.connect();
        try{
            String request = "UPDATE Client SET nom = ?, prenom = ? WHERE id_client = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, name_client);
            ps.setString(2, surname_client);
            ps.setString(3, id_client);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL " + sqle.getMessage());
        }
    }

    public void del_client(){
        String id_client = null;
        System.out.println("Which client would you like to delete ?\n");
        id_client = scan.next();
        java.sql.Connection connection = main.modele.Connection.connect();
        try{
            String request = "DELETE FROM Client WHERE id_client = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, id_client);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL " + sqle.getMessage());
        }
    }
    public void all_client() {
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "SELECT * FROM Client";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            while (resultSet.next()) {
                int id = resultSet.getInt("id_client");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                System.out.format("%s, %s, %s\n", id, nom, prenom);
            }
            statement.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL" + sqle.getMessage());
        }
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
        }catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Client objet) {
        System.out.println("Prompt the new client family name :\n");
        String name_client = scan.next();
        System.out.println("Prompt the new client name :\n");
        String surname_client = scan.next();
        int id_client = objet.getId();
        java.sql.Connection connection = main.modele.Connection.connect();
        try {
            String request = "UPDATE Client SET nom = ?, prenom = ?,identifiant = 0, mot_de_passe = 0, adr_numero = 0, adr_voie = 0, adr_code_postal = 0, adr_ville = 0, adr_pays = 0 WHERE id_client = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, name_client);
            ps.setString(2, surname_client);
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