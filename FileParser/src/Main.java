import lombok.SneakyThrows;

import java.util.*;
import java.util.stream.Stream;

class Generator{
    private static boolean[] resheto = new boolean[10000000];
    private static List prost;
    private int count;
    private Random random;

    static {
        Arrays.fill(resheto, true);
        for(int i = 2; i< resheto.length / 2; i++)
            for(int j = 2; j< resheto.length; j++)
                if(i*j < resheto.length)resheto[i*j] = false;
                else break;

        resheto[0]=false; resheto[1]=false;

        prost = new LinkedList<Integer>();
        for(int i = 0; i< resheto.length;i++)
            if(resheto[i]) prost.add(i);
    }

    Generator(int count){ this.count = count; this.random = new Random(count); }

    public ArrayList<Integer> getList(){
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i = 0; i< count; i++) res.add((int)prost.get(random.nextInt(prost.size())));
        return res;
    }
}

public class Main {
    private static boolean isPrime(int n){
        for(int i = 2; i * i <= n; i++) if(n % i == 0) return false;
        return true;
    }

    public boolean fatherCheck(List<Integer> list) {
        for(Integer i: list) if(!isPrime(i)) return true;
        return false;
    }

    interface Checker{ public boolean check();}

    class StepByStep extends Thread implements Checker{
        private List<Integer> list;
        StepByStep(List<Integer> list) { this.list = list; }

        @Override
        public void run(){
            System.out.println("Thread " + check());
        }

        @Override
        public boolean check() {return Main.this.fatherCheck(list);}
    }

    class Parallel extends Thread implements Checker {
        private int cnt;
        private Thread[] threads;
        private List<Integer> list;

        Parallel(List<Integer> list, int cnt) {
            this.list = list;
            this.cnt = cnt;
            this. threads = new Thread[cnt];
            delitel();
        }

        @Override
        public void run(){
            check();
        }

        @Override
        public boolean check() {
            for (Thread thread: threads)
                thread.start();
            return true;
        }

        private void delitel() {
            List list1 = new ArrayList<>(list);
            int step = list1.size() / cnt;

            for(int i = 0; i< threads.length - 1; i++){
                threads[i] = new StepByStep(new ArrayList<>(list1.subList(0,step)));
                list1 = new ArrayList(list1.subList(step, list1.size()));
            }
            threads[threads.length-1] = new StepByStep(list1);
        }
    }

    class ParallelStream implements Checker, Runnable{
        private List<Integer> list;
        ParallelStream(List<Integer> list){ this.list = list; }

        @Override
        public boolean check() {
                Stream.of(list).parallel().forEach(i -> Main.this.fatherCheck(i));
            return false;
        }

        @Override
        public void run() {

        }
    }

    public static void main(String[] args) throws Exception {
        Generator generator = new Generator(100000);
        System.out.println("Generator complete...");

        ArrayList<Integer> testArr = generator.getList();

        Main mainObj = new Main();
        Main.StepByStep stepByStep = mainObj.new StepByStep(testArr);
        Main.Parallel parallel = mainObj.new Parallel(testArr,4);
//        Main.ParallelStream parallelStream = mainObj.new ParallelStream(testArr);

        stepByStep.start();
        parallel.start();

//        mainObj.processing(parallelStream);

    }
}