package test.ListeMemoire;

import main.pojo.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestLMClient {

    private int id = 1;
    Client client;

    @Before
    public void setUp() {
        Client client = null;

        //Client clientTest = ListMemoireClientDAO.getInstance().create(client);
    }
    @Test
    public void TestClientSQLGetById() {
        System.out.println(client);
        Assert.assertNotNull(client);
    }
}
