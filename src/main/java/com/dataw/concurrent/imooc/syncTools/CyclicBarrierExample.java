package com.dataw.concurrent.imooc.syncTools;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kinyi_Chan
 * @since 2018-09-12
 */
@Slf4j
public class CyclicBarrierExample {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

    public static void main(String[] args) throws Exception {

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 8; i++) {
            Thread.sleep(1000);
            final int threadNum = i;
            pool.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });
        }
        pool.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        log.info("{} is ready", threadNum);
        cyclicBarrier.await();
        log.info("{} is continue", threadNum);
    }
}
