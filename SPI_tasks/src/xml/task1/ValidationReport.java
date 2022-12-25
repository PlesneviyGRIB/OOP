package xml.task1;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ValidationReport {
    private Map<String, Person> people;

    public ValidationReport(Map<String, Person> people) {
        this.people = people;
    }

    public void report(){

        System.out.println("VALIDATION:");

        AtomicInteger atomicInteger0 = new AtomicInteger(0);
        AtomicInteger atomicInteger1 = new AtomicInteger(0);

        people.values().stream().filter(p -> p.getValidation().containsKey("siblings-number")).forEach(p ->{
            atomicInteger0.incrementAndGet();
            if(p.getSiblings().size() < Integer.parseInt(p.getValidation().get("siblings-number"))){
                atomicInteger1.incrementAndGet();
            }
        });
        System.out.println("siblings-param:   total - " + atomicInteger0.get() + "   wrong - " + atomicInteger1.get());


        AtomicInteger atomicInteger2 = new AtomicInteger(0);
        AtomicInteger atomicInteger3 = new AtomicInteger(0);

        people.values().stream().filter(p -> p.getValidation().containsKey("children-number")).forEach(p ->{
            atomicInteger2.incrementAndGet();
            if(p.getChildren().size() < Integer.parseInt(p.getValidation().get("children-number"))){
                atomicInteger3.incrementAndGet();
            }
        });
        System.out.println("children-param:   total - " + atomicInteger2.get() + "   wrong - " + atomicInteger3.get());
    }
}
