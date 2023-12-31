package com.howieLuk.linkedList;

import java.util.Objects;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2023/12/30 20:03
 * @Version 1.0
 **/
public class ListTest {
    public static void main(String[] args) {
        linkedListTest();
        doubleLinkedListTest();
    }

    private static void linkedListTest() {
        MyLinkedList<ClassA> list = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new ClassA(i));
        }
        list.insert(0, new ClassA(-1));
        list.printList();
        list.insert(list.size() / 2, new ClassA(list.size() / 2));
        list.printList();
        list.insert(1, new ClassA(1));
        list.insert(list.size() - 1, new ClassA(list.size() - 1));
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "\t");
        }
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            list.set(i, new ClassA(i));
        }
        list.printList();
        testRemove(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, new ClassA(i));
            System.out.println("index:" + list.indexOf(new ClassA(i)));
        }
        while (!list.isEmpty()) {
            list.remove(0);
        }
        for (int i = 0; i < 8; i++) {
            list.add(new ClassA(i));
        }
        testRemove1(list);
        list.reverse1();
        list.printList();
        list.reverse2().printList();
        while (!list.isEmpty()) {
            list.remove(0);
        }
        list.add(new ClassA(1));
        list.reverse2().printList();
        list.reverse1();list.printList();
        list.remove(0);
        list.reverse2().printList();
    }

    private static void doubleLinkedListTest() {
        MyList<ClassA> list = new DoubleLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new ClassA(i));
        }
        list.insert(0, new ClassA(-1));
        list.printList();
        list.insert(list.size() / 2, new ClassA(list.size() / 2));
        list.printList();
        list.insert(1, new ClassA(1));
        list.insert(list.size() - 1, new ClassA(list.size() - 1));
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "\t");
        }
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            list.set(i, new ClassA(i));
        }
        list.printList();
        testRemove(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, new ClassA(i));
            System.out.println("index:" + list.indexOf(new ClassA(i)));
        }
        while (!list.isEmpty()) {
            list.remove(0);
        }
        for (int i = 0; i < 8; i++) {
            list.add(new ClassA(i));
        }
        testRemove1(list);
        list.reverse().printList();
        while (!list.isEmpty()) {
            list.remove(0);
        }
        list.add(new ClassA(1));
        list.reverse().printList();
        list.remove(0);
        list.reverse().printList();
    }

    static <T> void testRemove(MyList<T> list) {
        System.out.println(list.remove(0));
        System.out.println(list.remove(1));
        System.out.println(list.remove(list.size() - 1));
        System.out.println(list.remove(list.size() - 2));
        System.out.println(list.remove(list.size() / 2));
        System.out.println(list.remove(list.size()));
    }

    static void testRemove1(MyList<ClassA> list) {
        System.out.println(list.remove(new ClassA(0)));
        System.out.println(list.remove(new ClassA(1)));
        System.out.println(list.remove(new ClassA(list.size() - 1)));
        System.out.println(list.remove(new ClassA(list.size() - 2)));
        System.out.println(list.remove(new ClassA(list.size() / 2)));
        System.out.println(list.remove(new ClassA(list.size())));
        list.printList();
    }

    static class ClassA {
        int a;

        public ClassA(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return String.valueOf(a);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ClassA classA = (ClassA) o;
            return a == classA.a;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a);
        }
    }
}
