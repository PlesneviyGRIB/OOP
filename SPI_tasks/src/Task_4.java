import java.time.LocalTime;

public class Task_4 implements Runnable{
    public static void main(String[] args) {

        Thread thread = new Thread(new Task_4());
        thread.start();

        try {
            Thread.sleep(2000);
            thread.interrupt();
        }
        catch (InterruptedException e) {throw new RuntimeException(e);}

        System.out.println("Interrupted by main thread");
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(LocalTime.now());

            try {Thread.sleep(100);}
            catch (InterruptedException e) {Thread.currentThread().interrupt();}
        }
    }
}
