import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyTest {
    @Test
    public void NormalTest() {
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
        while(tree.hasNext()) {
            assertEquals(tree.next().toString(), res[i++]);
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
        while(tree.hasNext()) {
            assertEquals(tree.next().toString(), res[i++]);
        }

    }
}
