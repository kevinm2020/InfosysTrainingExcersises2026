import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


// Functional interface for database operations
// Allows passing different DB operations as lambda expressions or method references
@FunctionalInterface
interface DBOperation {
    void execute(Connection conn) throws SQLException;
}


public class CustomOperations {

    /**
     * Executes a database operation within a transaction
     * Handles commit on success and rollback on failure
     * 
     * @param operation The DBOperation to execute
     * @throws SQLException if connection fails
     */

    //run transaction where the DB operation is an argument that defines what kind of operation to run
    //this makes runIntransaction reusable for different operations
    public static void runInTransaction(DBOperation operation) throws SQLException
    {
        // Try-with-resources: automatically closes connection
        try (Connection conn = DriverManager.getConnection(DBUtil.DB_URL))
        {
            // Disable auto-commit to start manual transaction
            conn.setAutoCommit(false);

            try 
            {
                // Execute the provided operation
                operation.execute(conn);
                // Commit transaction if successful
                conn.commit();
                System.out.println("Custom IF → Transaction committed");
            } 
            catch (SQLException e) 
            {
                // Rollback transaction on error
                conn.rollback();
                System.out.println("Custom IF → Transaction rolled back");
                System.out.println(e.getMessage());
            }

        } 
        catch (SQLException e) 
        {
            // Handle connection failures
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * Example: Insert user data using lambda expression
     * Demonstrates inline implementation of DBOperation
     * 
     * @throws SQLException if operation fails
     */
    public static void insertWithLambda() throws SQLException
    {
         // Lambda expression defines the operation inline
         ///insertwithlambda runs runintransaction which takes a dboperation as parameter
         /// dboperation is a functional interface with a single method execute
         /// the lambda provides the implementation for execute
         runInTransaction(conn -> {
            String sql = "INSERT INTO users(name, email) VALUES(?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "CustomLambda");
                pstmt.setString(2, "customlambda@email.com");
                pstmt.executeUpdate();
            }
        });
    }

    /**
     * Example: Insert user data using method reference
     * Demonstrates passing an existing method as an operation
     * 
     * @throws SQLException if operation fails
     */
    public static void insertWithMethodRef() throws SQLException {
        // Method reference: uses insertCustomMethod as the operation
        //here runintransaction takes insertcustommethod as parameter
        //which is another case of dboperation functional interface
        //since insertcustommethod matches the signature of execute method in dboperation
        runInTransaction(CustomOperations::insertCustomMethod);
    }

            /**
             * Helper method for method reference example
             * Inserts a user into the database
             * 
             * @param conn Database connection
             * @throws SQLException if insert fails
             */
            //this method is passed as a method reference to runintransaction
            //it matches the signature of execute method in dboperation functional interface
            public static void insertCustomMethod(Connection conn) throws SQLException {
                String sql = "INSERT INTO users(name, email) VALUES(?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, "CustomMethodRef");
                    pstmt.setString(2, "custommethodref@email.com");
                    pstmt.executeUpdate();
                }
            }
            
        }
