package test.listeMemoire;

import main.dao.ListMemoireDAO.ListMemoireCategorieDAO;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.CategorieDAO;
import main.pojo.Categorie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestMLCategorie {

    private CategorieDAO dao;
    private EPersistence ePersistence = EPersistence.LISTEMEMORY;

    @Before
    public void setUp() {
        dao = DAOFactory.getDAOFactory(ePersistence).getCategorieDAO();
        assertNotNull(dao);
        assertNotNull(dao.findAll());
    }

    @Test
    public void testCategoryIsASingleton() {
        //GIVEN
        CategorieDAO categorieDAO1 = ListMemoireCategorieDAO.getInstance();
        CategorieDAO categorieDAO2 = ListMemoireCategorieDAO.getInstance();

        //THEN
        assertEquals(categorieDAO1, categorieDAO2);
    }

    @Test
    public void testCreateCategorie() {
        int size = dao.findAll().size();
        Categorie categorie = new Categorie();
        Assert.assertTrue(dao.create(categorie));
        Assert.assertEquals(size + 1, dao.findAll().size());
    }

    @Test
    public void testUpdateCategory() {
        Categorie categorieA = new Categorie();
        categorieA.setId(4);
        categorieA.setTitre("Title A");
        //categoryA is created
        Assert.assertTrue(dao.create(categorieA));
        //test if the two products are the same
        Categorie categorieB = dao.getById(categorieA.getId());
        Assert.assertEquals(categorieA, categorieB);
        //modifying categoryB
        categorieB.setTitre("Another one");
        Assert.assertTrue(dao.update(categorieB));
        //verify
        Assert.assertEquals(categorieB,dao.getById(categorieA.getId()));
    }

    @Test
    public void testDeleteCategorie() {
        Categorie categorie = new Categorie();
        Assert.assertTrue(dao.create(categorie));
        int size = dao.findAll().size();
        Assert.assertTrue(dao.delete(categorie));
        Assert.assertEquals(size - 1, dao.findAll().size());
    }

    @Test
    public void testFindAll() {
        ArrayList categoryArrayList = dao.findAll();
        Categorie categorie = new Categorie();
        dao.create(categorie);
        System.out.println(categoryArrayList);
        Assert.assertTrue(categoryArrayList.contains(categorie));
    }

    @Test
    public void testFindbyID() {
        Categorie categorieFind = new Categorie();
        categorieFind.setId(1);
        Assert.assertTrue(dao.create(categorieFind));
        assertNotNull(dao.getById(1));
    }
}
