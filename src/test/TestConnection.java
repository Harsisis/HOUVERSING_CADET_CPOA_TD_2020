package test;

import main.modele.Connection;
import org.junit.Test;

import java.sql.SQLException;
import static junit.framework.Assert.assertNotNull;


public class TestConnection {
    @Test
    public void TestConnection() throws SQLException {
        java.sql.Connection connection = Connection.getConnexion();
        assertNotNull(connection);
    }
}
