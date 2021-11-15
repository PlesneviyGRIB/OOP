import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {

    @Test
    public void AllExceptionsTest() throws Exception{
        Throwable e = assertThrows(Exception.class, () -> {
            Calculations c = new Calculations("/ 2 0");
        });
        assertEquals(e.getMessage(), "Division by zero occurred!");

        Throwable e1 = assertThrows(Exception.class, () -> {
            Calculations c1 = new Calculations("sqrt -3");
        });
        assertEquals(e1.getMessage(), "Negative value under sqrt!");

        Throwable e2 = assertThrows(Exception.class, () -> {
            Calculations c2 = new Calculations("log -3");
        });
        assertEquals(e2.getMessage(), "Value for function log have to be positive!");

        Throwable e3 = assertThrows(Exception.class, () -> {
            Calculations c3 = new Calculations("logdd -3");
        });
        assertEquals(e3.getMessage(), "Wrong term!");

    }

    @Test
    public void NormalTest() throws Exception {
        Calculations c = new Calculations("sqrt - + * / pow 2 5 8 15 7 3");
        assertEquals(c.getResult() ,  8);

        Calculations c1 = new Calculations("+ sqrt - + * / pow 2 5 8 15 7 3 10");
        assertEquals(c1.getResult() ,  18);

        Calculations c2 = new Calculations("- + sqrt - + * / pow 2 5 8 15 7 3 10 -12");
        assertEquals(c2.getResult() ,  30);
    }
}