package dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ClientServiceTest {
    private ClientService clientService;
    private Connection connection;

    @BeforeEach
    public void beforeEach() throws SQLException {
        String connectionUrl = "jdbc:h2:mem:testDb;DB_CLOSE_DELAY=-1";
        new DataBaseInitService().initDB(connectionUrl);
        connection = DriverManager.getConnection(connectionUrl);
        clientService = new ClientService(connection);
    }

    @AfterEach
    public void afterEach() throws SQLException {
        connection.close();
    }

    @Test
    public void testThatClientCreatedCorrectly() throws SQLException {

        long id = clientService.create("TestName");
        String createdClient = clientService.getById(id);

        Assertions.assertEquals("TestName", createdClient);
    }
}