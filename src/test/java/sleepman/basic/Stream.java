package sleepman.basic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Stream {

    private List<Sample> sample1;

    @Before
    public void setup() {
        this.sample1 = new ArrayList<>();
    }

    @Test
    public void streamTest1() {

    }

    class Sample {
        private Integer id;
        private String name;
        private String data;
    }

}
