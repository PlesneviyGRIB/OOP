/**
 * class that demonstrate simple order
 */
public class Order {
    private static int cnt = 0;
    private final int id = ++cnt;
    private final Food food;
    private final int deliveryTimeRequired;

    public Order(Food food, int deliveryTimeRequired) {
        this.food = food;
        this.deliveryTimeRequired = deliveryTimeRequired;
    }

    public int getId() { return id; }

    public  Food getFood() { return food; }

    public int getDeliveryTimeRequired() { return deliveryTimeRequired; }

    @Override
    public String toString(){
        return "|id: " + String.format("%2d",id) + "| " + food.getInfo();
    }
}