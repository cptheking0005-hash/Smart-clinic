package com.smartclinic.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    // MySQL server connection (without database)
    private static final String SERVER_URL =
        "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC";

    // SmartClinic database connection
    private static final String DATABASE_URL =
        "jdbc:mysql://localhost:3306/smartclinic?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASS = "admin123";

    // Load MySQL JDBC Driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Loaded Successfully!");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(
                "MySQL Driver Not Found: " + e.getMessage()
            );
        }
    }

    // Create database automatically if it does not exist
    public static Connection getConnection() throws SQLException {

        try (
            Connection serverConnection =
                DriverManager.getConnection(SERVER_URL, USER, PASS);

            Statement statement =
                serverConnection.createStatement()
        ) {
            statement.executeUpdate(
                "CREATE DATABASE IF NOT EXISTS smartclinic"
            );
        }

        // Connect to SmartClinic database
        return DriverManager.getConnection(
            DATABASE_URL, USER, PASS
        );
    }
}