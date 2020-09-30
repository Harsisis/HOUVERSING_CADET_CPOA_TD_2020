package test;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;


public class TestConnection {
    @Test
    public void TestConnection() {
        java.sql.Connection connection = main.modele.Connection.connect();
        assertNotNull(connection);
    }
}
