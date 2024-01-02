package com.howieLuk.queue;

public interface Queue<T> {

    T head();

    T pop();

    void push(T t);

    T peek(int i);

    T peek();

    boolean isEmpty();

    int length();

    void showQueue();

}
