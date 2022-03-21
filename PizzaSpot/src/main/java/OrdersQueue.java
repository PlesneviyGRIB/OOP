import java.util.concurrent.LinkedBlockingQueue;

/**
 * class for store order-object
 */

public class OrdersQueue<Order> extends LinkedBlockingQueue<Order> {
    private WorkingDay workingDay;
    OrdersQueue(WorkingDay workingDay){
        this.workingDay = workingDay;
    }

    @Override
    public boolean isEmpty(){
        return workingDay.isOver() && super.isEmpty();
    }
}