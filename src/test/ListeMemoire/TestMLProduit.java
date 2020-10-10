package test.ListeMemoire;

import main.dao.ListMemoireDAO.ListMemoireProduitDAO;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.ProduitDAO;
import main.pojo.Client;
import main.pojo.Produit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestMLProduit {

    private ProduitDAO dao;
    private EPersistence ePersistence = EPersistence.LISTEMEMORY;

    @Before
    public void setUp() {
        dao = DAOFactory.getDAOFactory(ePersistence).getProduitDAO();
        assertNotNull(dao);
        assertNotNull(dao.findAll());
    }

    @Test
    public void testProduitIsASingleton() {
        //GIVEN
        ProduitDAO produitDAO1 = ListMemoireProduitDAO.getInstance();
        ProduitDAO produitDAO2 = ListMemoireProduitDAO.getInstance();
        //THEN
        assertEquals(produitDAO1, produitDAO2);
    }

    @Test
    public void testFindbyID() {
        Produit prodFind = new Produit();
        prodFind.setId(1);
        Assert.assertTrue(dao.create(prodFind));
        assertNotNull(dao.getById(1));
    }
    @Test
    public void testFindAll() {
        ArrayList produitArrayList = dao.findAll();
        Produit produit = new Produit();
        dao.create(produit);
        System.out.println(produitArrayList);
        Assert.assertTrue(produitArrayList.contains(produit));
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

    @Test
    public void TestDeleteProduit(){
        Produit produit = new Produit();
        Assert.assertTrue(dao.create(produit));
        int size = dao.findAll().size();
        Assert.assertTrue(dao.delete(produit));
        Assert.assertEquals(size - 1, dao.findAll().size());
    }
}
