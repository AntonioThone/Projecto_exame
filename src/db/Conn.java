package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    private static final String URL = "jdbc:mysql://localhost:3306/euro";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
