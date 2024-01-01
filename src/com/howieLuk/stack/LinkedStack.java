package com.howieLuk.stack;

import com.howieLuk.linkedList.MyLinkedList;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/1 11:32
 * @Version 1.0
 **/
public class LinkedStack<T> extends MyLinkedList<T> {

    public void push(T t) {
        insert(0, t);
    }

    public T pop() {
        return remove(0);
    }

    public T peak() {
        try {
            return get(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        int i = 0;
        while (i < 20) {
            stack.push(i);
            i++;
        }
        System.out.println(stack.pop());
        System.out.println(stack.peak());
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        System.out.println(stack.pop());
    }

}
