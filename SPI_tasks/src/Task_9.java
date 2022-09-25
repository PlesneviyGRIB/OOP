import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Sometimes it takes quite long to catch deadlock, u can change constants to do better.
// U can determine deadlock condition if no new raws appearing in console.
//
// To reduce opportunity of deadlock one of philosopher should firstly pick up right fork (not all philosophers as first pick up fork from the same side).

public class Task_9 {
    private static final int CNT = 5;

    public static void main(String[] args) {

        Runnable[] philosophers = new Runnable[CNT];
        Fork[] forks = new Fork[CNT];

        for(int i = 0; i<CNT; i++){
            forks[i] = new Fork();
        }

        philosophers[0] = new Philosopher(forks[CNT-1], forks[0], 0);
        for(int i = 1; i<CNT; i++){
            philosophers[i] = new Philosopher(forks[i-1], forks[i], i);
        }

        ExecutorService executorService = Executors.newCachedThreadPool();

        List.of(philosophers).forEach(executorService::execute);

        executorService.shutdown();
    }
    static class Philosopher implements Runnable{
        private static final int maxReflectionTime = 4;
        private static final int maxMealTime = 4;

        private Fork leftFork;
        private Fork rightFork;
        private int id;

        Philosopher(Fork leftFork, Fork rightFork, int id){
            this.leftFork = leftFork;
            this.rightFork = rightFork;
            this.id = id;
        }

        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("Philosopher " + id + " reflecting");
                    TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1, maxReflectionTime));
                    System.out.println("Philosopher " + id + " try eat");
                    leftFork.use();
                    System.out.println("Philosopher " + id + " picked up left fork");
                    rightFork.use();
                    System.out.println("Philosopher " + id + " picked up right fork");
                    System.out.println("Philosopher " + id + " having meal");
                    TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1,maxMealTime));
                    rightFork.release();
                    System.out.println("Philosopher " + id + " released right fork");

                    leftFork.release();
                    System.out.println("Philosopher " + id + " released left fork");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Fork{
        private final Lock lock = new ReentrantLock();
        private void use() throws InterruptedException {
            lock.lock();
        }
        private void release(){
            lock.unlock();
        }
    }
}
