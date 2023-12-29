package com.howieLuk.queue;

public interface Queue<T> {

    T head();

    T pop();

    void push(T t);

    boolean isEmpty();

    int length();

    void showQueue();

}
