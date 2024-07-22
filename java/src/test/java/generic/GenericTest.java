package generic;

import org.junit.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;



public class GenericTest {

    class Box<T> {

        private T content;

        public T getContent() {
            return content;
        }

        public void setContent(T content) {
            this.content = content;
        }

        // 如何在函数里动态判断泛型的运行时类型，通过对象的getClass()方法与type.class判断是否equals就行
        public static <T> T add(T a, T b) {
            Class<T> clazz = (Class<T>) a.getClass();
            if (clazz.equals(Integer.class)) {
                return clazz.cast((Integer) a + (Integer) b);
            } else if (clazz.equals(String.class)) {
                return clazz.cast((String) a + (String) b);
            }
            return null;
        }

        // 泛型类中的静态变量和静态方法不能直接使用泛型类的泛型类型，因为泛型类中的泛型参数的实例化是在定义对象的时候指定的，而静态变量和静态方法不需要使用对象来调用。对象都没有创建，如何确定这个泛型参数是何种类型，所以当然是错误的。
        //但是要注意区分下面的一种情况：
//        private static T error_t;
//        public static T error_method() {
//            return null;
//        }

    }

    public static <T> T genericMethod1(T a, T b) {
        return b;
    }

    @Test
    public void classGeneric() {
        Box<String> stringBox = new Box<>();

        stringBox.setContent("123");
        System.out.println(stringBox.getContent());

        Box<Integer> integerBox = new Box<>();
        integerBox.setContent(100);
        System.out.println(integerBox.getContent());
    }

    @Test
    public void staticMethodGeneric() {
        assertEquals(Optional.of(3), Optional.ofNullable(Box.add(1, 2)));
        assertEquals(Optional.of("12"), Optional.ofNullable(Box.add("1", "2")));
        // 这里虽然是用的泛型对象调用的静态泛型方法，实际的类型还是由实参决定的。
        Box<String> stringBox = new Box<>();
        assertEquals(Optional.of(3), Optional.ofNullable(stringBox.add(1, 2)));
    }

    @Test
    public void testTypeEquals() {
        Box<Integer> integerBox = new Box<>();
        Box<String> stringBox = new Box<>();
        Class<?> integerClazz = integerBox.getClass();
        Class<?> stringClazz = stringBox.getClass();
        // 它们两个的类型一样是因为类型擦除导致的
        assertEquals(integerClazz, stringClazz);
    }

    @Test
    public void typeErase() throws Exception {
        // 这个的原始类型是Object
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
//        list.add("123"); compile error
        // 这样没问题是因为泛型的实现是类型擦除，在编译为字节码后类型里面就不会带泛型，它的原始类型就是Object，通过反射编译期间也不会检测出来，运行期间传入的“123”也是Object的子类，所以没问题。
        list.getClass().getMethod("add", Object.class).invoke(list, "123");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        // 这个类的原始类型就是Comparable，取第一个泛型限定类型
//        public class Pair<T extends Comparable> {}
    }

    @Test
    public void genericMethodWithoutGenericType() {
        // 不指定泛型函数的泛型类型，默认取实参类型中的同一父类的最小等级
        int a = genericMethod1(1, 2); //两个参数都是Integer，所以T也是Integer
        Number b = genericMethod1(1, 1.2); // 一个是Integer，另一个是Float，取同一父类的最小级Number
        Object c = genericMethod1(1, "123"); // 一个是Integer，一个是String，取同一父类的最小级Object

        // 指定泛型函数的泛型类型，如果实参的类型不一致，直接编译错误
//        int d = GenericTest.<Integer>genericMethod1(1, 1.2);
        
    }

    @Test
    public void test1() {
        ArrayList<Integer> list = new ArrayList<>();
//        list.add("123"); 在编译期间进行类型检查，会失败
    }

    @Test
    public void test2() {
        ArrayList<String> a = new ArrayList<>();
        Class<?> clazz = a.getClass();
        System.out.println(123);



    }
}
