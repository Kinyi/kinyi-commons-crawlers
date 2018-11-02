package com.dataw.practice.thread;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Kinyi_Chan
 * @since 2018-09-05
 */
public class CachedThreadPool {

    public static void main(String[] args) {
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(0, 100, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new BasicThreadFactory.Builder().build());
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(0, 100, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new BasicThreadFactory.Builder().build());
        for (int i = 0; i < 1000; i++) {
            poolExecutor.submit(new Task(i));
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
        poolExecutor.shutdown();
    }
}

class Task implements Runnable {
    private int num;

    public Task(int num) {
        this.num = num;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + ": " + num);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
