package test.SQL;

import main.dao.SQLDAO.SQLClientDAO;
import main.dao.fabrique.DAOFactory;
import main.modele.Connection;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.ClientDAO;
import main.pojo.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class TestSQLClient {
    private ClientDAO dao;
    private EPersistence ePersistence = EPersistence.MYSQL;

    @Before
    public void setUp() {
        dao = DAOFactory.getDAOFactory(ePersistence).getClientDAO();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(dao.findAll());
    }

    @Test
    public void testClientIsASingleton() {
        //GIVEN
        ClientDAO clientDAO1 = SQLClientDAO.getInstance();
        ClientDAO clientDAO2 = SQLClientDAO.getInstance();
        //THEN
        assertEquals(clientDAO1, clientDAO2);
    }

    @Test
    public void testCreateDeleteClient() throws SQLException {
        int size = dao.findAll().size();
        int id;
        Client client = new Client();
        client.setNom("nom");
        client.setPrenom("prenom");
        client.setIdentifiant("identifiant");
        client.setMdp("mdp");
        client.setAdrNumero("2");
        client.setAdrVoie("voie");
        client.setAdrCP("adrcp");
        client.setAdrVille("ville");
        client.setAdrPays("pays");
        Assert.assertTrue(dao.create(client));
        Statement statement = Connection.getConnexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Client";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        id = resultSet.getInt("id_client");
        dao.delete(dao.getById(id));
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void testupdateClient() throws SQLException {
        int size = dao.findAll().size();
        int idUp = 0;
        int idDel = 0;
        Client clientA = new Client();
        clientA.setNom("nom");
        clientA.setPrenom("prenom");
        clientA.setIdentifiant("identifiant");
        clientA.setMdp("mdp");
        clientA.setAdrNumero("2");
        clientA.setAdrVoie("voie");
        clientA.setAdrCP("adrcp");
        clientA.setAdrVille("ville");
        clientA.setAdrPays("pays");
        //getting generated key
        String request = ("INSERT INTO Client(nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        PreparedStatement preparedStatement = Connection.getConnexion().prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, clientA.getNom());
        preparedStatement.setString(2, clientA.getPrenom());
        preparedStatement.setString(3, clientA.getIdentifiant());
        preparedStatement.setString(4, clientA.getMdp());
        preparedStatement.setString(5, clientA.getAdrNumero());
        preparedStatement.setString(6, clientA.getAdrVoie());
        preparedStatement.setString(7, clientA.getAdrCP());
        preparedStatement.setString(8, clientA.getAdrVille());
        preparedStatement.setString(9, clientA.getAdrPays());
        preparedStatement.executeUpdate();
        ResultSet resultSetKey = preparedStatement.getGeneratedKeys();
        if (resultSetKey.next()) {
            idUp = resultSetKey.getInt(1);
        }
        //test if the two products are the same
        clientA.setId(idUp);
        Client clientB = dao.getById(idUp);
        System.out.println("A : " + clientA.getId());
        System.out.println("B : " + clientB.getId());
        Assert.assertEquals(clientA, clientB);
        //modifying categoryB
        clientB.setPrenom("Another one");
        System.out.println("CA " + clientA);
        System.out.println("CB " + clientB);
        //Assert.assertTrue(dao.update(clientB));
        //verify
        Assert.assertEquals(clientB,clientA);
        Statement statement = Connection.getConnexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Client";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        idDel = resultSet.getInt("id_client");
        dao.delete(dao.getById(idDel));
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void FindAllClient() throws SQLException {
        int id;
        int size = dao.findAll().size();
        Client client = new Client();
        client.setNom("test");
        client.setPrenom("test");
        client.setIdentifiant("test");
        client.setMdp("test");
        client.setAdrNumero("test");
        client.setAdrVoie("test");
        client.setAdrCP("test");
        client.setAdrVille("test");
        client.setAdrPays("test");
        dao.create(client);
        Assert.assertEquals(size + 1, dao.findAll().size());
        Statement statement = Connection.getConnexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Client";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        id = resultSet.getInt("id_client");
        client.setId(id);
        Assert.assertTrue(dao.findAll().contains(client));
        dao.delete(client);
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void GetById() throws SQLException {
        int size = dao.findAll().size();
        int idDel = 0;
        int id = 0;
        Client client = new Client();
        //getting generated key
        String request = ("INSERT INTO Client(nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        PreparedStatement preparedStatement = Connection.getConnexion().prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, "Test");
        preparedStatement.setString(2, "Test.png");
        preparedStatement.setString(3, "Test");
        preparedStatement.setString(4, "Test");
        preparedStatement.setString(5, "Test");
        preparedStatement.setString(6, "Test");
        preparedStatement.setString(7, "Test");
        preparedStatement.setString(8, "Test");
        preparedStatement.setString(9, "Test");
        preparedStatement.executeUpdate();
        ResultSet resultSetKey = preparedStatement.getGeneratedKeys();
        if (resultSetKey.next()) {
            id = resultSetKey.getInt(1);
        }
        client = dao.getById(id);
        Assert.assertNotNull(client);
        Statement statement = Connection.getConnexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Client";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        idDel = resultSet.getInt("id_client");
        dao.delete(dao.getById(idDel));
        Assert.assertEquals(size, dao.findAll().size());
    }

}
