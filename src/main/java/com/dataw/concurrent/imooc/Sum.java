package com.dataw.concurrent.imooc;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.annotation.NotThreadSafe;

import java.util.concurrent.*;

/**
 * @author Kinyi_Chan
 * @since 2018-09-05
 */
@Slf4j
@NotThreadSafe
public class Sum {
    private static int count;
    private static int total = 50;
    private static int clientTotal = 2;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(clientTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(total);
        for (int i = 0; i < total; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        log.info("pool state: {}", executorService.isTerminated());
        countDownLatch.await();
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            log.info("{}", System.currentTimeMillis());
        }
        log.info("pool state2: {}", executorService.isTerminated());
        log.info("count: " + count);
        System.out.println(executorService);

        SingletonExample instance = SingletonExample.getInstance();
//        ((ThreadPoolExecutor)executorService).get
    }

    private static void add() {
        count++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

}
