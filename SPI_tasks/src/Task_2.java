public class Task_2 implements Runnable{
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Task_2());
        thread.start();

        thread.join();
        //try { thread.join(); }
        //catch (InterruptedException e) {throw new RuntimeException(e);}

        for(int i = 0; i<10; i++)
            System.out.println("Main thread");
    }

    @Override
    public void run() {
        for(int i = 0; i<1000; i++)
            System.out.println("Supportive thread");
    }
}