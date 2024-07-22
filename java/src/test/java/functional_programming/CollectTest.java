package functional_programming;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Person {
    public String name;
    public String phoneNumber;

    public Person(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

public class CollectTest {

    public static void main(String[] args) {
        Set<String> set = Stream.of("a", "b", "c", "d").collect(Collectors.toSet());
        System.out.println(set);

        String s = Stream.of("a", "b", "c", "d").collect(Collectors.joining(", "));
        System.out.println(s);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("hjh", "1"));
        personList.add(new Person("tqy", "2"));

        Map<String, String> map = personList.stream().collect(Collectors.toMap(a -> a.name, a -> a.phoneNumber));
        System.out.println(map);

        Map<String, String> map1 = personList.stream().collect(Collectors.toMap(Person::getName, Person::getPhoneNumber));
        System.out.println(map1);
    }
}
