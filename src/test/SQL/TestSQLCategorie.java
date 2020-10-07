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

    @Before
    public void setUp() {
        dao = DAOFactory.getDAOFactory(ePersistence).getCategorieDAO();
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
    public void testCreateCategorie() throws SQLException {//pas de suppression
        String id = null;
        int size = dao.findAll().size();
        Categorie categorie = new Categorie();
        categorie.setTitre("test");
        categorie.setVisuel("test.png");
        dao.create(categorie);
        java.sql.Connection connection = main.modele.Connection.connect();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT id_commande FROM Commande ORDER BY id_commande DESC LIMIT 1;");
        while (resultSet.next()) {
            id = resultSet.getString("id_commande");
        }
        connection.close();
        System.out.println("\n\n" + Integer.parseInt(id) + "\n");
        dao.delete(dao.getById(Integer.parseInt(id)));
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void testDeleteCategorie(){
        int size = dao.findAll().size();
        Categorie categorie = new Categorie();
        dao.create(categorie);
        categorie = dao.getById(categorie.getId());
        dao.delete(categorie);
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
