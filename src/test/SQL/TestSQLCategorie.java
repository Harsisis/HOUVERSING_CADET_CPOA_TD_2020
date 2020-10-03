package test.SQL;

import main.dao.classeSQL.CategorySQLDAO;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.CategoryDAO;
import main.dao.metiersDAO.ClientDAO;
import main.pojo.Category;
import org.junit.Assert;
import org.junit.Before;

public class TestSQLCategorie {

    private CategoryDAO dao;
    private EPersistence ePersistence = EPersistence.MYSQL;

    @Before
    public void setUp() {
        dao = DAOFactory.getDAOFactory(ePersistence).getCategoryDAO();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(dao.findAll());
    }

}