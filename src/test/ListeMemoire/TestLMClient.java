package test.ListeMemoire;

import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.ClientDAO;
import main.pojo.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestLMClient {
    private ClientDAO dao;
    private EPersistence ePersistence = EPersistence.LISTEMEMORY;

    @Before
    public void setUp() {
        dao = DAOFactory.getDAOFactory(ePersistence).getClientDAO();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(dao.findAll());
    }

    @Test
    public void testIfClientIsASingleton() {

    }
    @Test
    public void testFindByID() {
        Client clientA = new Client();
        //create a clientA
        Assert.assertTrue(dao.create(clientA));
        //create a clientB
        Client clientB = dao.getById(clientA.getId());
        //test if clientB is an instance of the class Client
        Assert.assertTrue(clientB instanceof Client);
    }

    @Test
    public void testCreateClient() {
        //get the size of the array
        int size = dao.findAll().size();
        Client client = new Client();
        Assert.assertTrue(dao.create(client));
        //check if the array is incremented
        Assert.assertEquals(size + 1, dao.findAll().size());
    }

    @Test
    public void testUpdateClient() {
        Client clientA = new Client();
        clientA.setId(4);
        clientA.setNom("This is a name");
        //clientA is created
        Assert.assertTrue(dao.create(clientA));
        //test if the two products are the same
        Client clientB = dao.getById(clientA.getId());
        Assert.assertEquals(clientA,clientB);
        //modifying clientB
        clientB.setNom("Another one");
        Assert.assertTrue(dao.update(clientB));
        //verify
        Assert.assertEquals(clientB,dao.getById(clientA.getId()));
    }

    @Test
    public void testDeleteClient() {
        //get the size of the array
        Client client = new Client();
        Assert.assertTrue(dao.create(client));
        int size = dao.findAll().size();
        Assert.assertTrue(dao.delete(client));
        //check if the array is incremented
        Assert.assertEquals(size - 1, dao.findAll().size());
    }
}
