import java.util.List;

public class Checker extends Thread {
    private List<Integer> list;
    private boolean result;

    Checker(List<Integer> list) { this.list = list; }

    @Override
    public void run() {
        System.out.println("    Thread started..");
        result = listChecker(list);
    }

    public boolean getResult() { return result; }

    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) if (n % i == 0) return false;
        return true;
    }

    private boolean listChecker(List<Integer> list) {
        for (Integer i : list) if (!isPrime(i)) return true;
        return false;
    }
}