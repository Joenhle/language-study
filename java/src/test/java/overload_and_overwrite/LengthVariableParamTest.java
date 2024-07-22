package overload_and_overwrite;

import org.junit.Test;
import static org.junit.Assert.*;

public class LengthVariableParamTest {

    public static int printVariables(String... args) {
        return 1;
    }

    public static int printVariables(String arg1, String arg2) {
        return 2;
    }

    @Test
    public void test() {
        // 如果可变参数函数和定参数的函数重载时都满足某个函数调用，则选择那个定参数的函数，更匹配
        assertEquals(2, printVariables("1", "2"));
        assertEquals(1, printVariables("1", "2", "3"));
    }
}
