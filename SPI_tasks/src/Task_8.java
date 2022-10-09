import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class Task_8 {
    private static int cntOfThreads;

    public static void main(String[] args) {

        cntOfThreads = checkParams(args);

        AtomicReference<List<Future<Double>>> listAtomicReference = new AtomicReference<>();
        listAtomicReference.set(new ArrayList<>());

        AtomicReference<List<Calculator>> callableListAtomicReference = new AtomicReference<>();
        callableListAtomicReference.set(new ArrayList<>());

        AtomicReference<ExecutorService> executorServiceAtomicReference = new AtomicReference<>();
        executorServiceAtomicReference.set(Executors.newFixedThreadPool(cntOfThreads));

        AtomicReference<CountDownLatch> countDownLatchAtomicReference = new AtomicReference<>();
        countDownLatchAtomicReference.set(new CountDownLatch(1));

        AtomicReference<CountDownLatch> countDownLatchAtomicReference0 = new AtomicReference<>();
        countDownLatchAtomicReference0.set(new CountDownLatch(cntOfThreads));


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown hook ran!");
            executorServiceAtomicReference.get().shutdownNow();

            try {
                countDownLatchAtomicReference0.get().await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            long maxCnt = callableListAtomicReference.get().stream().map(c -> c.getCnt()).max(Long::compareTo).get();
            callableListAtomicReference.get().forEach(c -> c.setMaxCnt(maxCnt));

            countDownLatchAtomicReference.get().countDown();

            result(listAtomicReference.get());
        }
        ));

        boolean singh = false;
        for(int i = 0; i<cntOfThreads; i++) {
            var callable = new Calculator(singh, 3 + i * 2, cntOfThreads * 2, countDownLatchAtomicReference.get(), countDownLatchAtomicReference0.get());
            callableListAtomicReference.get().add(callable);
            listAtomicReference.get().add(executorServiceAtomicReference.get().submit(callable));
            singh = !singh;
        }
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

    static void result(List<Future<Double>> futures) {
        double res;
        res = futures.stream().map(f -> {
            try {
                return f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).mapToDouble(d -> d).sum();
        res +=1;

        System.out.println("RES: " + res);
        System.out.println("MISTAKE: " + (res - Math.PI/4));
    }

    static class Calculator implements Callable<Double>{
	    private volatile long cnt;
        private volatile long maxCnt;
        private boolean singh;
        private final long startNum;
        private final long pace;

        private CountDownLatch countDownLatch;
        private CountDownLatch countDownLatch0;

        public Calculator(boolean singh, long startNum, long pace, CountDownLatch countDownLatch, CountDownLatch countDownLatch0) {
            this.singh = singh;
            this.startNum = startNum;
            this.pace = pace;
            this.countDownLatch = countDownLatch;
            this.countDownLatch0 = countDownLatch0;
        }
        @Override
        public Double call() throws InterruptedException {
            double res = 0;
            long del = startNum;

            while (!Thread.currentThread().isInterrupted()){
                cnt++;
                if(singh) res += 1.0 / del;
                else res -= 1.0 / del;
                del += pace;
                if(cntOfThreads % 2 == 1) singh = !singh;
            }

            Thread.interrupted(); // to reset interrupted flag
            countDownLatch0.countDown();
            countDownLatch.await();

            for(long i = 0; i<maxCnt-cnt; i++){
                if(singh) res += 1.0 / del;
                else res -= 1.0 / del;
                del += pace;
                if(cntOfThreads % 2 == 1) singh = !singh;
            }

            return res;
        }

        public void setMaxCnt(long maxCnt){
            this.maxCnt = maxCnt;
        }

        public long getCnt(){
            return cnt;
        }
    }
}
