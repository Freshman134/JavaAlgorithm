package com.howieLuk.queue;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2023/12/29 20:29
 * @Version 1.0
 **/
public class QueueTest {

    public static void main(String[] args) {
        int queueSize = 5;
        Queue<TestA> aQueue = new CycleArrayQueue<>(queueSize);
        for (int i = 0; i < queueSize; i++) {
            TestA a = new TestA();
            a.a = i;
            aQueue.push(a);
        }
        System.out.println("队列长度" + aQueue.length());
        while (!aQueue.isEmpty()) {
            System.out.print(aQueue.head().a + "\t");
            aQueue.pop();
        }
        System.out.println();
        System.out.println(aQueue.head());
        for (int i = 0; i < queueSize; i++) {
            TestA a = new TestA();
            a.a = i;
            aQueue.push(a);
        }
        aQueue.showQueue();
        while (!aQueue.isEmpty()) {
            System.out.print(aQueue.head().a + "\t");
            aQueue.pop();
        }
    }

    static class TestA {
        int a;

        @Override
        public String toString() {
            return String.valueOf(a);
        }
    }
}
