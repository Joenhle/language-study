package reflect;

import org.junit.Test;
import org.junit.Test.*;
import org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {
    @Test
    public void test1() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        class A {
            static {
                System.out.println("A initialized");
            }
            public static int value = 10;
        }
        // 直接调用类名.class触发类加载，但是不会进行类初始化，通过类加载器加载class也不会初始化
        Class<?> tmp = A.class;
        System.out.println("1");

        // Class.forName触发类加载且会初始化，
        Class<?> clazz = Class.forName("reflect.ReflectTest$1A");
        System.out.println("2");
        Field field = clazz.getField("value");
        int value = field.getInt(null);
        System.out.println(value);
    }

    @Test
    public void test2() throws Exception {

        // 通过反射创建对象实例
        Class<?> clazz = Class.forName("reflect.TargetObject");
        Constructor<?> constructor = clazz.getConstructor(String.class);
        TargetObject to = (TargetObject) constructor.newInstance("hjh");

        // 获取TargetObject中的所有方法
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(method.getName());
        }

        // 获取指定方法并调用
        Method publicMethod = clazz.getMethod("publicMethod", String.class);
        publicMethod.invoke(to, "hjh");

        // 获取指定参数并修改，private需要先修改访问控制权限
        Field field = clazz.getField("name");
        field.setAccessible(true);
        // 方法和field对象在调用成员函数的时候需要传入反射对象的实例是因为class对象和反射的实例对象是隔离的。
        field.set(to, "tqy");
    }
}
