import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class DataForSystem {
    private final int storageSize;
    private final int cooksCount;
    private final int[] capacitiesOfDeliveriesBags;
    private final int ordersCount;

    DataForSystem(int storageSize, int cooksCount, int[]capacitiesOfDeliveriesBags, int ordersCount){
        this.storageSize = storageSize;
        this.cooksCount = cooksCount;
        this.capacitiesOfDeliveriesBags = capacitiesOfDeliveriesBags;
        this.ordersCount = ordersCount;
    }

    public static DataForSystem getDataForSystemFromFile(String path) throws FileNotFoundException {
        return new GsonBuilder().create().fromJson(new Scanner(new FileReader(path)).next(), DataForSystem.class);
    }

    public int getStorageSize() { return storageSize; }

    public int getCooksCount() { return cooksCount; }

    public int[] getCapacitiesOfDeliveriesBags() { return capacitiesOfDeliveriesBags; }

    public int getOrdersCount() { return ordersCount; }
}