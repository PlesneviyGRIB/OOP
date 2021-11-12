import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {

    @Test
    public void AllExceptionsTest() throws Exception{
        Calculations calculations = new Calculations();

        calculations.newTerm("/ 2 0");
        Throwable e = assertThrows(Exception.class, () -> {
            calculations.getResult();
        });
        assertEquals(e.getMessage(), "Dvision by zero occurred!");

        calculations.newTerm("sqrt -3");
        Throwable e1 = assertThrows(Exception.class, () -> {
            calculations.getResult();
        });
        assertEquals(e1.getMessage(), "Negative value under sqrt!");

        calculations.newTerm("log -3");
        Throwable e2 = assertThrows(Exception.class, () -> {
            calculations.getResult();
        });
        assertEquals(e2.getMessage(), "Value for function log have to be positive!");

        calculations.newTerm("/ / 2 2");
        Throwable e3 = assertThrows(Exception.class, () -> {
            calculations.getResult();
        });
        assertEquals(e3.getMessage(), "Wrong term!");

        Throwable e4 = assertThrows(Exception.class, () -> {
            calculations.newTerm("/ / 2we 2");
        });
        assertEquals(e4.getMessage(), "Wrong term!");
    }

    @Test
    public void NormalTest() throws Exception {
        Calculations calculations = new Calculations();

        calculations.newTerm("sqrt - + * / pow 2 5 8 15 7 3");
        assertEquals(calculations.getResult() ,  8);
        assertEquals(calculations.getResult() ,  8);

        calculations.newTerm("+ sqrt - + * / pow 2 5 8 15 7 3 10");
        assertEquals(calculations.getResult() ,  18);

        calculations.newTerm("- + sqrt - + * / pow 2 5 8 15 7 3 10 -12");
        assertEquals(calculations.getResult() ,  30);
    }
}