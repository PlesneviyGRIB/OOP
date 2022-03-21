import java.util.Random;

/**
 * class for generate orders
 */

public class OrdersGenerator{
    private Random random;

    OrdersGenerator(){ random = new Random(); }

    public Order next(){
        return new Order(new Pizza(Pizza.KindOfPizza.values()[Math.abs(random.nextInt()) % Pizza.KindOfPizza.values().length],
                                   Pizza.PizzaSize.values()[Math.abs(random.nextInt()) % Pizza.PizzaSize.values().length]),
                Math.abs(random.nextInt()) % 20);
    }
}