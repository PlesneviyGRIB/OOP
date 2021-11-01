import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TEST {
    @Test
    public void Normal_test() throws Exception {
        RecordBOOk r = new RecordBOOk("Vasya", "Pupkin", 271);

        Assertions.assertEquals(true, r.increased_scholarship());

        Subjects s = new Subjects();

        for(int i = 1; i<= 8;i++) {
            for(int j =0; j<s.getCount_sub();j++)
            r.setMark(i,s.getSub(j),4);
        }

        Assertions.assertEquals(r.averageScore(),4);
        Assertions.assertEquals(r.increased_scholarship(),false);

        r.setQualification(5);
        Assertions.assertEquals(r.honorsDegree(),false);
    }

    @Test
    public void All_exceptions_test() throws Exception {
        Throwable e = assertThrows(Exception.class, () -> {                            // bad exam score
            RecordBOOk r = new RecordBOOk("Vasya", "Pupkin", 1200);
        });
        assertEquals(e.getMessage(), "Inappropriate exam score");

        RecordBOOk r = new RecordBOOk("Vasya", "Pupkin", 120);

        Throwable e1 = assertThrows(Exception.class, () -> {                           // bad value of semester
            r.setMark(17,"История",5);
        });
        assertEquals(e1.getMessage(), "Wrong number of semester!");

        Throwable e2 = assertThrows(Exception.class, () -> {                           // bad course call
            r.setMark(1,"ИИИстория",5);
        });
        assertEquals(e2.getMessage(), "Wrong name of course!");

        Throwable e3 = assertThrows(Exception.class, () -> {                           // bad value of mark
            r.setMark(6,"История",6);
        });
        assertEquals(e3.getMessage(), "Wrong value of mark");

        Throwable e4 = assertThrows(Exception.class, () -> {                           // bad value of mark
            r.setMark(6,"История","зачет1");
        });
        assertEquals(e4.getMessage(), "Wrong value of mark");

        Throwable e5 = assertThrows(Exception.class, () -> {                           // bad value of qualification work
            r.setQualification(29);
        });
        assertEquals(e5.getMessage(), "Inappropriate value for qualifying work!");
    }
}
