package Task2;

import java.util.ArrayList;
import java.util.List;

public class Bean {
    private List<Long> list = new ArrayList<>();
    private Bean bean = this;

    public Bean() {
        for (int i = 0; i < 1_000_000; i++) {
            list.add(1L);
        }
    }
}
