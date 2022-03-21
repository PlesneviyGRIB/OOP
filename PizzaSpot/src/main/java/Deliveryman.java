import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * class processing actions of deliveryman
 * thread finish if storage become empty
 */
public class Deliveryman implements Runnable{
    private BlockingQueue storage;
    private int capacity;

    Deliveryman(BlockingQueue storage, int capacity){
        this.storage = storage;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        while(!storage.isEmpty()){
            try {
                ArrayList<Order> backPack = new ArrayList<>();
                storage.drainTo(backPack,capacity);
                if(backPack.size() > 0) {
                    System.out.println("deliveries backpack: " + backPack);
                    delivery(backPack);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Deliveryman done..");
    }

    private void delivery(ArrayList<Order> backPack) throws InterruptedException {
        for (Order order: backPack) {
            TimeUnit.SECONDS.sleep(order.getDeliveryTimeRequired());
            System.out.println(order + " - DELIVERED");
        }
    }
}