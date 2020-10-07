package test.ListeMemoire;

import main.dao.ListMemoireDAO.ListMemoireCategoryDAO;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.CategoryDAO;
import main.pojo.Category;
import main.pojo.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestMLCategorie {

    private CategoryDAO dao;
    private EPersistence ePersistence = EPersistence.LISTEMEMORY;

    @Before
    public void setUp() {
        dao = DAOFactory.getDAOFactory(ePersistence).getCategoryDAO();
        assertNotNull(dao);
        assertNotNull(dao.findAll());
    }

    @Test
    public void testCategoryIsASingleton() {
        //GIVEN
        CategoryDAO categoryDAO1 = ListMemoireCategoryDAO.getInstance();
        CategoryDAO categoryDAO2 = ListMemoireCategoryDAO.getInstance();

        //THEN
        assertEquals(categoryDAO1, categoryDAO2);
    }

    @Test
    public void testCreateCategorie() {
        int size = dao.findAll().size();
        Category category = new Category();
        Assert.assertTrue(dao.create(category));
        Assert.assertEquals(size + 1, dao.findAll().size());
    }

    @Test
    public void testUpdateCategory() {
        Category categoryA = new Category();
        categoryA.setId(4);
        categoryA.setTitre("Title A");
        //categoryA is created
        Assert.assertTrue(dao.create(categoryA));
        //test if the two products are the same
        Category categoryB = dao.getById(categoryA.getId());
        Assert.assertEquals(categoryA,categoryB);
        //modifying categoryB
        categoryB.setTitre("Another one");
        Assert.assertTrue(dao.update(categoryB));
        //verify
        Assert.assertEquals(categoryB,dao.getById(categoryA.getId()));
    }

    @Test
    public void testDeleteCategorie() {
        Category category = new Category();
        Assert.assertTrue(dao.create(category));
        int size = dao.findAll().size();
        Assert.assertTrue(dao.delete(category));
        Assert.assertEquals(size - 1, dao.findAll().size());
    }

    @Test
    public void testFindAll() {
        ArrayList categoryArrayList = dao.findAll();
        Category category = new Category();
        dao.create(category);
        System.out.println(categoryArrayList);
        Assert.assertTrue(categoryArrayList.contains(category));
    }

    @Test
    public void testFindbyID() {
        Category categoryFind = new Category();
        categoryFind.setId(1);
        Assert.assertTrue(dao.create(categoryFind));
        assertNotNull(dao.getById(1));
    }
}
