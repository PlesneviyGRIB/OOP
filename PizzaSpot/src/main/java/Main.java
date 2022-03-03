import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void createOrders(BlockingQueue<Order> orders){
        orders.add(new Order(new Pizza(Pizza.KindOfPizza.FOURCHEESES, Pizza.PizzaSize.MEDIUM),2));
        orders.add(new Order(new Pizza(Pizza.KindOfPizza.CRUDO, Pizza.PizzaSize.LARGE),30));
        orders.add(new Order(new Pizza(Pizza.KindOfPizza.CARBONARA, Pizza.PizzaSize.LARGE),2));
        orders.add(new Order(new Pizza(Pizza.KindOfPizza.FOURCHEESES, Pizza.PizzaSize.EXTRALARGE),13));
        orders.add(new Order(new Pizza(Pizza.KindOfPizza.MARINARA, Pizza.PizzaSize.MEDIUM),7));
        orders.add(new Order(new Pizza(Pizza.KindOfPizza.CARBONARA, Pizza.PizzaSize.MEDIUM),19));
        orders.add(new Order(new Pizza(Pizza.KindOfPizza.MARGARITA, Pizza.PizzaSize.EXTRALARGE),4));
        orders.add(new Order(new Pizza(Pizza.KindOfPizza.FOURCHEESES, Pizza.PizzaSize.SMALL),6));

    }
    public  static void main(String... args) throws Exception{

        Orders orders = new Orders();
        createOrders(orders);
        Storage storage = new Storage(10);

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new Cook(orders,storage));
        executorService.execute(new Cook(orders,storage));
        executorService.execute(new Deliveryman(storage,2));
        executorService.execute(new Deliveryman(storage,1));
        executorService.execute(new Deliveryman(storage,4));

        TimeUnit.SECONDS.sleep(50);

        executorService.shutdown();
    }
}