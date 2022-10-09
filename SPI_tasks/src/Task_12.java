import java.util.*;
import java.util.concurrent.TimeUnit;

public class Task_12 {
    public static void main(String[] args) {
        Task_12 obj = new Task_12();
        new Thread(new Sorting(obj)).start();
        obj.read();
    }

    static class Sorting implements Runnable{
        private final Task_12 obj;

        public Sorting(Task_12 obj) {
            this.obj = obj;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                obj.sort();
            }
        }
    }

    private List<String> list = new LinkedList<>();

    private void read(){
        Scanner scanner = new Scanner(System.in);
        String str;

        while(true){
            str = scanner.nextLine();
            if(str.equals("")) print();
            else addElem(str);
        }
    }

    private synchronized void addElem(String str){
        list.add(str);
    }

    private synchronized void print(){
        list.forEach(System.out::println);
    }
    private synchronized void sort(){
        System.out.println("START SORTING PROCESS");
        //Collections.sort(list);
        bubbleSort(list);
        System.out.println("FINISH SORTING");
    }

    private void bubbleSort(List<String> unsorted){
        String min;
        int current;
        Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
        for(int i = 0; i<unsorted.size()-1; i++){
            min = unsorted.get(i);
            current = i;
            for(int k = i+1; k< unsorted.size(); k++){
                if(comparator.compare(min,unsorted.get(k)) > 0) {
                    min = unsorted.get(k);
                    current = k;
                }
            }
            swap(i,current, unsorted);
        }
    }

    private void swap(int i, int k, List<String> unsorted){
        String tmp = unsorted.get(i);
        unsorted.set(i, unsorted.get(k));
        unsorted.set(k,tmp);
    }
}
