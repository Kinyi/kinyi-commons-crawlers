package com.dataw;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kinyi_Chan
 * @since 2018-09-05
 */
//@Slf4j
public class OOM {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(new Task(i));
        }
        executorService.shutdown();
    }

    static class Task implements Runnable {
        int num;

        public Task(int num) {
            this.num = num;
        }

        public void run() {
            try {
                Thread.sleep(1);
                System.out.println(Thread.currentThread().getName() + ": " + num);
            } catch (InterruptedException e) {
//                log.error(e.getMessage(), e);
                System.out.println(e.getMessage());
            }
        }
    }
}
