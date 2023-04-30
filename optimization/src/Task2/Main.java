package Task2;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println(ProcessHandle.current().pid());

        Singleton singleton = Singleton.getInstance();

        Runnable runnable = () -> {
            Singleton ref = singleton;
            Bean bean = new Bean();

            try {
                Thread.sleep(100_000);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        int cnt = 5;
        ExecutorService executor = Executors.newFixedThreadPool(cnt);

        for (int i = 0; i < cnt; i++) {
            executor.execute(runnable);
        }

        executor.shutdown();
    }

}
