import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Consumer;

/*
What is the Consumer interface?
- Consumer is a predefined functional interface in Java that represents an operation 
- that takes a single input argument.
- It does not return any result (void).
- It is often used for operations that have side effects, 
- such as modifying an object or performing I/O operations.

*/
public class ConsumerOperations {

    // Transaction runner for Consumer<Connection>
    // This method takes a Consumer that accepts a Connection and executes it within a transaction
    
    //this function runs DB transcation where the operation is defined by a Consumer functional interface.
    //Meaning using the interface we can pass different operations to be executed within the transaction.
    public static void runInTransaction(Consumer<Connection> operation) {
        // Try-with-resources: automatically closes the connection
        try (Connection conn = DriverManager.getConnection(DBUtil.DB_URL)) {

            // Disable auto-commit so we can control when changes are saved
            conn.setAutoCommit(false);

            try 
            {
                // Execute the Consumer operation by passing the connection
                operation.accept(conn);
                // If successful, commit all changes to the database
                conn.commit();
                System.out.println("Consumer → Transaction committed");
            } 
            catch (Exception e) 
            {
                // If an error occurs, undo all changes made during this transaction
                conn.rollback();
                System.out.println("Consumer → Transaction rolled back");
                System.out.println(e.getMessage());
            }

        }
        catch (SQLException e) 
        {
            // Catch connection errors
            System.out.println(e.getMessage());
        }
    }

    // Lambda implementation - uses an inline lambda expression
    // This method demonstrates using a lambda to define the DB operation

    ///it uses the transaction runner above and passes a lambda that defines the operation
    public static void insertWithLambda() 
    {
        // Pass a lambda that defines the database operation directly
        runInTransaction(conn -> {
            String sql = "INSERT INTO users(name, email) VALUES(?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "ConsumerLambda");
                pstmt.setString(2, "consumerlambda@email.com");
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Method reference implementation - uses an existing method
    //it used the transaction runner above and passes a method reference that defines the operation
    public static void insertWithMethodRef() 
    {
        // Pass a reference to an existing method instead of a lambda
        runInTransaction(ConsumerOperations::insertConsumerMethod);
    }

        // Named method for method reference - this method is referenced above
        // It contains the actual database insertion logic
        public static void insertConsumerMethod(Connection conn) {
            String sql = "INSERT INTO users(name, email) VALUES(?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "ConsumerMethodRef");
                pstmt.setString(2, "consumermethod@email.com");
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}
