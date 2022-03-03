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
        while (!Thread.interrupted()){
            try {
                Order order = (Order) orders.take();
                cooking(order);
                storage.add(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void cooking(Order order) throws InterruptedException { TimeUnit.SECONDS.sleep(order.getFood().getTime()); }
}