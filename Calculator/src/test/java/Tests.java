import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {

    @Test
    public void AllExceptionsTest() throws Exception{
        Calculations c = new Calculations();

        Throwable e = assertThrows(Exception.class, () -> {
            c.newCalculation("/ 2 0");
        });
        assertEquals(e.getMessage(), "Division by zero occurred!");

        Throwable e1 = assertThrows(Exception.class, () -> {
            c.newCalculation("sqrt -3");
        });
        assertEquals(e1.getMessage(), "Negative value under sqrt!");

        Throwable e2 = assertThrows(Exception.class, () -> {
            c.newCalculation("log -3");
        });
        assertEquals(e2.getMessage(), "Value for function log have to be positive!");

        Throwable e3 = assertThrows(Exception.class, () -> {
            c.newCalculation("logdd -3");
        });
        assertEquals(e3.getMessage(), "Wrong term!");

        Throwable e4 = assertThrows(Exception.class, () -> {
            c.newCalculation("sqrt - + * / pow 2");
        });
        assertEquals(e4.getMessage(), "Wrong term!");
    }

    @Test
    public void NormalTest() throws Exception {
        Calculations c = new Calculations();

        assertEquals(c.newCalculation("sqrt - + * / pow 2 5 8 15 7 3") ,  8);

        assertEquals(c.newCalculation("+ sqrt - + * / pow 2 5 8 15 7 3 10") ,  18);

        assertEquals(c.newCalculation("- + sqrt - + * / pow 2 5 8 15 7 3 10 -12") ,  30);

        assertEquals(c.newCalculation("/ 6 + + 2 2 2") ,  1);
    }
}