package com.dataw.concurrent.imooc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 只执行一次的代码
 *
 * @author Kinyi_Chan
 * @since 2018-09-06
 */
public class Mukewang {

    private static AtomicBoolean isHappened;

    static {
        isHappened = new AtomicBoolean(false);
    }

    private static int clientTotal = 5000; //请求总数
    private static int threadTotal = 200;  //同时并发执行的线程数

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                }catch (Exception e) {
                    System.out.println("exception");
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("isHappened: " + isHappened.get());
    }

    private static void test() {
        if (isHappened.compareAndSet(false, true)) {
            System.out.println("execute");
        }
    }
}
