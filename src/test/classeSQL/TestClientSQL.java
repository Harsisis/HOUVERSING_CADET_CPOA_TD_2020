package test.classeSQL;

import main.dao.classeSQL.ClientSQL;
import main.pojo.Client;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class TestClientSQL {

    private int id = 1;
    Client client;

    @Before
    public void setUp() {
        client = ClientSQL.getInstance().getById(id);
    }
    @Test
    public void TestClientSQLGetById() {
        System.out.println(client);
        assertNotNull(client);
    }
}
