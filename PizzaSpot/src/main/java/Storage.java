import java.util.concurrent.ArrayBlockingQueue;

/**
 * class for store order-object
 * state: cooksInProcess store number of cooks connected with current storage
 */
public class Storage<Order> extends ArrayBlockingQueue<Order> {
    private volatile int cooksInProcess = 0;

    Storage(int size){ super(size);}

    public synchronized void cookProccecing(){ cooksInProcess++; }

    public synchronized void cookStopedProccecing(){ cooksInProcess--; }

    private synchronized int getCooksInProcess(){ return cooksInProcess; }

    @Override
    public synchronized boolean isEmpty(){ return super.isEmpty() && getCooksInProcess() == 0; }
}