package Main.java.com.demo.concurrency;

import Main.java.com.demo.concurrency.model.Order;
import Main.java.com.demo.concurrency.service.OrderProcessor;
import Main.java.com.demo.concurrency.service.OrderCallable;
import Main.java.com.demo.concurrency.concurrent.ProcesingTracker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//EXECUTOR / FUTURE  / CALLABLE

public class App {
    public static void main(String[] args) {

        System.out.println("Advanced Java Concurrency Demo Started...");

        //Order tracker
        ProcesingTracker tracker = new ProcesingTracker();

        System.out.println("DEMO 01: USING MANUAL THREADS");
       

        //Create new Orders obejects
        Order order1 = new Order("ORDER-1", 100);
        Order order2 = new Order("ORDER-2", 200);

        //Creating new worker threads
        Thread t1 = new Thread(new OrderProcessor(order1));
        Thread t2 = new Thread(new OrderProcessor(order2));

        //begin manual threads
        t1.start();
        t2.start();
    
        System.out.println("DEMO 02: USING EXECUTOR + CALLABLE/FUTURE ON THREADS");
        /*
        In this variation, instead of creating thread workers myself, i can
        have a managed pool of workers that i submit tasks to
        */

        //Create new orders
        Order order3 = new Order("Order-3", 100);
        Order order4 = new Order("Order-4", 100);


        //Thread pool of 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //Submit taks to executor (pool thread) 
        Future<String> future1 = executor.submit(new OrderCallable(order3, tracker));
        Future<String> future2 = executor.submit(new OrderCallable(order4, tracker));

        /*
        we are using Future because it's capabilities to async results in a non-bloking fashion
        */

        try
        {
            // START TIMER
            long startTime2 = System.currentTimeMillis();

            //PROCESS WORKER THREADS
            future1.get();
            future2.get();

            // STOP TIMER
            long endTime2 = System.currentTimeMillis();
            long totalTime2 = endTime2 - startTime2;
            System.out.println("Total processing time: "
                    + totalTime2 + " ms");

            //REPORT

            System.out.println("\n=== FINAL REPORT ===");
            System.out.println("Total processed: "
                    + tracker.getProcessedCount());     //Atomic Counter Int

            System.out.println("Results:");
            tracker.getProcessedResults()
                   .forEach(System.out::println);   //CopyOnWrite Array List (thread safe concurrency)
        } 
        catch (InterruptedException e) 
        {
            System.out.println("Main thread was interrupted!");
            Thread.currentThread().interrupt(); // very important best practice
        } 
        catch (Exception e) 
        { 
            e.printStackTrace();
        } 
        finally 
        {
            executor.shutdown();
        }


    }
}
