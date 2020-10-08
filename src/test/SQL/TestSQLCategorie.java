package test.SQL;

import main.dao.SQLDAO.SQLCategorieDAO;
import main.dao.SQLDAO.SQLClientDAO;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.CategorieDAO;
import main.dao.metiersDAO.ClientDAO;
import main.pojo.Categorie;
import main.pojo.Produit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestSQLCategorie {

    private CategorieDAO dao;
    private EPersistence ePersistence = EPersistence.MYSQL;
    private java.sql.Connection connection;
    private Statement statement;

    @Before
    public void setUp() throws SQLException {
        dao = DAOFactory.getDAOFactory(ePersistence).getCategorieDAO();
        connection = main.modele.Connection.connect();
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
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
        int id = 0;
        int size = dao.findAll().size();
        Categorie categorie = new Categorie();
        categorie.setTitre("test");
        categorie.setVisuel("test.png");
        dao.create(categorie);
        String query = "SELECT * FROM Categorie";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.last();
        id = resultSet.getInt("id_categorie");
        Categorie categorie1 = dao.getById(id);
        dao.delete(categorie1);
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void testupdateCategorie(){//pas de suppression
        int size = dao.findAll().size();
        Categorie categorie = new Categorie();
        categorie.setTitre("test");
        categorie.setVisuel("test.png");
        dao.create(categorie);
        Assert.assertEquals(size + 1, dao.findAll().size());
        Categorie categ = dao.getById(categorie.getId());
        System.out.println(categ.getId());
        dao.delete(categorie);
    }

    @Test
    public void FindAllCategorie(){
        ArrayList categorieArrayList = dao.findAll();
        Categorie categorie = new Categorie();
        dao.create(categorie);
        System.out.println(categorieArrayList);
        Assert.assertTrue(categorieArrayList.contains(categorie));
    }

    @Test
    public void GetById(){
        Categorie categorie = new Categorie();
        dao.create(categorie);
        categorie = dao.getById(categorie.getId());
        Assert.assertEquals(categorie, dao.getById(categorie.getId()));
        dao.delete(categorie);
    }

}
