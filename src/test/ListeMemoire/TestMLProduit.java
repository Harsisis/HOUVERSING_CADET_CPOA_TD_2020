package test.ListeMemoire;

import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.ProduitDAO;
import main.pojo.Produit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMLProduit {

    private ProduitDAO dao;
    private EPersistence ePersistence = EPersistence.LISTEMEMORY;

    @Before
    public void setUp() {
        dao = DAOFactory.getDAOFactory(ePersistence).getProduitDAO();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(dao.findAll());
    }

    @Test
    public void testFindbyID() {
        //Produit produit
    }

    @Test
    public void testCreateProduit() {
        int size = dao.findAll().size();
        Produit produit = new Produit();
        Assert.assertTrue(dao.create(produit));
        Assert.assertEquals(size + 1, dao.findAll().size());
    }

    @Test
    public void testUpdateProduit() {
        Produit produitA = new Produit();
        produitA.setId(4);
        produitA.setDescription("This is a description");
        //produitA is created
        Assert.assertTrue(dao.create(produitA));
        //test if the two products are the same
        Produit produitB = dao.getById(produitA.getId());
        Assert.assertEquals(produitA,produitB);
        //modifying produitB
        produitB.setDescription("Another one");
        Assert.assertTrue(dao.update(produitB));
        //verify
        Assert.assertEquals(produitB,dao.getById(produitA.getId()));
    }
}
