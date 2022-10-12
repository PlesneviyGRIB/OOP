import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task_14 {
    private static final int heapMaxsize = 100;
    static class A_Line implements Runnable{
        static class A{}
        private static final int demandedTime = 1;
        static final ArrayBlockingQueue<A> heapA = new ArrayBlockingQueue<>(heapMaxsize);

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    TimeUnit.SECONDS.sleep(demandedTime);
                    heapA.put(new A());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    static class B_Line implements Runnable{
        static class B{}
        private static final int demandedTime = 2;
        static final ArrayBlockingQueue<B_Line.B> heapB = new ArrayBlockingQueue<>(heapMaxsize);
        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    TimeUnit.SECONDS.sleep(demandedTime);
                    heapB.put(new B_Line.B());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    static class C_Line implements Runnable{
        static class C{}
        private static final int demandedTime = 3;
        static final ArrayBlockingQueue<C_Line.C> heapC = new ArrayBlockingQueue<>(heapMaxsize);
        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    TimeUnit.SECONDS.sleep(demandedTime);
                    heapC.put(new C_Line.C());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    static class widgetProducer implements Runnable{
        static class Module{
            public Module(A_Line.A a, B_Line.B b) {}
        }

        static class Widget{
            public Widget(C_Line.C C, Module module) {}
            @Override
            public String toString(){
                return "New widget done!";
            }
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                try {
                    System.out.println(new Widget(C_Line.heapC.take(), new Module(A_Line.heapA.take(), B_Line.heapB.take())));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        int cntOfALines = 1;
        int cntOfBLines = 2;
        int cntOfCLines = 3;

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 0; i<cntOfALines; i++)
            executorService.execute(new A_Line());

        for(int i = 0; i<cntOfBLines; i++)
            executorService.execute(new B_Line());

        for(int i = 0; i<cntOfCLines; i++)
            executorService.execute(new C_Line());

        executorService.execute(new widgetProducer());

        TimeUnit.SECONDS.sleep(20);
        executorService.shutdownNow();

        System.out.println(A_Line.heapA.size());
        System.out.println(B_Line.heapB.size());
        System.out.println(C_Line.heapC.size());
    }
}
