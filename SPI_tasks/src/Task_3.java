import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task_3 implements Runnable{

    public static void print(String s){
        System.out.println(s);
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 0; i<4; i++)
            executorService.execute(new Task_3("Thread " + i));
    }

    private String text;

    public Task_3 (String text){
        this.text = text;
    }

    @Override
    public void run() {
        for(int i = 0; i<10; i++)
            print(text);
    }
}
