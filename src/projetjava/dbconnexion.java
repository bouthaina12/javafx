/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbconnexion {
     private static String HOST = "localhost"; // Change this to your phpMyAdmin host
    private static int PORT = 3306; // Default MySQL port
    private static String DB_NAME = "projet_java2"; // Change this to your database name in phpMyAdmin
    
    private static String USERNAME = "root"; // Change this to your MySQL username
    private static String PASSWORD = ""; // Change this to your MySQL password
    private static Connection connection;

    public static Connection getConnection() {
         try {
            // Ensure the MySQL JDBC driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create the connection URL
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;

            // Establish the connection
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            System.out.println("Connected to the database.");

            // Execute a SELECT query
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
        return connection;
    }

}
