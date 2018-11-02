package com.dataw.queue;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Kinyi_Chan
 * @since 2018-09-13
 */
public class PriorityQueue {

    public static void main(String[] args) {
        PriorityBlockingQueue<Object> queue = new PriorityBlockingQueue<>(10, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return o1.hashCode() - o2.hashCode();
            }
        });

        LoadingCache<String, Integer> cache = CacheBuilder.newBuilder()
                .build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(String key) throws Exception {
                return -1;
            }
        });

        List<String> list = new LinkedList<>();
        list.add("name");
        list.add("age");
        list.add("birthday");
        Map<String, Integer> map = list.stream().collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);

        int[] arr = new int[10];
        arr[1] = 10;
    }
}
