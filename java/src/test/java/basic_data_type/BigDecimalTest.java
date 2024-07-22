package basic_data_type;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;

public class BigDecimalTest {
    // 专门解决java小数精确性的问题
    @Test
    public void test() {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("2.0");
        BigDecimal c = new BigDecimal("3.0");

        BigDecimal x = a.subtract(b);
        BigDecimal y = b.subtract(c);
        assertEquals(x, y);
    }
}
