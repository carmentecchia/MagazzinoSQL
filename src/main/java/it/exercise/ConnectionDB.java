package it.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/sys";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connessione riuscita");
        } catch (SQLException e) {
            System.out.println("Errore di connessione: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Driver non trovato: " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connessione chiusa");
            } catch (SQLException e) {
                System.out.println("Errore nella chiusura della connessione: " + e.getMessage());
            }
        }
    }
}
