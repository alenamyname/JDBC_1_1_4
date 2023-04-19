package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/usersdb";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Be1Shoji090587";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            connection.setAutoCommit(false);
            //System.out.println("ok");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("not ok");
        }
        return connection;
    }
}
