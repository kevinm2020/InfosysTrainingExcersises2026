package Main.java.com.demo.concurrency.model;

public class Order {

    //fields
    private final String orderId;
    private final int amount;

    //Constructor
    public Order(String orderId, int amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    //getters
    public String getOrderId() {
        return orderId;
    }
    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", amount=" + amount +
                '}';
    }



    
}
