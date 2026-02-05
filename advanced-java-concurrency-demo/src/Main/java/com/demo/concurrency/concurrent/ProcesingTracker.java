package Main.java.com.demo.concurrency.concurrent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ProcesingTracker 
{

    //Atomic Integer - Thread Safe int of orders
    private final AtomicInteger processedCount = new AtomicInteger(0);

    //Concurrent Collection - Thread Safe list of orders
    private final List<String> processedResults = new CopyOnWriteArrayList<>();

    public void recordSuccess(String result)
    {
        //Atomic operation on the int of orders
        processedCount.incrementAndGet();
        //Thread safe list that takes concurrent writes and reads
        processedResults.add(result);
        //no need for synchornized or locks here
    }

    public int getProcessedCount()
    {
        return processedCount.get();
    }

    public List<String> getProcessedResults()
    {
        return processedResults;
    }
    
}
