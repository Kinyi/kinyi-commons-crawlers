package com.dataw.concurrent.imooc.syncContainer;

import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

/**
 * @author Kinyi_Chan
 * @since 2018-09-07
 */
@Slf4j
public class VectorExample {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        vector.remove(i);
                        log.info("{} - {}", Thread.currentThread().getName(), vector.size());
                    }
                }
            };
            Thread t2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        vector.get(i);
                        log.info("{} - {}", Thread.currentThread().getName(), vector.size());
                    }
                }
            };
            t1.start();
            t2.start();
        }
    }
}
