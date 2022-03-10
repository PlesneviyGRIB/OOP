import java.util.List;

public class StepByStep extends Thread{
    private Checker checker;
    private boolean result;
    private long startTime;
    private long finishTime;

    StepByStep(List<Integer> list) {
        checker = new Checker(list);
        startTime = System.currentTimeMillis();
        start();
    }

    @Override
    public void run(){
        System.out.println("Thread StepByStep started..");

        checker.start();
        try {
            checker.join();
            result = checker.getResult();
        }
        catch (Exception e ){ System.out.println(e.getMessage());}

        finishTime = System.currentTimeMillis() -startTime;
        System.out.format("Thread StepByStep finished in %ds %3dms\n", finishTime / 1000, finishTime % 1000);
    }

    public boolean getResult() throws Exception {
        if(this.isAlive()) throw new Exception();
        return result;
    }
}