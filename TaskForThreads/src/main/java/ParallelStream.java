import java.util.List;

public class ParallelStream extends Thread {
    private List<Integer> list;
    private boolean result;
    private long startTime;
    private long finishTime;

    ParallelStream(List<Integer> list){
        this.list = list;
        startTime = System.currentTimeMillis();
        start();
    }

    @Override
    public void run() {
        System.out.println("Thread ParallelStream started..");
        //result = list.parallelStream().anyMatch(n ->isPrime(n));

        finishTime = System.currentTimeMillis()-startTime;
        System.out.format("Thread ParallelStream finished in %ds %dms\n", finishTime / 1000, finishTime % 1000);
    }

    public boolean getResult() throws Exception {
        if(this.isAlive()) throw new Exception();
        return result;
    }
}