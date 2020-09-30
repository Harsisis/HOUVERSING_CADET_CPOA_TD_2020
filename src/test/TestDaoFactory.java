package test;

import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.CategoryDAO;
import main.dao.metiersDAO.ProduitDAO;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class TestDaoFactory {

    private CategoryDAO categoryDAO;
    private ProduitDAO produitDAO;
    private DAOFactory factory;

    @Before
    public void setUp() {
        factory = DAOFactory.getDAOFactory(EPersistence.LISTEMEMORY);
        categoryDAO = factory.getCategoryDAO();
        produitDAO = factory.getProduitDAO();
    }

    @Test
    public void TestDaoMemoire() {
        assertNotNull(factory);
        assertNotNull(categoryDAO);
        assertNotNull(produitDAO);
    }
}