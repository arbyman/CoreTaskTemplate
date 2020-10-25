package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/myDB?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "admin";
    private static final String PASSWORD = "vsegda17";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
//        try {
//            Class.forName(DRIVER);
//            System.out.println("Driver is found");
//            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
//                System.out.println("Success");
//                return connection;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
    }
}
