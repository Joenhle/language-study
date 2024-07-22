package data_structure.map;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapTest {
    @Test
    public void test1() {
        // hashmap的循环方式
        Map<Integer, String> map = new HashMap<>();
        String values[] = new String[]{"0", "1", "2", "3", "4"};
        for (int i = 0; i < 5; i++) {
            map.put(i, String.valueOf(i));
        }


        // entrySet iterator
        int i = 0;
        Iterator<Map.Entry<Integer, String>> iterator1 = map.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry<Integer, String> next = iterator1.next();
            assertEquals(i, (int)next.getKey());
            assertEquals(values[i++], next.getValue());
        }

        // keySet iterator
        i = 0;
        Iterator<Integer> iterator2 = map.keySet().iterator();
        while (iterator2.hasNext()) {
            Integer next = iterator2.next();
            assertEquals(i, (int)next);
            assertEquals(values[i++], map.get(next));
        }

        // foreach entrySet
        i = 0;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            assertEquals(i, (int)entry.getKey());
            assertEquals(values[i++], entry.getValue());
        }

        // foreach keyset
        i = 0;
        for (Integer key : map.keySet()) {
            assertEquals(i, (int) key);
            assertEquals(values[i++], map.get(key));
        }

        // lambda
        map.forEach((key, value) -> {
            assertEquals(String.valueOf(key), value);
        });

        // single thread stream
        map.entrySet().stream().forEach(a -> {
            assertEquals(String.valueOf(a.getKey()), a.getValue());
        });

        // parallel thread stream
        map.entrySet().parallelStream().forEach(a -> {
            assertEquals(String.valueOf(a.getKey()), a.getValue());
        });
    }
}
