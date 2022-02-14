import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Generator generator = new Generator(700000);
        System.out.println("Generator complete...");

        List<Integer> list  = new ArrayList<>(generator.getList());

        StepByStep stepByStep = new StepByStep(list);
        Parallel parallel = new Parallel(list, 8);
        ParallelStream parallelStream = new ParallelStream(list);

        try{
            stepByStep.join();
            parallel.join();
            parallelStream.join();
            System.out.println(stepByStep.getResult());
            System.out.println(parallel.getResult());
            System.out.println(parallelStream.getResult());
        }
        catch (Exception e){ System.out.println(e.fillInStackTrace()); }
    }
}