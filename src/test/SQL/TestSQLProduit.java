package test.SQL;

import main.dao.SQLDAO.SQLCategorieDAO;
import main.dao.SQLDAO.SQLProduitDAO;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.ProduitDAO;
import main.pojo.Produit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class TestSQLProduit {

    private ProduitDAO dao;
    private EPersistence ePersistence = EPersistence.MYSQL;
    private java.sql.Connection connection;

    @Before
    public void setUp() {
        dao = DAOFactory.getDAOFactory(ePersistence).getProduitDAO();
        connection = main.modele.Connection.connect();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(dao.findAll());
    }

    @Test
    public void testProduitIsASingleton() {
        //GIVEN
        ProduitDAO produitDAO1 = SQLProduitDAO.getInstance();
        ProduitDAO produitDAO2 = SQLProduitDAO.getInstance();
        //THEN
        assertEquals(produitDAO1, produitDAO2);
    }

    @Test
    public void testCreateDeleteProduit() throws SQLException {
        int size = dao.findAll().size();
        int id;
        Produit produit = new Produit();
        produit.setNom("nom");
        produit.setDescription("description");
        produit.setVisuel("visuel");
        produit.setTarif(10f);
        produit.setCategory(SQLCategorieDAO.getInstance().getById(1));
        Assert.assertTrue(dao.create(produit));
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Produit";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        id = resultSet.getInt("id_produit");
        dao.delete(dao.getById(id));
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void testupdateProduit() throws SQLException {
        int size = dao.findAll().size();
        int idUp = 0;
        int idDel = 0;
        Produit produitA = new Produit();
        //getting generated key
        String request = ("INSERT INTO Produit(nom, description, tarif, visuel, id_categorie) VALUES(?, ?, ?, ?, ?)");
        PreparedStatement preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, "nom");
        preparedStatement.setString(2, "description");
        preparedStatement.setFloat(3, 10f);
        preparedStatement.setString(4, "visuel");
        preparedStatement.setInt(5, 1);
        preparedStatement.executeUpdate();
        ResultSet resultSetKey = preparedStatement.getGeneratedKeys();
        if (resultSetKey.next()) {
            idUp = resultSetKey.getInt(1);
        }
        //test if the two products are the same
        produitA.setId(idUp);
        Produit produitB = dao.getById(idUp);
        System.out.println("A : " + produitA.getId());
        System.out.println("B : " + produitB.getId());
        Assert.assertEquals(produitA, produitB);
        //modifying categoryB
        produitB.setNom("Another one");
        Assert.assertTrue(dao.update(produitB));
        //verify
        Assert.assertEquals(produitB,produitA);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Produit";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        idDel = resultSet.getInt("id_produit");
        dao.delete(dao.getById(idDel));
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void FindAllProduit() throws SQLException {
        int id;
        int size = dao.findAll().size();
        Produit produit = new Produit();
        produit.setNom("nom");
        produit.setDescription("description");
        produit.setVisuel("visuel");
        produit.setTarif(10f);
        produit.setCategory(SQLCategorieDAO.getInstance().getById(1));
        Assert.assertTrue(dao.create(produit));
        Assert.assertEquals(size + 1, dao.findAll().size());
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Produit";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        id = resultSet.getInt("id_produit");
        produit.setId(id);
        Assert.assertTrue(dao.findAll().contains(produit));
        dao.delete(produit);
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void GetById() throws SQLException {
        int size = dao.findAll().size();
        int idDel = 0;
        int id = 0;
        Produit produit = new Produit();
        //getting generated key
        String request = ("INSERT INTO Produit(nom, description, tarif, visuel, id_categorie) VALUES(?, ?, ?, ?, ?)");
        PreparedStatement preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, "nom");
        preparedStatement.setString(2, "description");
        preparedStatement.setFloat(3, 10f);
        preparedStatement.setString(4, "visuel");
        preparedStatement.setInt(5, 1);
        preparedStatement.executeUpdate();
        ResultSet resultSetKey = preparedStatement.getGeneratedKeys();
        if (resultSetKey.next()) {
            id = resultSetKey.getInt(1);
        }
        produit = dao.getById(id);
        Assert.assertNotNull(produit);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Produit";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        idDel = resultSet.getInt("id_produit");
        dao.delete(dao.getById(idDel));
        Assert.assertEquals(size, dao.findAll().size());
    }

}
