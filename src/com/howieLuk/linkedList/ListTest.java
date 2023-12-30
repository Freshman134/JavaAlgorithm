package com.howieLuk.linkedList;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2023/12/30 20:03
 * @Version 1.0
 **/
public class ListTest {
    public static void main(String[] args) {
        linkedListTest();
    }

    private static void linkedListTest() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        list.insert(0, -1);
        list.printList();
        list.insert(list.size() / 2, list.size() / 2);
        list.printList();
        list.insert(1, 1);
        list.insert(list.size() - 1, list.size() - 1);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "\t");
        }
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            list.set(i, i);
        }
        list.printList();
        System.out.println(list.remove(0));
        System.out.println(list.remove(1));
        System.out.println(list.remove(list.size() - 1));
        System.out.println(list.remove(list.size() - 2));
        System.out.println(list.remove(list.size() / 2));
        System.out.println(list.remove(list.size()));
        list.printList();
        while (!list.isEmpty()) {
            list.remove(0);
        }
    }

}
