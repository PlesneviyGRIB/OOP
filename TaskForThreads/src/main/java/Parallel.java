import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Parallel extends Thread {
    private int cnt;
    private List<Thread> threads;
    private List<Integer> list;
    private boolean result;
    private long startTime;
    private long finishTime;

    Parallel(List<Integer> list, int cnt) {
        this.list = list;
        this.cnt = cnt;
        startTime = System.currentTimeMillis();
        this.threads = new ArrayList<>();
        delitel();
        start();
    }

    @Override
    public void run(){
        System.out.println("Thread Parallel started..");

        for (Thread thread: threads)
            thread.start();

        for(Thread thread: threads)
            try {
                thread.join();
            }
            catch (Exception e) { System.out.println(e.getMessage()); }

        Checker checker;
        for(Thread thread: threads) {
            result |= ((Checker) thread).getResult();
        }

        finishTime = System.currentTimeMillis()-startTime;
        System.out.format("Thread Parallel finished in %ds %dms\n", finishTime / 1000, finishTime % 1000);
    }

    private void delitel(){
        List list1 = new LinkedList(list);
        int step = list1.size() / cnt;

        while(!list1.isEmpty()){
            if(list1.size() > step) {
                threads.add(new Checker(new ArrayList<>(list1.subList(0, step))));
                list1 = new ArrayList(list1.subList(step, list1.size()));
            }
            else {
                threads.add(new Checker(list1));
                list1.clear();
            }
        }
    }

    public boolean getResult() throws Exception {
        if(this.isAlive()) throw new Exception();
        return result;
    }
}