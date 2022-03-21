import java.util.concurrent.TimeUnit;

public class WorkingDay extends Thread {
    private final int time;

    WorkingDay(int time){
        this.time = time;
    }

    public synchronized boolean isOver(){
        return !this.isAlive();
    }

    @Override
    public void run(){
        System.out.println("*** DAY STARTS ***");
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("*** WORKING DAY IS OVER ***");
    }
}