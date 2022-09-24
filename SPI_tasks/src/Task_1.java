import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task_1 implements Runnable{
    public static void main(String[] args) {

        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(new Task_1());

        exec.shutdown();

        for(int i = 0; i<10; i++)
            System.out.println("Main thread");
    }

    @Override
    public void run() {
        for(int i = 0; i<10; i++)
            System.out.println("Supportive thread");
    }
}