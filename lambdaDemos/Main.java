//01/29
// Small exercise on functional interfaces and lambda expressions in Java

import java.util.List;
import java.util.function.Predicate;

public class Main 
{

    /**
     * Creates and returns a list of sample Product objects with various items.
     * Each product has a name, price, and rating.
     * 
     * @return a List containing five Product objects (Laptop, Smartphone, Tablet, Monitor, Headphones)
     */
    public static List<Product> createNewProducts()
    {
        // Initialize a sample list of products with their names, prices, and ratings
        List<Product> products = List.of(
            new Product("Laptop", 999.99, 4.5),
            new Product("Smartphone", 699.99, 4.7),
            new Product("Tablet", 399.99, 4.2),
            new Product("Monitor", 199.99, 4.3),
            new Product("Headphones", 149.99, 4.6)
        );
        return products;
    }


    /**
     * Filters and prints products from the list based on a given predicate.
     * Uses a functional interface (Predicate) to test each product against a condition.
     * 
     * @param lst the list of Product objects to evaluate
     * @param predicate a Predicate that defines the filtering condition
     */
    public static void evaluate(List<Product> lst, Predicate<Product> predicate)
    {
        // Iterate through each product in the list
        for (Product product : lst) {
            if (predicate.test(product)) {
                System.out.println(product);
            }
        }
    }

    /**
     * Main entry point for the application.
     * Demonstrates the use of lambda expressions with functional interfaces.
     */
    public static void main(String[] args) 
    {
        System.out.println("Hello, World!");
        
        // Create a list of sample products
        List<Product> products = createNewProducts();

        // Evaluate and print products with price greater than 500
        // Lambda expression: p -> p.getPrice() > 500
        System.out.println("\nProducts with price > $500:");
        evaluate(products, p -> p.getPrice() > 500);

        //we are passing behavior (predicate) to the evaluate method
        
        System.out.println("-----");
        
        // Evaluate and print products with rating greater than or equal to 4.5
        // Lambda expression: p -> p.getRating() >= 4.5
            System.out.println("\nProducts with rating >= 4.5:");
            evaluate(products, p -> p.getRating() >= 4.5);
        }
    }
