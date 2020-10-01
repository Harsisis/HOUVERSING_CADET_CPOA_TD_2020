package test.classeSQL;

import main.dao.ListMemoire.ListMemoireClientDAO;
import main.dao.classeSQL.ClientSQL;
import main.pojo.Client;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class TestListMemoireClientDAO {

    private int id = 1;
    Client client;

    @Before
    public void setUp() {
        client = ListMemoireClientDAO.getInstance().getById(id);
    }
    @Test
    public void TestClientSQLGetById() {
        System.out.println(client);
        assertNotNull(client);
    }
}
