package org.mailreport;

import org.junit.jupiter.api.Test;
import org.mailreport.models.Worker;
import org.mailreport.supportive.MyUtils;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
    public void exceptionsTest(){
        Throwable e = assertThrows(Exception.class, () -> {
            new Worker("", "", "worker@@gail.com");
        });
        assertEquals(e.getMessage(), "Wrong email");
    }

    @Test
    public void myUtilsTest(){
        assertFalse(MyUtils.isEmail("@"));
        assertFalse(MyUtils.isEmail("qw@/gmail.com"));
        assertFalse(MyUtils.isEmail("123"));

        assertTrue(MyUtils.isEmail("grib@gmail.com"));
        assertTrue(MyUtils.isEmail("e.grib@g.nsu.ru"));

        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("world");

        assertEquals(MyUtils.listToString(list), "Hello world");
    }
}
