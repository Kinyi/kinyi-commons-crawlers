package com.dataw.concurrent.imooc.syncContainer;

import java.util.*;

/**
 * @author Kinyi_Chan
 * @since 2018-09-07
 */
public class VectorExample2 {

    // ConcurrentModificationException
    private static void test1(List<Integer> list) {
        for (Integer integer : list) {
            if (integer.equals(3)) {
                list.remove(integer);
            }
        }
    }

    private static void test2(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next.equals(3)) {
                list.remove(next);
            }
        }
    }

    private static void test3(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(3)) {
                list.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        test1(list);
    }
}
