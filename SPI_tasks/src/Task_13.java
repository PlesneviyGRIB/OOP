import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Task_13 {
    private static final int CNT = 5;
    private static final int maxReflectionTime = 100;
    private static final int maxMealTime = 100;

    static class Philosopher extends Thread{
        private final int id;
        private final Semaphore leftFork;
        private final Semaphore rightFork;
        private final Semaphore waiter;

        Philosopher(int id, Semaphore leftFork, Semaphore rightFork, Semaphore waiter) {
            this.id = id;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
            this.waiter = waiter;
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

                        waiter.acquire();

                        if (leftFork.availablePermits() > 0 && rightFork.availablePermits() > 0) {
                            leftFork.acquire();
                            rightFork.acquire();
                            state = true;
                            waiter.release();

                        } else {
                            synchronized (Philosopher.class) {
                                waiter.release();
                                Philosopher.class.wait();
                            }
                        }
                    }

                    System.out.println("Philosopher " + id + " HAVING MEAL");

                    TimeUnit.MILLISECONDS.sleep(maxMealTime);

                    System.out.println("Philosopher " + id + " FINISHED EATING");

                    leftFork.release();
                    rightFork.release();

                    synchronized (Philosopher.class) {
                        waiter.release();
                        Philosopher.class.notifyAll();
                    }
                }
            }catch (InterruptedException ignore){}
        }
    }

    public static void main(String[] args){

        Philosopher[] philosophers = new Philosopher[CNT];

        Semaphore[] forks = new Semaphore[CNT];

        Semaphore waiter = new Semaphore(1);

        for (int i = 0;i < forks.length; ++i)
            forks[i] = new Semaphore(1);

        for (int i = 0;i < philosophers.length; ++i)
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % forks.length], waiter);

        for (Philosopher p : philosophers) p.start();
    }
}
