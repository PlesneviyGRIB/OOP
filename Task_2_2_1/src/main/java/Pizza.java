/**
 * class of one kind of food with two appropriate enums
 */
public class Pizza implements Food{
    public enum PizzaSize {SMALL, MEDIUM, LARGE, EXTRALARGE}
    public enum KindOfPizza {MARGARITA,MARINARA,FOURSEASONS,CARBONARA,WITHSEAFOOD,FOURCHEESES,NEAPOLITANO,CRUDO}

    private PizzaSize pizzaSize;
    private KindOfPizza kindOfPizza;
    private int timeOfCooking;

    public Pizza(KindOfPizza kindOfPizza, PizzaSize pizzaSize){
        this.kindOfPizza = kindOfPizza;
        this.pizzaSize = pizzaSize;
        this.timeOfCooking = getTimeOfCooking(kindOfPizza, pizzaSize);
    }

    public int getTime() { return timeOfCooking; }

    public String getInfo() { return this.toString(); }

    public static int getTimeOfCooking(KindOfPizza kindOfPizza, PizzaSize pizzaSize){
        int time = 0;
        switch (kindOfPizza){
            case MARGARITA, MARINARA, FOURSEASONS, FOURCHEESES -> time ++;
            case CARBONARA, WITHSEAFOOD, NEAPOLITANO, CRUDO -> time += 2;
            default -> time += 1;
        }
        switch (pizzaSize){
            case LARGE, EXTRALARGE -> time *= 2;
        }
        return time;
    }

    @Override
    public String toString() {
        return "pizza " + kindOfPizza.toString() + " " + pizzaSize.toString();
    }
}