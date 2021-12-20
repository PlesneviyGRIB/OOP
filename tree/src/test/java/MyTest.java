import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyTest {
    @Test
    public void NormalTest() {
        Tree tree = new Tree();

        tree.addNode(null,"hi");
        tree.addNode("hi","Kirill");
        tree.addNode("hi","Egor");
        tree.addNode("hi","Semen");
        tree.addNode("Semen",1);
        tree.addNode("Semen",2);
        tree.addNode("Semen",3);
        tree.addNode("Egor",'d');
        tree.rmNode('d');

        String[] res = new String[]{"hi","Kirill","Egor","Semen","1","2","3"};

        int i = 0;
        while(tree.hasNext()) {
            assertEquals(tree.next().toString(), res[i++]);
        }
    }
}
