package test.SQL;

import main.dao.SQLDAO.SQLCategorieDAO;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.CategoryDAO;
import main.pojo.Categorie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void testCategoryIsASingleton() {
        //GIVEN
        CategoryDAO categoryDAO1 = SQLCategorieDAO.getInstance();
        CategoryDAO categoryDAO2 = SQLCategorieDAO.getInstance();
        //THEN
        assertEquals(categoryDAO1, categoryDAO2);
    }

    @Test
    public void testCreateCategory(){//pas de suppression
        int size = dao.findAll().size();
        Categorie categorie = new Categorie();
        categorie.setTitre("test");
        categorie.setVisuel("test.png");
        dao.create(categorie);
        Assert.assertEquals(size + 1, dao.findAll().size());
        categorie = dao.getById(categorie.getId());
        dao.delete(categorie);
    }

    @Test
    public void testDeleteCategory(){
        int size = dao.findAll().size();
        Categorie categorie = new Categorie();
        dao.create(categorie);
        categorie = dao.getById(categorie.getId());
        dao.delete(categorie);
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void testupdateCategory(){//pas de suppression
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
    public void FindAllCategory(){
        dao.findAll();
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
