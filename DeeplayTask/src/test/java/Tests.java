import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {

    @Test
    public void getResultTest(){
        assertEquals(10, Solution.getResult("STWSWTPPTPTTPWPP", "Human"));
        assertEquals(15, Solution.getResult("STWSWTPPTPTTPWPP", "Swamper"));
        assertEquals(14, Solution.getResult("TTPTSPSTSTSWWSPW", "Woodman"));

        Throwable e;

        e = assertThrows(Exception.class, () -> Solution.getResult("STWSWTPPTPTTPWPP", "Woodman123"));
        assertEquals("Wrong input data", e.getMessage());

        e = assertThrows(Exception.class, () -> Solution.getResult("STWSWT0@&^", "Woodman"));
        assertEquals("Wrong input data", e.getMessage());
    }
}
