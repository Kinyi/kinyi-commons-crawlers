package com.dataw.practice.thread;

/**
 * @author Kinyi_Chan
 * @since 2017-04-18
 */
public class Run {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Child(), "小孩" + i).start();
        }
    }
}
