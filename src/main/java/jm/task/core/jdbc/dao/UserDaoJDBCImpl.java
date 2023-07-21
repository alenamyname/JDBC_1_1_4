package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {
    }
    @Override
    public void createUsersTable() {
        myExecuteUpdate("CREATE TABLE IF NOT EXISTS "
                + "users"
                + " (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(255), " +
                "lastName VARCHAR(255), " +
                "age TINYINT);");

    }

    @Override
    public void dropUsersTable() {
        myExecuteUpdate("DROP TABLE IF EXISTS " + "users");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO " + "users" + " (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setByte(3, age);
                statement.executeUpdate();

                System.out.println("User с именем " + name + " " + lastName + " " + age + " " + "добавлен в базу данных");
            } catch (SQLException e) {
                myRollback(connection);
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        myExecuteUpdate("DELETE FROM " + "users" + "WHERE id = " + id + ";");
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM " + "users";
        List<User> users = new ArrayList<>();

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();

                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getInt(4));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        myExecuteUpdate("TRUNCATE TABLE " + "users");
    }


    private void myRollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void myExecuteUpdate(String sql) {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                myRollback(connection);
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

