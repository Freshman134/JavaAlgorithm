package com.howieLuk.linkedList;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2023/12/31 18:53
 * @Version 1.0
 **/
public class CycleLinkedList<T> implements MyList<T> {

    int size;
    Node<T> head = new Node<>(null, null);

    static class Node<T> {
        T t;
        Node<T> next;
        public Node(T t, Node<T> next) {
            this.t = t;
            this.next = next;
        }
    }

    public CycleLinkedList() {
        head.next = head;
    }

    @Override
    public void add(T t) {
        insert(size, t);
    }

    @Override
    public T get(int i) {
        return null;
    }

    @Override
    public int indexOf(T t) {
        return 0;
    }

    @Override
    public void insert(int i, T t) {
        if (i > size) {
            throw new IndexOutOfBoundsException(i);
        }
        Node<T> node = head;
        for (int j = 0; j < i; j++) {
            node = node.next;
        }
        node.next = new Node<>(t, node.next);
        size++;
    }

    @Override
    public void set(int i, T t) {

    }

    @Override
    public T remove(int i) {
        System.out.println("remove(int i)");

        return null;
    }

    @Override
    public T remove(T t) {
        System.out.println("remove(T t)");
        Node<T> priorNode = head;
        for (Node<T> delNode = head.next; delNode != head; delNode = delNode.next) {
            if (delNode.t.equals(t)) {
                priorNode.next = delNode.next;
                size--;
                return delNode.t;
            }
            priorNode = delNode;
        }
        return null;
    }

    @Override
    public void printList() {
        if (isEmpty()) {
            return;
        }
        for (Node<T> node = head.next; node != head; node = node.next) {
            System.out.print(node.t + "\t");
        }
        System.out.println("size:" + size);
    }

    @Override
    public MyList<T> reverse() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
