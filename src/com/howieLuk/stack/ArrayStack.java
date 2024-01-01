package com.howieLuk.stack;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/1 11:09
 * @Version 1.0
 **/
public class ArrayStack<T> {
    Object[] arr;
    int size;
    int top = -1;

    public ArrayStack() {
        this(16);
    }

    public ArrayStack(int capacitySize) {
        arr = new Object[capacitySize];
    }

    public void push(T t) {
        try {
            arr[++top] = t;
        } catch (ArrayIndexOutOfBoundsException ignore) {
            Object[] newArr = new Object[arr.length << 1];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
            arr[top] = t;
        }
        size++;
    }

    public T pop() {
        try {
            size--;
            return (T)arr[top--];
        } catch (ArrayIndexOutOfBoundsException e) {
            size++; top++;
            return null;
        }
    }

    public T peak() {
        try {
            return (T)arr[top];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printList() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println("size:" + size);
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
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
        stack.peak();
    }
}
