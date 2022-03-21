import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * class processing actions of cook
 * thread finish if queue of orders become empty
 */

public class Cook implements Runnable{
    private BlockingQueue ordersQueue;
    private BlockingQueue storage;

    Cook(BlockingQueue ordersQueue, BlockingQueue storage){
        this.ordersQueue = ordersQueue;
        this.storage = storage;
    }

    @Override
    public void run() {
        ((Storage)storage).cookProccecing();
        while (!ordersQueue.isEmpty()){
            try {
                Order order = (Order) ordersQueue.poll();
                if(order != null){
                    cooking(order);
                    storage.put(order);
                    System.out.println("|id: "+ String.format("%2d",order.getId()) + "| WAS PUT TO STORAGE");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Cook done..");
        ((Storage)storage).cookStopedProccecing();
    }

    private void cooking(Order order) throws InterruptedException {
        TimeUnit.SECONDS.sleep(order.getFood().getTime());
        System.out.println("|id: "+ String.format("%2d",order.getId()) + "| WAS COOKED");
    }
}