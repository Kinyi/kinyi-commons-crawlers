package com.dataw.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Kinyi_Chan
 * @since 2018-09-12
 */
@Slf4j
public class EggPlateExample {

    private BlockingQueue<Object> queue = new ArrayBlockingQueue<>(5);

    private void putEgg(Object egg) {
        try {
            queue.put(egg);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        log.info("put egg");
    }

    private Object getEgg() {
        Object egg = null;
        try {
            egg = queue.take();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        log.info("get egg");
        return egg;
    }

    static class PutThread extends Thread {
        private EggPlateExample instance;
//        private Object egg = new Object();

        public PutThread(EggPlateExample instance) {
            this.instance = instance;
        }

        @Override
        public void run() {
            instance.putEgg(new Object());
        }
    }

    static class GetThread extends Thread {
        private EggPlateExample instance;

        public GetThread(EggPlateExample instance) {
            this.instance = instance;
        }

        @Override
        public void run() {
            instance.getEgg();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        EggPlateExample instance = new EggPlateExample();
        for (int i = 0; i < 10; i++) {
            new GetThread(instance).start();
            Thread.sleep(1000);
            new PutThread(instance).start();
        }
    }
}
