package com.howieLuk.linkedList;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2023/12/31 14:24
 * @Version 1.0
 **/
public class DoubleLinkedList<T> implements MyList<T> {

    private int size;
    private Node<T> head = new Node<>(null, null, null);
    private Node<T> tail = new Node<>(null, null, null);

    public DoubleLinkedList() {
        head.rear = tail;
        tail.prior = head;
    }

    static class Node<T> {
        T t;
        Node<T> prior;
        Node<T> rear;
        public Node(T t, Node<T> prior, Node<T> rear) {
            this.t = t;
            this.prior = prior;
            this.rear = rear;
        }

        @Override
        public String toString() {
            return t.toString();
        }
    }

    @Override
    public void add(T t) {
        insert(size, t);
    }

    @Override
    public T get(int i) {
        if (i >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
        Node<T> node;
        if (i <= size / 2) {
            node = head; // 从head出发
            for (int j = 0; j <= i; j++) {
                node = node.rear;
            }
        } else {
            node = tail; // 从tail出发
            for (int j = size; j > i; j--) {
                node = node.prior;
            }
        }
        return node.t;
    }

    @Override
    public int indexOf(T t) {
        int i = 0;
        for (Node<T> node = head.rear; node != tail; node = node.rear) {
            if (node.t.equals(t)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public void insert(int i, T t) {
        if (i > size) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
        if (i <= size / 2) {
            // 从head出发
            Node<T> node = head;
            for (int j = 0; j < i; j++) {
                node = node.rear;
            }
            Node<T> newNode = new Node<>(t, node, node.rear);
            Node<T> rearNode = node.rear;
            node.rear = newNode;
            rearNode.prior = newNode;
        } else {
            // 从tail出发
            Node<T> node = tail;
            for (int j = size; j > i; j--) {
                node = node.prior;
            }
            Node<T> newNode = new Node<>(t, node.prior, node);
            Node<T> priorNode = node.prior;
            node.prior = newNode;
            priorNode.rear = newNode;
        }
        size++;
    }

    @Override
    public void set(int i, T t) {
        if (i >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
        Node<T> node;
        if (i <= size / 2) {
            // 从head出发
            node = head;
            for (int j = 0; j <= i; j++) {
                node = node.rear;
            }
        } else {
            // 从tail出发
            node = tail;
            for (int j = size; j > i; j--) {
                node = node.prior;
            }
        }
        node.t = t;
    }

    @Override
    public T remove(int i) {
        if (i >= size) {
            return null;
        }
        Node<T> node;
        if (i <= size / 2) {
            // 从head出发
            node = head;
            for (int j = 0; j <= i; j++) {
                node = node.rear;
            }
        } else {
            // 从tail出发
            node = tail;
            for (int j = size; j > i; j--) {
                node = node.prior;
            }
        }
        Node<T> priorNode = node.prior;
        Node<T> rearNode = node.rear;
        priorNode.rear = rearNode;
        rearNode.prior = priorNode;
        size--;
        return node.t;
    }

    @Override
    public T remove(T t) {
        for (Node<T> node = head.rear; node != tail; node = node.rear) {
            if (node.t.equals(t)) {
                Node<T> priorNode = node.prior;
                Node<T> rearNode = node.rear;
                priorNode.rear = rearNode;
                rearNode.prior = priorNode;
                size--;
                return node.t;
            }
        }
        return null;
    }

    @Override
    public void printList() {
        if (isEmpty()) {
            System.out.println("size:" + 0);
            return;
        }
        int size = 0;
        for (Node<T> node = head.rear; node != tail; node = node.rear) {
            System.out.print(node.t + "\t");
            size++;
        }
        System.out.println("size:" + size);
    }

    @Override
    public MyList<T> reverse() {
        DoubleLinkedList<T> ret = new DoubleLinkedList<>();
        for (Node<T> node = tail.prior; node != head; node = node.prior) {
            Node<T> priorNode = ret.tail.prior;
            Node<T> newNode = new Node<>(node.t, priorNode, ret.tail);
            priorNode.rear = newNode;
            ret.tail.prior = newNode;
            ret.size++;
        }
        return ret;
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
