package com.howieLuk.queue;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2023/12/29 18:33
 * @Version 1.0
 **/
public class ArrayQueue<T> implements Queue<T> {
    private Object [] arr;
    private int front;
    private int rear;
    private int queueSize;

    public ArrayQueue(int queueSize) {
        this.queueSize = queueSize;
        arr = new Object[queueSize];
        front = -1;
        rear = -1;
    }

    @Override
    public T head() {
        return isEmpty() ? null : (T) arr[front + 1];
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        T ret = (T) arr[front + 1];
//        for (int i = 0; i < rear; i++) {
//            arr[i] = arr[i + 1];
//        }
        if (rear >= 0) {
            System.arraycopy(arr, 1, arr, 0, rear);
        }
        rear--;
        return ret;
    }

    @Override
    public void push(T t) {
        if (rear == queueSize - 1) {
            throw new RuntimeException("队列已满");
        }
        arr[++rear] = t;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public int length() {
        return rear + 1;
    }

    @Override
    public void showQueue() {
        for (int i = 0; i <= rear; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }


}

