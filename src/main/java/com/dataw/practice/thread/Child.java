package com.dataw.practice.thread;

import lombok.extern.slf4j.Slf4j;
//import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Kinyi_Chan
 * @since 2017-04-12
 */
@Slf4j
public class Child implements Runnable {
//    private static final Logger LOG = Logger.getLogger(Child.class);
    private Transformation transformation;

    @Override
    public void run() {
        while (true) {
            try {
                transformation = Transformation.getInstance();
                System.out.println(Thread.currentThread().getName() + ": " + transformation);
                String type = transformation.getType();
                System.out.println(Thread.currentThread().getName() + ": 获取到的变形金刚是: " + type);
                String str = "擎天柱" + new Random().nextInt(10);
                System.out.println(Thread.currentThread().getName() + ": 将变形金刚设置成: " + str);
                transformation.setType(str);
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }

        }
    }
}
