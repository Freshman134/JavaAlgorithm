package com.howieLuk.queue;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2023/12/29 19:39
 * @Version 1.0
 **/
public class CycleArrayQueue<T> implements Queue<T> {
    private Object [] arr;
    private int front;
    private int rear;
    private int queueSize;

    public CycleArrayQueue(int queueSize) {
        this.queueSize = queueSize + 1;
        arr = new Object[queueSize + 1];
    }

    @Override
    public T head() {
        return isEmpty() ? null : (T) arr[front];
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        T ret = (T) arr[front];
        front = (front + 1) % queueSize;
        return ret;
    }

    @Override
    public void push(T t) {
        if ((rear + 1) % queueSize == front) {
            throw new RuntimeException("队列已满");
        }
        arr[rear] = t;
        rear = (rear + 1) % queueSize;
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    public int length() {
        return (rear - front + queueSize) % queueSize;
    }

    @Override
    public void showQueue() {
        int length = length();
        for (int i = 0; i < length; i++) {
            System.out.print(arr[(front + i) % queueSize] + "\t");
        }
        System.out.println();
    }

}
