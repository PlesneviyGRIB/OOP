
public class Main {
    private static final String path = "/home/egor/IdeaProjects/PizzaSpot/src/data.json";

    public  static void main(String... args) throws Exception {

        new PizzaSpot(DataForSystem.getDataForSystemFromFile(path));

    }
}