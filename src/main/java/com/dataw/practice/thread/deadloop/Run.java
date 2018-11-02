package com.dataw.practice.thread.deadloop;

/**
 * @author Kinyi_Chan
 * @since 2017-06-07
 */
public class Run {
    public static void main(String[] args) {
        PrintString printString = new PrintString();
        new Thread(printString).start();
        System.out.println("stop it! stopThread = " + Thread.currentThread().getName());
        printString.setContinuePrint(false);
    }
}
