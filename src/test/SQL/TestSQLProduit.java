package test.SQL;

import main.dao.SQLDAO.SQLProduitDAO;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.ProduitDAO;
import main.pojo.Categorie;
import main.pojo.Produit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSQLProduit {

    private ProduitDAO dao;
    private EPersistence ePersistence = EPersistence.MYSQL;

    @Before
    public void setUp() {
        dao = DAOFactory.getDAOFactory(ePersistence).getProduitDAO();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(dao.findAll());
    }

    @Test
    public void testProduitIsASingleton() {
        //GIVEN
        ProduitDAO produitDAO1 = SQLProduitDAO.getInstance();
        ProduitDAO produitDAO2 = SQLProduitDAO.getInstance();
        //THEN
        assertEquals(produitDAO1, produitDAO2);
    }

    @Test
    public void testCreateProduit(){// pas de suppresion
        int size = dao.findAll().size();
        Produit Produit = new Produit();
        Produit.setNom("test");
        Produit.setDescription("test");
        Produit.setTarif(17);
        Produit.setCategory(new Categorie());
        Produit.setVisuel("test.png");
        dao.create(Produit);
        Assert.assertEquals(size + 1, dao.findAll().size());
        Produit = dao.getById(Produit.getId());
        dao.delete(Produit);
    }

    @Test
    public void testDeleteProduit(){
        int size = dao.findAll().size();
        Produit Produit = new Produit();
        dao.create(Produit);
        Produit = dao.getById(Produit.getId());
        dao.delete(Produit);
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void testupdateProduit(){// pas de suppresion
        int size = dao.findAll().size();
        Produit Produit = new Produit();
        Produit.setNom("te");
        Produit.setDescription("test");
        Produit.setTarif(17);
        Produit.setCategory(new Categorie());
        Produit.setVisuel("test.png");
        dao.create(Produit);
        Assert.assertEquals(size + 1, dao.findAll().size());
        Produit = dao.getById(Produit.getId());
        dao.delete(Produit);
    }

    @Test
    public void FindAllProduit(){
        dao.findAll();
    }

    @Test
    public void GetById(){// pas de suppresion
        Produit Produit = new Produit();
        Produit.setNom("test");
        Produit.setDescription("test");
        Produit.setTarif(17);
        Produit.setCategory(new Categorie());
        Produit.setVisuel("test.png");
        dao.create(Produit);
        Assert.assertEquals(Produit, dao.getById(Produit.getId()));
        dao.delete(dao.getById(Produit.getId()));
    }

}
