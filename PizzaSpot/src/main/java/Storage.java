import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Storage extends ArrayBlockingQueue<Order> {
    private volatile int cooksInProcess = 0;

    Storage(int size){ super(size);}

    public synchronized void cookProccecing(){ cooksInProcess++; }

    public synchronized void cookStopedProccecing(){ cooksInProcess--; }

    @Override
    public synchronized boolean isEmpty(){ return super.isEmpty() && cooksInProcess == 0; }
}