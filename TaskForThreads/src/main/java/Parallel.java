import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.State.TERMINATED;

class Parallel extends Thread {
    private int cnt;
    private List<Thread> threads;
    private List<Integer> list;
    private boolean result = false;
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

        loop:
        for(;;) {
            for (Thread thread : threads)
                if (thread.getState().equals(TERMINATED) && ((Checker) thread).getResult()) {
                    result = true;
                    break loop;
                }
            if(threads.stream().allMatch(th -> th.getState().equals(TERMINATED))) break;
        }

//        for(Thread thread: threads)
//            System.out.println(thread.getState());

        if(!result) {
            Checker checker;
            for (Thread thread : threads) {
                result |= ((Checker) thread).getResult();
            }
        }

        finishTime = System.currentTimeMillis()-startTime;
        System.out.format("Thread Parallel finished in %ds %3dms\n", finishTime / 1000, finishTime % 1000);
    }

    private void delitel(){
        List list1 = new LinkedList(list);
        int step = list1.size() / cnt;

        while(!list1.isEmpty()){
            if(list1.size() >= step) {
                threads.add(new Checker(new LinkedList(list1.subList(0, step))));
                list1 = new LinkedList(list1.subList(step, list1.size()));
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