package com.howieLuk.linkedList;

import java.util.LinkedList;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2023/12/30 19:29
 * @Version 1.0
 **/
public class MyLinkedList<T> extends MyAbstractList<T> implements MyList<T> {

    Node<T> head = new Node<>(null, null);
    int size;

    static class Node<T> {
        T t;
        Node<T> next;
        public Node(T t, Node<T> next) {
            this.t = t;
            this.next = next;
        }

        @Override
        public String toString() {
            return t.toString();
        }
    }

    public boolean add(T t) {
        insert(size, t);
        return true;
    }

    public T get(int i) {
        if (i > size) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
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

    @Override
    public int indexOf(Object obj) {
        T t = (T)obj;
        int i = 0;
        for (Node<T> node = head.next; node != null; node = node.next) {
            if (node.t.equals(t)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void insert(int i, T t) {
        if (i > size) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
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

    public T set(int i, T t) {
        if (i >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
        Node<T> node = head;
        for (int j = 0; j <= i; j++) {
            try {
                node = node.next;
            } catch (NullPointerException e) {
                throw new RuntimeException("location:" + j);
            }
        }
        T ret = node.t;
        node.t = t;
        return ret;
    }

    public T remove(int i) {
        if (i >= size || i < 0) {
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

    @Override
    public boolean remove(Object obj) {
        T t = (T)obj;
        Node<T> priorNode = head;
        for (Node<T> node = head.next; node != null; node = node.next) {
            if (node.t.equals(t)) {
                priorNode.next = node.next;
                size--;
                return true;
            }
            priorNode = node;
        }
        return false;
    }

    public void printList() {
        if (isEmpty()) {
            System.out.println("size:" + 0);
            return;
        }
        int size = 0;
        for (Node<T> node = head.next; node != null; node = node.next) {
            System.out.print(node.t + "\t");
            size++;
        }
        System.out.println("size:" + size);
    }

    @Override
    public MyList<T> reverse() {
        return reverse2();
    }

    /**
     * 思路一
     * 反转链表，会改变原链表排序
     */
    public void reverse1() {
        if (isEmpty()) {
            return;
        }
        Node<T> priorReverseNode = head.next;
        Node<T> reverseNode = priorReverseNode.next;
        priorReverseNode.next = null; // 第一个节点翻转
        while (reverseNode != null) {
            Node<T> tmp = reverseNode.next;
            reverseNode.next = priorReverseNode;
            priorReverseNode = reverseNode;
            reverseNode = tmp;
        }
        head.next = priorReverseNode;
    }

    /**
     * 反转链表，不会改变原链表排序
     * 思路二，不会改变原内部排序
     * @return 新reverse链表
     */
    public MyLinkedList<T> reverse2() {
        MyLinkedList<T> ret = new MyLinkedList<>(); // 定义新链表
        for (Node<T> e = head.next; e != null; e = e.next) {
            ret.head.next = new Node<>(e.t, ret.head.next); // 头插法
            ret.size++;
        }
        return ret;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


}
