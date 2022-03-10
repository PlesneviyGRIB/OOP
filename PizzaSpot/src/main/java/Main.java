import java.util.concurrent.*;

public class Main {
    private static final String path = "/home/egor/IdeaProjects/PizzaSpot/src/data.json";

    public static void createOrders(BlockingQueue<Order> orders, int cnt){
        OrdersGenerator ordersGenerator = new OrdersGenerator();
        while(cnt-- > 0) orders.add(ordersGenerator.next());
    }

    public  static void main(String... args) throws Exception{

        DataForSystem dataForSystem  = DataForSystem.getDataForSystemFromFile(path);

        Orders orders = new Orders();
        Storage storage = new Storage(dataForSystem.getStorageSize());
        createOrders(orders, dataForSystem.getOrdersCount());

        ExecutorService executorService = Executors.newCachedThreadPool();


        for(int i = 0 ; i< dataForSystem.getCooksCount(); i++) executorService.execute(new Cook(orders, storage));

        int[] deliveries = dataForSystem.getCapacitiesOfDeliveriesBags();
        for(int i = 0; i< deliveries.length; i++) executorService.execute(new Deliveryman(storage,deliveries[i]));

        executorService.shutdown();
    }
}