package basic_data_type;

import org.junit.Test;
import static org.junit.Assert.*;

public class InitialTest {

    private int a;
    private static int b;

    @Test
    public void test() {
        // 成员变量和静态成员变量没有初始化会有默认值，但是局部变量没有初始化会编译报错。
        // TODO 这里具体的原因可以看看深入理解JAVA虚拟机
        assertEquals(a, 0);
        assertEquals(b, 0);
        int c;
        // 这里虽然在运行时会赋值，但是编译器可能推断不出来，因此还是视为未赋值
        if (a == 0) {
            c = 0;
        }
//        assertEquals(c, 0);
    }
}
