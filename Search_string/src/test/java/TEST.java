import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.FileReader;

public class TEST{
    @Test
    public void NormalArrayTest() throws IOException {
        String path = "test0.txt";
        FileReader read1 = new FileReader(path);
        String s = "aabaab";

        int[] myres = new int[4];
        myres = Search_class.Search(s, read1);
        int[] res = new int[4];
        res[0] = 0; res[1] = 10; res[2] = 13; res[3] = 16;

        Assertions.assertArrayEquals(res, myres);
    }

    @Test
    public void emptyStringArrayTest() throws IOException {
        String path = "test0.txt";
        FileReader read1 = new FileReader(path);
        String s = "";

        int[] myres = new int[2];
        myres = Search_class.Search(s, read1);
        int[] res = new int[2];
        res[0] = 1; res[1] = 0;

        Assertions.assertArrayEquals(res, myres);
    }

    @Test
    public void emptyFileArrayTest() throws IOException {
        String path = "test1.txt";
        FileReader read1 = new FileReader(path);
        String s = "aabaab";

        int[] myres = new int[2];
        myres = Search_class.Search(s, read1);
        int[] res = new int[2];
        res[0] = 2; res[1] = 0;

        Assertions.assertArrayEquals(res, myres);
    }
}