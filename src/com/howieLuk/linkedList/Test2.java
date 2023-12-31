package com.howieLuk.linkedList;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2023/12/31 14:07
 * @Version 1.0
 **/
public class Test2<T> extends MyLinkedList<T> {

    public void printReverse() {
        if (isEmpty()) {
            return;
        }
        Object[] stack = new Object[size];
        int top = -1;
        for (Node<T> node = head.next; node != null; node = node.next) {
            stack[++top] = node;
        }
        for (int i = top; i > -1 ; i--) {
            Node<T> e =(Node<T>) stack[i];
            System.out.print(e.t + "\t");
        }
        System.out.println("size:" + size);
    }

    public static void main(String[] args) {
        Test2<Integer> test2 = new Test2<>();
        for (int i = 0; i < 5; i++) {
            test2.add(i);
        }
        test2.printList();
        test2.printReverse();
    }
}
