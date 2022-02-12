import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Generator generator = new Generator(800000);
        System.out.println("Generator complete...");

        StepByStep stepByStep = new StepByStep(generator.getList());
        Parallel parallel = new Parallel(generator.getList(), 8);
        ParallelStream parallelStream = new ParallelStream(generator.getList());

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