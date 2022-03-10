import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class test_Task_1_1_1 {
    @Test
    public void emptyArrayTest() {
        int[] arr = {};
        int[] ans = {};
        Task_1_1_1 sort = new Task_1_1_1();
        sort.sort(arr);
        Assertions.assertArrayEquals(ans, arr);
    }

    @Test
    public void nonemptyArrayTest() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] ans = {1, 2, 3, 4, 5};
        Task_1_1_1 sort = new Task_1_1_1();
        sort.sort(arr);
        Assertions.assertArrayEquals(ans, arr);
    }

    @Test
    public void testWithSingh(){
        int[] arr = {-1, -5, 0, 4, 3, 2, 1};
        int[] ans = {-5, -1, 0, 1, 2, 3, 4};
        Task_1_1_1 sort = new Task_1_1_1();
        sort.sort(arr);
        Assertions.assertArrayEquals(ans, arr);
    }
}
