import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class Task_8 {
    private static int cntOfThreads;
    private static List<Future<Double>> futures = new ArrayList<>();

    private static AtomicLong time = new AtomicLong();

    public static void main(String[] args) {

        checkParams(args);

        AtomicReference<ExecutorService> ref = new AtomicReference<>();

        ExecutorService executorService = Executors.newFixedThreadPool(cntOfThreads);

        ref.set(executorService);
        time.set(System.currentTimeMillis());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown hook ran!");
            ref.get().shutdownNow();
            System.out.println("HERE");
            try {
                result(time.get(), futures);
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        ));

        boolean singh = false;
        for(int i = 0; i<cntOfThreads; i++) {
            futures.add(executorService.submit(new Calculator(singh,  3 + i*2, cntOfThreads * 2)));
            singh = !singh;
        }
        executorService.shutdown();
    }

    static void checkParams(String[] args){
        if(args.length<1) {
            System.out.println("Provide count of threads as param!");
            System.exit(0);
        }
        try{
            cntOfThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Wrong param!");
            System.exit(0);
        }
    }

    static void result(long startTime, List<Future<Double>> futures) throws ExecutionException, InterruptedException {
        double res = 1;
        for(Future<Double> future : futures)
            res += future.get();

        System.out.println("RES: " + res);

        startTime -= System.currentTimeMillis();
        System.out.println("Time: " + -startTime);
    }

    static class Calculator implements Callable<Double>{
        private final boolean singh;
        private final long startNum;
        private final long pace;

        public Calculator(boolean singh, long startNum, long pace) {
            this.singh = singh;
            this.startNum = startNum;
            this.pace = pace;
        }
        @Override
        public Double call() {
            double res = 0;
            long del = startNum;

            while (!Thread.currentThread().isInterrupted()){
                res += 1.0 / del;
                del += pace;
            }
            return singh ? res : -res;
        }
    }
}
