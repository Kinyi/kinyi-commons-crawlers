package com.dataw.practice.thread.unsafe;

/**
 * @author Kinyi_Chan
 * @since 2017-05-03
 */
public class Run {

    public static void main(String[] args) {
        PrintString printString = new PrintString();
        printString.printStringMethod();
        System.out.println("我要停止它! stopThread = " + Thread.currentThread().getName());
        printString.setContinuePrint(false);
    }
}
