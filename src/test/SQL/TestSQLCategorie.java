package test.SQL;

import main.dao.SQLDAO.SQLCategorieDAO;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.CategorieDAO;
import main.pojo.Categorie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class TestSQLCategorie {

    private CategorieDAO dao;
    private EPersistence ePersistence = EPersistence.MYSQL;
    private java.sql.Connection connection;

    @Before
    public void setUp() {
        dao = DAOFactory.getDAOFactory(ePersistence).getCategorieDAO();
        connection = main.modele.Connection.connect();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(dao.findAll());
    }

    @Test
    public void testCategorieIsASingleton() {
        //GIVEN
        CategorieDAO categorieDAO1 = SQLCategorieDAO.getInstance();
        CategorieDAO categorieDAO2 = SQLCategorieDAO.getInstance();
        //THEN
        assertEquals(categorieDAO1, categorieDAO2);
    }

    @Test
    public void testCreateDeleteCategorie() throws SQLException {
        int size = dao.findAll().size();
        int id;
        Categorie categorie = new Categorie();
        categorie.setTitre("test");
        categorie.setVisuel("test.png");
        Assert.assertTrue(dao.create(categorie));
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Categorie";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        id = resultSet.getInt("id_categorie");
        dao.delete(dao.getById(id));
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void testupdateCategorie() throws SQLException {
        int size = dao.findAll().size();
        int idUp = 0;
        int idDel = 0;
        Categorie categorieA = new Categorie();
        categorieA.setTitre("Title A");
        categorieA.setVisuel("Visuel A");
        //getting generated key
        String request = "INSERT INTO Categorie(titre, visuel) VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, categorieA.getTitre());
        preparedStatement.setString(2, categorieA.getVisuel());
        preparedStatement.executeUpdate();
        ResultSet resultSetKey = preparedStatement.getGeneratedKeys();
        if (resultSetKey.next()) {
            idUp = resultSetKey.getInt(1);
        }
        //test if the two products are the same
        categorieA.setId(idUp);
        Categorie categorieB = dao.getById(idUp);
        System.out.println("A : " + categorieA.getId());
        System.out.println("B : " + categorieB.getId());
        Assert.assertEquals(categorieA, categorieB);
        //modifying categoryB
        categorieB.setTitre("Another one");
        Assert.assertTrue(dao.update(categorieB));
        //verify
        Assert.assertEquals(categorieB,categorieA);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Categorie";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        idDel = resultSet.getInt("id_categorie");
        dao.delete(dao.getById(idDel));
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void FindAllCategorie() throws SQLException {
        int id;
        int size = dao.findAll().size();
        Categorie categorie = new Categorie();
        categorie.setTitre("test");
        categorie.setVisuel("test.png");
        dao.create(categorie);
        Assert.assertEquals(size + 1, dao.findAll().size());
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Categorie";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        id = resultSet.getInt("id_categorie");
        categorie.setId(id);
        Assert.assertTrue(dao.findAll().contains(categorie));
        dao.delete(categorie);
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void GetById() throws SQLException {
        int size = dao.findAll().size();
        int idDel = 0;
        int id = 0;
        Categorie categorie = new Categorie();
        //getting generated key
        String request = "INSERT INTO Categorie(titre, visuel) VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, "Test");
        preparedStatement.setString(2, "Test.png");
        preparedStatement.executeUpdate();
        ResultSet resultSetKey = preparedStatement.getGeneratedKeys();
        if (resultSetKey.next()) {
            id = resultSetKey.getInt(1);
        }
        categorie = dao.getById(id);
        Assert.assertNotNull(categorie);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Categorie";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        idDel = resultSet.getInt("id_categorie");
        dao.delete(dao.getById(idDel));
        Assert.assertEquals(size, dao.findAll().size());
    }

}
