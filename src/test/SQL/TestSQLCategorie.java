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
    public void testCreateCategory(){
        int size = dao.findAll().size();
        Category category = new Category();
        category.setTitre("test");
        category.setVisuel("test.png");
        dao.create(category);
        Assert.assertEquals(size + 1, dao.findAll().size());
        dao.delete(category);// il faut set les parametres
    }

}
