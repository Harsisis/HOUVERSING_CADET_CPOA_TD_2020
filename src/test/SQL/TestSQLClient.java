package test.SQL;

import main.dao.ListMemoireDAO.ListMemoireClientDAO;
import main.dao.SQLDAO.ClientSQLDAO;
import main.dao.fabrique.DAOFactory;
import main.dao.fabrique.EPersistence;
import main.dao.metiersDAO.ClientDAO;
import main.pojo.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class TestSQLClient {

    private ClientDAO dao;
    private EPersistence ePersistence = EPersistence.MYSQL;

    @Before
    public void setUp() {
        dao = DAOFactory.getDAOFactory(ePersistence).getClientDAO();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(dao.findAll());
    }

    @Test
    public void testClientIsASingleton() {
        //GIVEN
        ClientDAO clientDAO1 = ClientSQLDAO.getInstance();
        ClientDAO clientDAO2 = ClientSQLDAO.getInstance();
        //THEN
        assertEquals(clientDAO1, clientDAO2);
    }

    @Test
    public void testCreateClient(){//pas de suppression
        int size = dao.findAll().size();
        Client Client = new Client();
        Client.setNom("test");
        Client.setPrenom("test2");
        dao.create(Client);
        Assert.assertEquals(size + 1, dao.findAll().size());
        Client = dao.getById(Client.getId());
        dao.delete(Client);
    }

    @Test
    public void testDeleteClient(){
        int size = dao.findAll().size();
        Client Client = new Client();
        dao.create(Client);
        Client = dao.getById(Client.getId());
        dao.delete(Client);
        Assert.assertEquals(size, dao.findAll().size());
    }

    @Test
    public void testupdateClient(){//pas de suppression
        int size = dao.findAll().size();
        Client Client = new Client();
        Client.setNom("test");
        Client.setPrenom("test.png");
        dao.create(Client);
        Assert.assertEquals(size + 1, dao.findAll().size());
        Client categ = dao.getById(Client.getId());
        System.out.println(categ.getId());
        dao.delete(Client);
    }

    @Test
    public void FindAllClient(){
        dao.findAll();
    }

    @Test
    public void GetById(){
        Client Client = new Client();
        Client.setId(4);
        Client.setNom("test");
        Client.setPrenom("test2");
        dao.create(Client);
        Client = dao.getById(4);
        Assert.assertEquals(Client, dao.getById(Client.getId()));
        dao.delete(Client);
    }

}
