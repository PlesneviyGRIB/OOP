import java.util.concurrent.ArrayBlockingQueue;

public class Storage extends ArrayBlockingQueue<Order> {
    Storage(int size){ super(size);}
}