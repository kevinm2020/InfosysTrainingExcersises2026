package Main.java.com.demo.concurrency.service;

import Main.java.com.demo.concurrency.model.Order;

/*
The OrderProcessor class implements Runnable to process orders concurrently.
It takes an Order object as a parameter and defines the run method to handle
*/

public class OrderProcessor implements Runnable {

    private final Order order;

    //Constructor
    public OrderProcessor(Order order) {
        this.order = order;
    }

    @Override
    public void run() 
    {
        //START CLOCK
        long startTime = System.currentTimeMillis(); 
        System.out.println(
            "Proccessing " + order.getOrderId() + 
            " on thread: " + Thread.currentThread().getName()
        );

        try
        {
            Thread.sleep(500); // Simulate time-consuming processing

        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            System.err.println("Order processing interrupted for order: " + order.getOrderId());
        }

        System.out.println("Finished Proccessing Order " + order.getOrderId());
        // ✅ STOP TIMER
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total processing time: "
                    + totalTime + " ms");

    }



    
}

/*
This is a worker that know how to proccess one order(given a Order obj)
This class implement Runnable and implements the run()

*/