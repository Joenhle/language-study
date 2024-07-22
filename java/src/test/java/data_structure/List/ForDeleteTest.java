package data_structure.List;

import java.util.ArrayList;
import java.util.List;

public class ForDeleteTest {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5));

        for (Integer i : nums) {
            if (i % 2 == 0) {
                nums.remove(i);
            }
        }

        System.out.println(nums);
    }
}
