package type_check;

import org.junit.Test;

public class UpOrDownCastTest {

    class Animal {
        public void hello() {
            System.out.println("animal");
        }
    }

    class Dog extends Animal {
        @Override
        public void hello() {
            System.out.println("dog");
        }
    }

    @Test
    public void test() {
        Animal animal = new Dog(); // 向上转型
        Class<?> clazz = animal.getClass();
        if (animal instanceof Dog) {
            Dog dog = (Dog) animal; // 向下转型
        }
    }

    @Test
    public void test1() {
        Dog dog = new Dog();
        Animal animal = dog; // 向上转型
        animal.hello();
    }
}
