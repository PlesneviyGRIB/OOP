import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class Task_5 {
    public static void main(String[] args) {

        Timer timer = new Timer();

        AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Thread thread = new Thread(() -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(LocalTime.now());

                        try {
                            TimeUnit.MILLISECONDS.sleep(100);}
                        catch (InterruptedException e) {Thread.currentThread().interrupt();}
                    }
                    System.out.println("Interrupted");
                });
                thread.start();
                threadAtomicReference.set(thread);
            }
        }, 0);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                threadAtomicReference.get().interrupt();
                timer.cancel();
            }
        }, 2000);

    }
}
