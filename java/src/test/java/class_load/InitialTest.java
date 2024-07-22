package class_load;
import org.junit.Test;

public class InitialTest {

    class A {
        static int a = 10;
        static {
            System.out.println(123);
        }
    }

    @Test
    public void demo1() {
    }
}
