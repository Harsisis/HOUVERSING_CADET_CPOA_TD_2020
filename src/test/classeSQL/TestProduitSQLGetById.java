package test.classeSQL;

import main.dao.classeSQL.ProduitSQL;
import main.pojo.Produit;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class TestProduitSQLGetById {

    private int id = 2;
    Produit produit;

    @Before
    public void setUp() {
        produit = ProduitSQL.getInstance().getById(id);
    }
    @Test
    public void TestProduitSQLGetById() {
        System.out.println(produit);
        assertNotNull(produit);
    }
}
