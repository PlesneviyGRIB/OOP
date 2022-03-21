import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * class with static method for take data for system from file with
 * serialized object-DataForSystem
 */

public class DataForSystem {
    private final int storageSize;
    private final int cooksCount;
    private final int[] capacitiesOfDeliveriesBags;
    private final int workingTime;

    DataForSystem(int storageSize, int cooksCount, int[] capacitiesOfDeliveriesBags, int workingTime) {
        this.workingTime = workingTime;
        this.cooksCount = cooksCount;
        this.capacitiesOfDeliveriesBags = capacitiesOfDeliveriesBags;
        this.storageSize = storageSize;
    }

    public static DataForSystem getDataForSystemFromFile(String path) {
        try(FileReader fileReader = new FileReader(path)) {
            return new GsonBuilder().create().fromJson(new Scanner(fileReader).next(), DataForSystem.class);
        } catch (IOException e) { e.printStackTrace(); }
        return null;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public int getCooksCount() {
        return cooksCount;
    }

    public int[] getCapacitiesOfDeliveriesBags() {
        return capacitiesOfDeliveriesBags;
    }

    public int getWorkingTime() {
        return workingTime;
    }
}