package functional_programming;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class methodRefTest {
    @Test
    public void test() {

        // 特定对象的方法引用
        List<String> list = new ArrayList<>();
        Consumer<String> consumer = list::add;
        consumer.accept("123");
        assertEquals(1, list.size());
        assertEquals("123", list.getFirst());

        // 类的方法引用
        list.clear();
        BiConsumer<List<String>, String> biConsumer = List::add;
        biConsumer.accept(list, "123");
        assertEquals(1, list.size());
        assertEquals("123", list.getFirst());

        // BiFunction接受两个参数，返回一个值，BiConsumer接受两个参数，不返回值
        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> a + b;
        BiConsumer<Integer, Integer> biConsumer1 = (a, b) -> System.out.println(a + b);

        // (a, b) -> c这种可以赋值给BiConsumer吗？lambda表达式不可以，对象的方法引用可以
//        BiConsumer<Integer, Integer> biConsumer2 = (a, b) -> a+b;
    }
}
