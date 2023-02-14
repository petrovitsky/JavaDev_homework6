package dao;

import entity.ClientEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class ClientService {
    private final Connection connection;
    private PreparedStatement createClientSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement maxIdSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteSt;
    private PreparedStatement getAllClirntsSt;

    public ClientService(Connection connection) throws SQLException {
        this.connection = connection;
        createClientSt = connection.prepareStatement("INSERT INTO client (name) VALUES (?)");
        getByIdSt = connection.prepareStatement("SELECT name FROM CLIENT WHERE ID = ?");
        maxIdSt = connection.prepareStatement("SELECT MAX(id) AS maxId FROM CLIENT");
        updateSt = connection.prepareStatement("UPDATE CLIENT SET name =  ? WHERE id = ?");
        deleteSt = connection.prepareStatement("DELETE FROM CLIENT WHERE ID  = ?");
        getAllClirntsSt = connection.prepareStatement("SELECT * FROM CLIENT");
    }


    /*Передбач валідацію вхідних даних в методах класу ClientService.
    Якщо якісь вхідні дані некоректні
    (наприклад, ми намагаємось створити клієнта з занадто коротким чи довгим іменем)
    - відповідний метод має викидати Exception.*/


    /*додає нового клієнта з іменем name. Повертає ідентифікатор щойно створеного клієнта */
    public long create(String name) throws SQLException {
        if (name == null) {
            return -1;
        }
        nameValidator(name);

        createClientSt.setString(1, name);
        createClientSt.executeUpdate();

        long l;
        try (ResultSet resultSet = maxIdSt.executeQuery()) {
            resultSet.next();
            l = resultSet.getLong(1);
        }
        return l;
    }

    /* повертає назву клієнта з ідентифікатором id */
    public String getById(long id) throws SQLException {
        getByIdSt.setLong(1, id);

        String result = "No data is available";
        try (ResultSet resultSet = getByIdSt.executeQuery();) {
            resultSet.next();
            result = resultSet.getString("name");
        } catch (SQLException exception) {
            exception.printStackTrace();

        }
        return result;
    }

    /* встановлює нове ім'я name для клієнта з ідентифікатором id*/
    public void setName(long id, String name) throws SQLException {
        nameValidator(name);
        updateSt.setString(1, name);
        updateSt.setLong(2, id);
        updateSt.executeUpdate();
        updateSt.close();
    }

    /*видаляє клієнта з ідентифікатором id*/
    public void deleteById(long[] id) throws SQLException {
        for (int i = 0; i < id.length; i++) {
            deleteSt.setLong(1, id[i]);
            deleteSt.addBatch();
        }
        deleteSt.executeBatch();
        deleteSt.close();
    }

    /* повертає всіх клієнтів з БД у вигляді колекції об'єктів типу Client (цей клас створи сам, у ньому має бути 2 поля - id та name)*/
    public List<ClientEntity> listAll() throws SQLException {
        List<ClientEntity> clients = new ArrayList<>();
        ResultSet resultSet = getAllClirntsSt.executeQuery();
        while (resultSet.next()) {
            clients.add(new ClientEntity(resultSet.getLong(1), resultSet.getString(2)));
        }
        resultSet.close();
        return clients;
    }

    private void nameValidator (String name) {
        if (name.length() <= 2 || name.length() > 1000){
            throw new IllegalArgumentException("Name length should be between 2 and 1000 symbols");
        }
    }
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
