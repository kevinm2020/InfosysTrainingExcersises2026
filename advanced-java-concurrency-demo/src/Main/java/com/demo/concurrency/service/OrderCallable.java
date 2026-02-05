package Main.java.com.demo.concurrency.service;

import Main.java.com.demo.concurrency.model.Order;
import java.util.concurrent.Callable;
import Main.java.com.demo.concurrency.concurrent.ProcesingTracker;

public class OrderCallable implements Callable<String> {

    private final Order order;
    private final ProcesingTracker tracker;
    

    public OrderCallable(Order order, ProcesingTracker tracker)
    {
        this.order = order;
        this.tracker = tracker;
    }

    @Override
    public String call() throws Exception
    {
        //Actual order proccessing logic takes place here

        String threadName = Thread.currentThread().getName();

        System.out.println(
                "Processing " + order.getOrderId() +
                " on thread: " + threadName
        );

        Thread.sleep(500); // Simulate work

        String result = "Completed " + order.getOrderId() + " by " + threadName;
        tracker.recordSuccess(result);
        System.out.println(result);

        return result;

    }
    
}

/*

Callable interface allows us to use call() which is helpul because it
allows us to return result back to the App.
run() on Runnable is a void function.
Call() also allows us to throw exceptions
 */