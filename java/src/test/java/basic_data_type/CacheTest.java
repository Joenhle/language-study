package basic_data_type;

import org.junit.Test;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void testIntegerCache() {
        // 除了浮点类型外的其它基本类型的包装类型在一个较小的范围内使用了缓存对象
        Integer a = 32;
        Integer b = 32;

        assertSame(a, b);
        assertEquals(a, b);

        Integer c = 200;
        Integer d = 200;
        assertNotSame(c,d);
        assertEquals(c, d);

        Float e = 2.0f;
        Float f = 2.0f;
        assertNotSame(e, f);
        assertEquals(e, f);
    }
}
