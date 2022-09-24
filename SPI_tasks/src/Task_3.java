import java.util.ArrayList;
public class Task_3 implements Runnable{

    public static void print(String s){
        System.out.println(s);
    }

    public static void main(String[] args) {
        ArrayList<Thread> arr = new ArrayList<>();

        for(int i = 0; i<4; i++)
            arr.add(new Thread(new Task_3("Thread " + i)));

        arr.forEach(Thread::start);
    }

    private String text;

    public Task_3 (String text){
        this.text = text;
    }

    @Override
    public void run() {
        for(int i = 0; i<100; i++)
            print(text);
    }
}
