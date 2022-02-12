import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class Tests {

    private void processing(Thread thread){
        thread.start();
        try {
            thread.join();
        } catch (Exception e){ e.fillInStackTrace(); }
    }

    private void finish(Thread thread){
        try {
            thread.join();
        } catch (Exception e){ e.fillInStackTrace(); }
    }

    @Test
    public void checkerTest(){
        Checker checker = new Checker(List.of(123,12,10));
        processing(checker);
        Assertions.assertEquals(true, checker.getResult());

        checker = new Checker(List.of(1,2,3));
        processing(checker);
        Assertions.assertEquals(false, checker.getResult());

        checker = new Checker(List.of(17,17,3,5,7,197,18));
        processing(checker);
        Assertions.assertEquals(true, checker.getResult());
    }

    @Test
    public void GeneratorTest(){
        Generator generator = new Generator(10);
        Assertions.assertEquals(10,generator.getList().size());
        Assertions.assertEquals(generator.getList(), generator.getList());

        Checker checker = new Checker(generator.getList());
        processing(checker);
        Assertions.assertEquals(false,checker.getResult());
    }

    @Test
    public void StepByStepTest() throws Exception {
        Generator generator = new Generator(100);

        StepByStep stepByStep = new StepByStep(generator.getList());
        Checker checker = new Checker(generator.getList());

        finish(stepByStep);
        processing(checker);

        Assertions.assertEquals(checker.getResult(),stepByStep.getResult());

        List<Integer> list = new LinkedList<>((new Generator(9999)).getList());
        list.set(7777, 666);

        stepByStep = new StepByStep(list);
        checker = new Checker(list);

        finish(stepByStep);
        processing(checker);

        Assertions.assertEquals(checker.getResult(), stepByStep.getResult());
    }

    @Test
    public void ParallelTest() throws Exception {
        Generator generator = new Generator(666);

        Parallel parallel = new Parallel(generator.getList(), 6);
        Checker checker = new Checker(generator.getList());

        finish(parallel);
        processing(checker);

        Assertions.assertEquals(checker.getResult(),parallel.getResult());

        List<Integer> list = new LinkedList<>((new Generator(9777)).getList());
        list.set(7777, 666);

        parallel = new Parallel(list, 3);
        checker = new Checker(list);

        finish(parallel);
        processing(checker);

        Assertions.assertEquals(checker.getResult(), parallel.getResult());
    }

    @Test
    public void ParallelStreamTest() throws Exception {
        Generator generator = new Generator(90);

        ParallelStream parallelStream = new ParallelStream(generator.getList());
        Checker checker = new Checker(generator.getList());

        finish(parallelStream);
        processing(checker);

        Assertions.assertEquals(checker.getResult(),parallelStream.getResult());

        List<Integer> list = new LinkedList<>((new Generator(1111)).getList());
        list.set(77, 105);

        parallelStream = new ParallelStream(list);
        checker = new Checker(list);

        finish(parallelStream);
        processing(checker);

        Assertions.assertEquals(checker.getResult(), parallelStream.getResult());
    }
}