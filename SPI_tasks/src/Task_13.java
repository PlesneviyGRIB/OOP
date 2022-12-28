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

public class Task_13 {
    private static final int CNT = 5;

    public static void main(String[] args) {

        Runnable[] philosophers = new Runnable[CNT];
        Fork[] forks = new Fork[CNT];

        for(int i = 0; i<CNT; i++){
            forks[i] = new Fork();
        }

        Waiter waiter = new Waiter();
        philosophers[0] = new Philosopher(forks[CNT-1], forks[0], 0, waiter);
        for(int i = 1; i<CNT; i++){
            philosophers[i] = new Philosopher(forks[i-1], forks[i], i, waiter);
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
        private Waiter waiter;

        Philosopher(Fork leftFork, Fork rightFork, int id, Waiter waiter){
            this.leftFork = leftFork;
            this.rightFork = rightFork;
            this.id = id;
            this.waiter = waiter;
        }

        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("Philosopher " + id + " reflecting");
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, maxReflectionTime));
                    System.out.println("Philosopher " + id + " try eat");
                    AtomicPickingUpBothForks atomic = new AtomicPickingUpBothForks(leftFork, rightFork, waiter);
                    while(!atomic.atomicPickUp()) {} // if philosopher want to eat he will eat.
                    TimeUnit.MILLISECONDS.sleep(100);
                    try {
                        System.out.println("Philosopher " + id + " picked up left fork");
                        System.out.println("Philosopher " + id + " picked up right fork");
                        System.out.println("Philosopher " + id + " HAVING MEAL");
                        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, maxMealTime));
                    }
                    finally {
                        atomic.atomicRelease();
                        System.out.println("Philosopher " + id + " released right fork");
                        System.out.println("Philosopher " + id + " released left fork");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Waiter{
        private Lock lock = new ReentrantLock();

        public void lock(){
            lock.lock();
        }

        public synchronized void sleep() throws InterruptedException {
            release();
            this.wait();
            lock();
        }

        public synchronized void newFreeForks() {
            this.notifyAll();
        }

        public void release(){
            lock.unlock();
        }
    }

    static class AtomicPickingUpBothForks{
        private Fork right;
        private Fork left;
        private Waiter waiter;

        AtomicPickingUpBothForks(Fork left, Fork right, Waiter waiter){
            this.left = left;
            this.right = right;
            this.waiter = waiter;
        }

        private boolean atomicPickUp(){
            try{
                waiter.lock();
                boolean l = false, r = false;
                try {
                    l = left.use();
                    r = right.use();
                    if(l && r) return true;
                }   catch (InterruptedException e){
                    if(l) left.release();
                    if(r) right.release();
                }
                if(l) left.release();
                if(r) right.release();
                waiter.sleep();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                waiter.release();
            }
            return false;
        }
        private void atomicRelease(){
            try{
                waiter.lock();
                right.release();
                left.release();
            }
            finally {
                waiter.newFreeForks();
                waiter.release();
            }
        }
    }

    static class Fork{
        private final Lock lock = new ReentrantLock();

        private boolean use() throws InterruptedException {
            return lock.tryLock();
        }
        private void release(){
            lock.unlock();
        }
    }
}
