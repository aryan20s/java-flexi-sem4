package movierating.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/movie?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "Enter Password here"; // Replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}