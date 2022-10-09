import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Task_11 implements Runnable{
    private static final int CNT = 100;
    private static Semaphore semaphore = new Semaphore(1);
    private static Semaphore semaphore0 = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newSingleThreadExecutor();
        var runnable = new Task_11();
        exec.execute(runnable);

        exec.shutdown();

        for(int i = 0; i<CNT; i++) {
            semaphore.acquire();
            semaphore0.acquire();
            System.out.println("Main thread");
            semaphore.release();
            semaphore0.release();
        }
    }

    @Override
    public void run() {
        for(int i = 0; i<CNT; i++) {
            try {
                semaphore0.acquire();
                semaphore.acquire();
                System.out.println("Supportive thread");
                semaphore0.release();
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}