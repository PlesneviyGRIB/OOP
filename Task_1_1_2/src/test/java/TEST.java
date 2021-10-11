import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TEST{
    @Test
    public void NormalArrayTest() throws IOException {
        String path = "test0.txt";
        String s = "aabaab";
        int[] myres = new int[4];
        myres = Search_class.Search(s, path);
        int[] res = new int[4];
        res[0] = 0; res[1] = 10; res[2] = 13; res[3] = 16;

        Assertions.assertArrayEquals(res, myres);
    }

    @Test
    public void emptyStringArrayTest() throws IOException {
        String path = "test0.txt";
        String s = "";
        int[] myres = new int[0];
        myres = Search_class.Search(s, path);
        int[] res = new int[0];

        Assertions.assertArrayEquals(res, myres);
    }

    @Test
    public void emptyFileArrayTest() throws IOException {
        String path = "test1.txt";
        String s = "aabaab";
        int[] myres = new int[0];
        myres = Search_class.Search(s, path);
        int[] res = new int[0];

        Assertions.assertArrayEquals(res, myres);
    }

    @Test
    public void NoneStringInFileArrayTest() throws IOException {
        String path = "test1.txt";
        String s = "@#";
        int[] myres = new int[0];
        myres = Search_class.Search(s, path);
        int[] res = new int[0];

        Assertions.assertArrayEquals(res, myres);
    }

    @Test
    public void NoExistingFile() throws IOException {
        String path = "test123.txt";
        String s = "@#";
        Throwable thrown = assertThrows(IOException.class, () -> {
            int[] check = Search_class.Search(s, path);
        });
        assertEquals(thrown.getMessage(), "test123.txt (Нет такого файла или каталога)");
    }
}