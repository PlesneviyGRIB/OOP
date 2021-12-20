import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyTest {
    @Test
    public void NormalTest() throws Exception{
        Tree tree = new Tree();

        tree.addWithBFS(null,"hi");
        tree.addWithBFS("hi","Kirill");
        tree.addWithBFS("hi","Egor");
        tree.addWithBFS("hi","Semen");
        tree.addWithBFS("Semen",1);
        tree.addWithBFS("Semen",2);
        tree.addWithBFS("Semen",3);
        tree.addWithBFS("Egor",'d');
        tree.rmWithBFS('d');

        String[] res = new String[]{"hi","Kirill","Egor","Semen","1","2","3"};

        int i = 0;
        for (Tree it = tree; it.hasNext(); ) {
            Object current = it.next();
            assertEquals(current.toString(), res[i++]);
        }

        tree.rmWithBFS("hi");

        tree.addWithDFS(null,"hi");
        tree.addWithDFS("hi","Kirill");
        tree.addWithDFS("hi","Egor");
        tree.addWithDFS("hi","Semen");
        tree.addWithDFS("Semen",1);
        tree.addWithDFS("Semen",2);
        tree.addWithDFS("Semen",3);
        tree.addWithDFS("Egor",'d');
        tree.rmWithBFS('d');

        i = 0;
        for (Tree it = tree; it.hasNext(); ) {
            Object current = it.next();
            assertEquals(current.toString(), res[i++]);
        }

        Tree tr = new Tree();
        tr.addWithDFS(null, "first");
        Throwable e = assertThrows(Exception.class, () -> {
            tr.addWithBFS(1,"wre");
        });
        assertEquals(e.getMessage(), "No such parent in tree!");

        Throwable e1 = assertThrows(Exception.class, () -> {
            tr.rmWithBFS("wre");
        });
        assertEquals(e1.getMessage(), "No such node in tree!");

    }
}
