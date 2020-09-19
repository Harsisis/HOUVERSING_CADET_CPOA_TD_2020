package main.dao.classeDao;
import java.util.Scanner;
import java.sql.*;

public class ClientDao {
    Scanner scan = new Scanner(System.in);

    public void add_client(){
        String name_client = null;
        String surname_client = null;
        System.out.println("Prompt the clientomer name :\n");
        name_client = scan.next();
        System.out.println("Prompt the clientomer surname :\n");
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
        System.out.println("Which clientomer would you like to change ?\n");
        id_client = scan.next();
        System.out.println("Prompt the clientomer name :\n");
        name_client = scan.next();
        System.out.println("Prompt the clientomer surname :\n");
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
        System.out.println("Which clientomer would you like to delete ?\n");
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
}