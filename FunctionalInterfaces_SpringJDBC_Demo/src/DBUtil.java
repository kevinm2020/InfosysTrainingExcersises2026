import java.sql.*;

public class DBUtil {

    public static final String DB_URL = "jdbc:sqlite:database.db";

    // Create the users table
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n" +
                     " id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                     " name TEXT NOT NULL,\n" +
                     " email TEXT NOT NULL\n" +
                     ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Users table created or already exists");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Read and display all users
    public static void readUsers() {
        String sql = "SELECT id, name, email FROM users";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("---- Users in DB ----");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Name: " + rs.getString("name") +
                                   ", Email: " + rs.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    
}

/*
databse setpup & helpers
*/
