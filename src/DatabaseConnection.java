import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Description: QAP 4 - Database and File Handling - DatabaseConnection Class: Handles the connection to the database.
 * Author: Joseph Gallant
 * Date(s): July 20, 2025
 */

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Keyin2024";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("An error has occurred while connecting to the database.");
            e.printStackTrace();
        }

        return connection;
    }
}
