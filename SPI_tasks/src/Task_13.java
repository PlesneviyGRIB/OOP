import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task_13 {
    private static final int CNT = 5;
    private static final int maxReflectionTime = 100;
    private static final int maxMealTime = 100;

    static class Philosopher extends Thread{
        private final int id;
        private final Semaphore leftFork;
        private final Semaphore rightFork;
        private final Lock waiter;
        private final Condition condition;

        Philosopher(int id, Semaphore leftFork, Semaphore rightFork, Lock waiter, Condition cond) {
            this.id = id;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
            this.waiter = waiter;
            this.condition = cond;
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Philosopher " + id + " reflecting");

                    TimeUnit.MILLISECONDS.sleep(maxReflectionTime);

                    boolean state = false;

                    System.out.println("Philosopher " + id + " try eat");

                    while (!state) {

                        waiter.lock();

                        if (leftFork.availablePermits() > 0 && rightFork.availablePermits() > 0) {
                            leftFork.acquire();
                            rightFork.acquire();
                            state = true;
                            waiter.unlock();
                        } else {
                            condition.await();
                            waiter.unlock();
                        }
                    }

                    System.out.println("Philosopher " + id + " HAVING MEAL");

                    TimeUnit.MILLISECONDS.sleep(maxMealTime);

                    System.out.println("Philosopher " + id + " FINISHED EATING");

                    leftFork.release();
                    rightFork.release();

                    waiter.lock();
                    condition.signalAll();
                    waiter.unlock();
                }
            }catch (InterruptedException ignore){}
        }
    }

    public static void main(String[] args){

        Philosopher[] philosophers = new Philosopher[CNT];

        Semaphore[] forks = new Semaphore[CNT];

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        for (int i = 0;i < forks.length; ++i)
            forks[i] = new Semaphore(1);

        for (int i = 0;i < philosophers.length; ++i)
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % forks.length], reentrantLock, condition);

        for (Philosopher p : philosophers) p.start();
    }
}
