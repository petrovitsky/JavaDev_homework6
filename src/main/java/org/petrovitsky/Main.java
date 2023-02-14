package org.petrovitsky;

import dao.ClientService;
import dao.DataBaseInitService;
import dao.Database;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        new DataBaseInitService().initDB("jdbc:h2:./homework6");
        ClientService service = new ClientService(Database.getInstance().getConnection());
        System.out.println(service.listAll());
        service.create(null);
        service.setName(service.create("origin"), "Current");
        service.deleteById(new long[]{14,12});
        System.out.println(service.listAll());
        service.deleteById(new long[] {13,15,16,17,18,19,20});
        service.closeConnection();
    }
}