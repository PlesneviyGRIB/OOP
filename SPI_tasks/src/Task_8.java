import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class Task_8 {
    private static int cntOfThreads;
    private static List<Future<Data>> futures = new ArrayList<>();

    private static AtomicLong time = new AtomicLong();

    public static void main(String[] args) throws InterruptedException {

        cntOfThreads = checkParams(args);

        AtomicReference<ExecutorService> ref = new AtomicReference<>();

        ExecutorService executorService = Executors.newFixedThreadPool(cntOfThreads);

        ref.set(executorService);
        time.set(System.currentTimeMillis());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown hook ran!");
            ref.get().shutdownNow();
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

        TimeUnit.SECONDS.sleep(6);
        System.exit(0);
    }

    static int checkParams(String[] args){
        int res = 0;
        if(args.length<1) {
            System.out.println("Provide count of threads as param!");
            System.exit(0);
        }
        try{
            res = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Wrong param!");
            System.exit(0);
        }
        return res;
    }

    static void result(long startTime, List<Future<Data>> futures) throws ExecutionException, InterruptedException {
        double res = count(futures);

        System.out.println("RES: " + res);
        System.out.println("MISTAKE: " + (res - Math.PI/4));

        startTime -= System.currentTimeMillis();
        System.out.println("Time: " + -startTime);
    }

    static private double count(List<Future<Data>> futures){

        List<Data> data = futures.stream().map(f -> {
            try {
                return f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).toList();

        int maxCnt = data.stream().map(d -> d.cnt).max(Integer::compareTo).get();
        double res = 1;

        for (Data d : data) {
            res += d.res;
            long pace = cntOfThreads * 2;
            long del = d.startNum + pace * (d.cnt+1);
            boolean singh = d.singh;

            for(int i = 0; i<maxCnt - d.cnt; i++){
                if(singh) res += 1.0 / del;
                else res -= 1.0 / del;
                del += pace;
                if(cntOfThreads % 2 == 1) singh = !singh;
            }
        }
        return res;
    }

    static class Calculator implements Callable<Data>{
	    private int cnt = 0;
        private boolean singh;
        private final long startNum;
        private final long pace;

        public Calculator(boolean singh, long startNum, long pace) {
            this.singh = singh;
            this.startNum = startNum;
            this.pace = pace;
        }
        @Override
        public Data call() {
            double res = 0;
            long del = startNum;

            while (!Thread.currentThread().isInterrupted()){
                cnt++;
                if(singh) res += 1.0 / del;
                else res -= 1.0 / del;
                del += pace;
                if(cntOfThreads % 2 == 1) singh = !singh;
            }
            return new Data(cnt, res, startNum, singh);
        }
    }

    record Data(int cnt, double res, long startNum, boolean singh){}
}
