package main.db;
import java.util.Scanner;
import java.sql.*;

public class Customer {
    Scanner scan = new Scanner(System.in);

    public void add_cust(){
        String name_cust = null;
        String surname_cust = null;
        System.out.println("Prompt the customer name :\n");
        name_cust = scan.next();
        System.out.println("Prompt the customer surname :\n");
        surname_cust = scan.next();
        java.sql.Connection connection = Connection.connect();
        try{
            String request = "INSERT INTO Client(nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays) VALUES(?, ?, 0, 0, 0, 0, 0, 0, 0)";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, name_cust);
            ps.setString(2, surname_cust);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL " + sqle.getMessage());
        }
    }

    public void edit_cust(){
        String name_cust = null;
        String surname_cust = null;
        String id_cust = null;
        System.out.println("Which customer would you like to change ?\n");
        id_cust = scan.next();
        System.out.println("Prompt the customer name :\n");
        name_cust = scan.next();
        System.out.println("Prompt the customer surname :\n");
        surname_cust = scan.next();
        java.sql.Connection connection = Connection.connect();
        try{
            String request = "UPDATE Client SET nom = ?, prenom = ? WHERE id_client = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, name_cust);
            ps.setString(2, surname_cust);
            ps.setString(3, id_cust);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL " + sqle.getMessage());
        }
    }

    public void del_cust(){
        String id_cust = null;
        System.out.println("Which customer would you like to delete ?\n");
        id_cust = scan.next();
        java.sql.Connection connection = Connection.connect();
        try{
            String request = "DELETE FROM Client WHERE id_client = ? ";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, id_cust);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("FAIL " + sqle.getMessage());
        }
    }
    public void all_cust() {
        java.sql.Connection connection = Connection.connect();
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