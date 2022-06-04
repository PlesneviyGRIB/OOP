import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * class for set new orders in special Queue
 * all orders came wile Thread.currentThread.isAlive()
 */

public class NewOrders implements Runnable{
    private final BlockingQueue ordersQueue;
    private Random random;
    private final WorkingDay workingDay;
    private final OrdersGenerator ordersGenerator = new OrdersGenerator();

    NewOrders(BlockingQueue ordersQueue, WorkingDay workingDay){
        this.ordersQueue = ordersQueue;
        random = new Random();
        this.workingDay = workingDay;
    }

    @Override
    public void run() {
        System.out.println("Orders start coming!");
        while(true){
            try {
                TimeUnit.SECONDS.sleep(1 + random.nextInt() % 4);
                Order order = ordersGenerator.next();
                if(workingDay.isOver()) break;
                ordersQueue.add(order);
                System.out.println("|id: " + String.format("%2d", order.getId()) +"| ORDER JUST COME");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}