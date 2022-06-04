import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Generator generator = new Generator(10000);
        System.out.println("Generator complete...");

        Thread stepByStep = new StepByStep(generator.getList());
        Thread parallel = new Parallel(generator.getList(), 6);
        Thread parallelStream = new ParallelStream(generator.getList());

        try{
            stepByStep.join();
            parallel.join();
            parallelStream.join();
            System.out.println(((StepByStep)stepByStep).getResult());
            System.out.println(((Parallel)parallel).getResult());
            System.out.println(((ParallelStream)parallelStream).getResult());
        }
        catch (Exception e){ System.out.println(e.fillInStackTrace()); }
    }
}