// Importing the necessary SQL package for database operations
import java.sql.*;

public class Main {

    // Database URL for SQLite connection
    private static final String DB_URL = "jdbc:sqlite:database.db";

    public static void main(String[] args) throws SQLException {

       // 1️⃣ Setup DB
        DBUtil.createTable();

        // 2️⃣ Custom interface examples
        CustomOperations.insertWithLambda();
        CustomOperations.insertWithMethodRef();

        // 3️⃣ Predefined Consumer interface examples
        ConsumerOperations.insertWithLambda();
        ConsumerOperations.insertWithMethodRef();

        // 4️⃣ Read all users
        DBUtil.readUsers();

    }

    // Method to create a table for users in the database
    private static void createTable() 
    {

        // SQL command to create a table if it does not already exist
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " name TEXT NOT NULL,\n"
                + " email TEXT NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) 
        {
            // Executing the SQL command to create the table
            stmt.execute(sql);
        } 
        catch (SQLException e) 
        {
            // Printing any SQL exception messages
            System.out.println(e.getMessage());
        }
    }

    // Method to insert a new user into the database
    private static void insertUser(String name, String email) 
    {
        // SQL command to insert a new user with placeholders for parameters
        String sql = "INSERT INTO users(name, email) VALUES(?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {
            // Setting the parameters for the prepared statement
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            // Executing the update to insert the user
            pstmt.executeUpdate();
        } 
        catch (SQLException e) 
        {
            // Printing any SQL exception messages
            System.out.println(e.getMessage());
        }
    }

    // Method to read and display all users from the database
    private static void readUsers() 
    {
        // SQL command to select all users from the database
        String sql = "SELECT id, name, email FROM users";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) 
        {

            // Iterating through the result set and printing each user's details
            while (rs.next()) 
            {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Name: " + rs.getString("name") +
                                   ", Email: " + rs.getString("email"));
            }
        } 
        catch (SQLException e) 
        {
            // Printing any SQL exception messages
            System.out.println(e.getMessage());
        }
    }

    private static void insertUsersInTransaction()
    {
        String sql = "INSERT INTO users(name, email) VALUES(?,?)";
        try(Connection conn = DriverManager.getConnection(DB_URL))
        {

            // Disable auto-commit mode (start transaction)
            conn.setAutoCommit(false);

            try(PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                // Inserting first user
                pstmt.setString(1, "User1");
                pstmt.setString(2, "user1@email.com");
                pstmt.executeUpdate();
                // Inserting second user
                pstmt.setString(1, "User2");
                pstmt.setString(2, "user2@email.com");
                pstmt.executeUpdate();

                //forced error to test rollback
                if(true)
                    throw new SQLException("Forced error to test rollback");
                // Commit transaction if both inserts are successful

                conn.commit();                                                                          //transaction success - commit to DB
                System.out.println("Transaction committed successfully.");
            }
            catch( SQLException e)
            {
                System.out.println("Transaction is being rolled back.");
                conn.rollback();
            }
            finally
            {
                // Re-enable auto-commit mode
                conn.setAutoCommit(true);
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
