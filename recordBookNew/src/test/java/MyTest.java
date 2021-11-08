import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyTest {
    @Test
    public void allExceptionsTest() throws Exception {
        RecordBook r = new RecordBook("Vasiya", "Pupkin");

        Throwable e = assertThrows(Exception.class, () -> {
            r.addMark("PE", 6,1);
        });
        assertEquals(e.getMessage(), "Wrong value of mark!");

        Throwable e1 = assertThrows(Exception.class, () -> {
            r.addMark("PE", 4,0);
        });
        assertEquals(e1.getMessage(), "Wrong value for semester!");

        Throwable e2 = assertThrows(Exception.class, () -> {
            r.setQualificationWork(10);
        });
        assertEquals(e2.getMessage(), "Wrong value of mark!");

        r.addMark("Math",5,1);
        Throwable e3 = assertThrows(Exception.class, () -> {
            r.greatDiploma();
        });
        assertEquals(e3.getMessage(), "Set qualification work mark!");
    }

    @Test
    public void normalTestWithAllMethods() throws Exception {
        RecordBook r = new RecordBook("Vasiya", "Pupkin");

        Assertions.assertEquals(r.averageScore(),0);

        r.setQualificationWork(4);
        Assertions.assertEquals(r.greatDiploma(), false);

        r.setQualificationWork(5);
        Assertions.assertEquals(r.greatDiploma(), true);

        Assertions.assertEquals(r.isHighScholarship(), false);

        r.addMark("Math",5,1);
        r.addMark("Math",3,3);
        r.addMark("Math",3,4);
        r.addMark("History",5,3);
        r.addMark("Math",2,3);
        r.addMark("Math",3,3);
        r.addMark("PE",5,4);
        r.addMark("PE",5,4);
        r.addMark("PE",5,4);
        r.addMark("PE",3,4);
        r.addMark("Geometry",4,5);
        r.addMark("Math",5,5);
        r.addMark("History",3,1);
        r.addMark("PE",2,7);

        Assertions.assertEquals(r.averageScore(),3,888889);
        Assertions.assertEquals(r.isHighScholarship(),false);
        Assertions.assertEquals(r.greatDiploma(),false);
    }
}