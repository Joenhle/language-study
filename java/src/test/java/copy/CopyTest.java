package copy;

import org.junit.Test;
import static org.junit.Assert.*;

class Address {
    public String location;

    public Address(String location) {
        this.location = location;
    }
}

class Person {
    public String name;
    public Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}

class ShadowCopyPerson extends Person implements Cloneable {
    public ShadowCopyPerson(String name, Address address) {
        super(name, address);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class DeepCopyPerson extends Person implements Cloneable {
    public DeepCopyPerson(String name, Address address) {
        super(name, address);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepCopyPerson p = (DeepCopyPerson) super.clone();
        p.address = new Address(p.address.location);
        return p;
    }
}


public class CopyTest {
    @Test
    public void testReferenceCopy() {
        ShadowCopyPerson p1 = new ShadowCopyPerson("hjh", new Address("3663"));
        ShadowCopyPerson p2 = p1;
        p2.name = "tqy";
        assertEquals("tqy", p1.name);
    }

    @Test
    public void testShadowCopy() throws CloneNotSupportedException {
        ShadowCopyPerson p1 = new ShadowCopyPerson("hjh", new Address("3663"));
        ShadowCopyPerson p2 = (ShadowCopyPerson) p1.clone();
        p2.name = "tqy";
        assertEquals("hjh", p1.name);

        p2.address.location = "1234";
        assertEquals("1234", p1.address.location);
    }

    @Test
    public void testDeepCopy() throws CloneNotSupportedException {
        DeepCopyPerson p1 = new DeepCopyPerson("hjh", new Address("3663"));
        DeepCopyPerson p2 = (DeepCopyPerson) p1.clone();
        p2.name = "tqy";
        p2.address.location = "1234";

        assertEquals("hjh", p1.name);
        assertEquals("3663", p1.address.location);
    }
}
