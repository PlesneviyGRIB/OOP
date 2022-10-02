import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Task_10 implements Runnable{
    private static final int CNT = 10000;
    private static final AtomicBoolean ORDER = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newSingleThreadExecutor();
        var runnable = new Task_10();
        exec.execute(runnable);

        exec.shutdown();

        for(int i = 0; i<CNT; i++) {
            System.out.println("Main thread");

            synchronized (Task_10.class){
                ORDER.set(false);
                Task_10.class.notify();
                while (!ORDER.get())
                    Task_10.class.wait();
            }
        }
    }

    @Override
    public void run() {
        for(int i = 0; i<CNT; i++) {
            System.out.println("Supportive thread");

            synchronized (Task_10.class){
                ORDER.set(true);
                Task_10.class.notify();
                if(i != CNT-1)
                    while (ORDER.get()) {
                        try {
                            Task_10.class.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
            }
        }
    }
}