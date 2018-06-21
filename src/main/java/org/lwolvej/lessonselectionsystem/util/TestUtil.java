package org.lwolvej.lessonselectionsystem.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class TestUtil {

    public static void main(String... args) {
        List<Integer> now = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        now = now.stream().filter(obj -> obj % 2 != 0).collect(Collectors.toList());
        for (Integer n : now) {
            System.out.println(n);
        }

    }
}
