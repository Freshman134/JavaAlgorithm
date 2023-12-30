package com.howieLuk.linkedList;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2023/12/30 19:29
 * @Version 1.0
 **/
public class MyLinkedList<T> {

    Node<T> head = new Node<>(null, null);
    int size;

    private class Node<T> {
        T t;
        Node<T> next;
        public Node(T t, Node<T> next) {
            this.t = t;
            this.next = next;
        }
    }

    public void add(T t) {
        insert(size, t);
    }

    public T get(int i) {
        if (i > size) {
            throw new IndexOutOfBoundsException(i);
        }
        Node<T> node = head;
        for (int j = 0; j <= i; j++) {
            try {
                node = node.next;
            } catch (NullPointerException e) {
                throw new RuntimeException("location:" + j);
            }
        }
        return node.t;
    }

    public void insert(int i, T t) {
        if (i > size) {
            throw new IndexOutOfBoundsException(i);
        }
        Node<T> node = head;
        for (int j = 0; j < i; j++) {
            try {
                node = node.next;
            } catch (NullPointerException e) {
                throw new RuntimeException("location:" + j);
            }
        }
        node.next = new Node<>(t, node.next);
        size++;
    }

    public void set(int i, T t) {
        if (i >= size) {
            throw new IndexOutOfBoundsException(i);
        }
        Node<T> node = head;
        for (int j = 0; j <= i; j++) {
            try {
                node = node.next;
            } catch (NullPointerException e) {
                throw new RuntimeException("location:" + j);
            }
        }
        node.t = t;
    }

    public T remove(int i) {
        if (i >= size) {
            return null;
        }
        Node<T> prior = head;
        for (int j = 0; j < i; j++) {
            try {
                prior = prior.next;
            } catch (NullPointerException e) {
                throw new RuntimeException("location:" + j);
            }
        }
        Node<T> removeNode = prior.next;
        prior.next = removeNode.next;
        size--;
        return removeNode.t;
    }

    public void printList() {
        if (isEmpty()) {
            return;
        }
        for (Node<T> node = head.next; node != null; node = node.next) {
            System.out.print(node.t + "\t");
        }
        System.out.println();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
