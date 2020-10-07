package test.SQL;

import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.CategoryDAO;
import main.pojo.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestSQLCategorie {

    private CategoryDAO dao;
    private EPersistence ePersistence = EPersistence.MYSQL;

    @Before
    public void setUp() {
        dao = DAOFactory.getDAOFactory(ePersistence).getCategoryDAO();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(dao.findAll());
    }

    @Test
    public void testCreateCategory(){//pas de suppression
        int size = dao.findAll().size();
        Category category = new Category();
        category.setTitre("test");
        category.setVisuel("test.png");
        dao.create(category);
        Assert.assertEquals(size + 1, dao.findAll().size());
        category = dao.getById(category.getId());
        dao.delete(category);
    }

    @Test
    public void testDeleteCategory(){
        int size = dao.findAll().size();
        Category category = new Category();
        dao.create(category);
        category = dao.getById(category.getId());
        dao.delete(category);
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void testupdateCategory(){//pas de suppression
        int size = dao.findAll().size();
        Category category = new Category();
        category.setTitre("test");
        category.setVisuel("test.png");
        dao.create(category);
        Assert.assertEquals(size + 1, dao.findAll().size());
        Category categ = dao.getById(category.getId());
        System.out.println(categ.getId());
        dao.delete(category);
    }

    @Test
    public void FindAllCategory(){
        dao.findAll();
    }

    @Test
    public void GetById(){
        Category category = new Category();
        dao.create(category);
        category = dao.getById(category.getId());
        Assert.assertEquals(category, dao.getById(category.getId()));
        dao.delete(category);
    }

}
