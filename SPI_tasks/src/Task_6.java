import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Task_6 {
    public static void main(String[] args) {
        new Founder(new Company(70)).start();
    }
}
final class Company {
    private final List<Department> departments;
    public Company(final int departmentsCount) {
        this.departments = new ArrayList<>(departmentsCount);
        for (int i = 0; i < departmentsCount; i++) {
            departments.add(i, new Department(i));
        }
    }
    /**
     * Вывод результата по всем отделам.
     * P.S. Актуально после того, как все отделы выполнят свою работу.
     */
    public void showCollaborativeResult() {
        System.out.println("All departments have completed their work.");
        final int result = departments.stream()
                .map(Department::getCalculationResult)
                .reduce(Integer::sum)
                .orElse(-1);
        System.out.println("The sum of all calculations is: " + result);
    }
    /**
     * @return Количество доступных отделов для симуляции выполнения
    работы.
     */
    public int getDepartmentsCount() {
        return departments.size();
    }
    /**
     * @param index Индекс для текущего свободного отдела.
     * @return Свободный отдел для симуляции выполнения работы.
     */
    public Department getFreeDepartment(final int index) {
        return departments.get(index);
    }
}

class Department {
    private final int identifier;
    private final int workingSeconds;
    private int calculationResult = 0;
    public Department(final int identifier) {
        this.identifier = identifier;
        this.workingSeconds = ThreadLocalRandom.current().nextInt(1, 6);
    }
    /**
     * Симуляция работы длительностью в workingSeconds секунд.
     * В данном случае просто вычисляем сумму.
     */
    public void performCalculations() {
        for (int i = 0; i < workingSeconds; i++) {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (final InterruptedException ignored) {
// Ignored case.
            }
            calculationResult += i;
        }
    }
    /**
     * @return Уникальный идентификатор для отдела,
     * по которому мы можем отличить его от других.
     */
    public int getIdentifier() {
        return identifier;
    }
    /**
     * ВАЖНО!
     * Далеко не самый правильный способ вычисления и получения данных,
     * но для демонстрации работы барьера пойдёт.
     *
     * @return Результат вычислений.
     */
    public int getCalculationResult() {
        return calculationResult;
    }
}

final class Founder {

    class Worker implements Runnable{
        private Department department;
        Worker(Department department){
            this.department = department;
        }
        @Override
        public void run() {
            department.performCalculations();
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private CyclicBarrier barrier;
    private final List<Runnable> workers;
    public Founder(final Company company) {
        int cnt = company.getDepartmentsCount();

        this.workers = new ArrayList<>(cnt);

        barrier = new CyclicBarrier(cnt, company::showCollaborativeResult);

        for(int i = 0; i<cnt; i++){
            workers.add(new Worker(company.getFreeDepartment(i)));
        }
    }
    public void start() {
        for (final Runnable worker : workers) {
            new Thread(worker).start();
        }
    }
}