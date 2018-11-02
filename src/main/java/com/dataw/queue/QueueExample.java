package com.dataw.queue;

import com.dataw.concurrent.imooc.Sum;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author Kinyi_Chan
 * @since 2018-09-11
 */
public class QueueExample {

    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        String pop = ((LinkedList<String>) list).peek();
        System.out.println(pop);

//        String sentence = "43215432";
//        Set<String> mids = new Gson().fromJson(sentence, new TypeToken<Set<String>>() {
//        }.getType());
//        System.out.println(mids.size());

        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        PriorityBlockingQueue<Object> priorityBlockingQueue = new PriorityBlockingQueue<>();

        Sum sum = new Sum();
    }
}
