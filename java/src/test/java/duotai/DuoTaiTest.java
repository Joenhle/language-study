package duotai;

import org.junit.Test;
import static org.junit.Assert.*;

public class DuoTaiTest {
    @Test
    public void xieBianTest() {

        class Animal {
            public Animal getAnimal() {
                return this;
            }
        }

        class Dog extends Animal {
            // 重写中的协变现象
            @Override
            public Dog getAnimal() {
                return this;
            }
        }

        Animal dog = new Dog();
        assertEquals(Dog.class, dog.getAnimal().getClass());
    }

}
