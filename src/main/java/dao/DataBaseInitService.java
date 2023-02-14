package dao;

import org.flywaydb.core.Flyway;

public class DataBaseInitService {
    public void initDB(String connectionUrl) {
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure()
                .dataSource(connectionUrl, null, null).load();
        // Start the migration
        flyway.migrate();
    }
}
