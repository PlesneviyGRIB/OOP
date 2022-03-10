import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Cook implements Runnable{
    private BlockingQueue orders, storage;

    Cook(BlockingQueue orders, BlockingQueue storage){
        this.orders = orders;
        this.storage = storage;
    }

    @Override
    public void run() {
        ((Storage)storage).cookProccecing();
        while (!orders.isEmpty()){
            try {
                Order order = (Order) orders.take();
                cooking(order);
                storage.put(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Cook done..");
        ((Storage)storage).cookStopedProccecing();
    }

    private void cooking(Order order) throws InterruptedException { TimeUnit.SECONDS.sleep(order.getFood().getTime()); }
}