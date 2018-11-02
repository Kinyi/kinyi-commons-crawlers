package com.dataw.queue;

/**
 * @author Kinyi_Chan
 * @since 2018-10-08
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
