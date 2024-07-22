package basic_data_type;

import org.junit.Test;
import static org.junit.Assert.*;

public class PackingTest {
    @Test
    public void testPackAndUnpack() throws Exception {
        Integer a = 40;
        Integer b = new Integer(40);
        assertNotSame(a, b);
        assertEquals(a, b);

        Integer c = Integer.valueOf(40);
        assertSame(a, c);
        assertEquals(a, c);
    }


    @Test
    public void testCost() throws Exception {
        // 这里会频繁的拆箱装箱，比较耗时
        Long sum = 0L;
        for (long i = 0L; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
    }
}
