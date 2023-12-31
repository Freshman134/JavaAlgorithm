package com.howieLuk.linkedList;

/**
 * @Deacription 面试题：查询单链表倒数第k个节点
 * @Author HowieLuk
 * @Date 2023/12/31 11:11
 * @Version 1.0
 **/
public class Test1<T> extends MyLinkedList<T> {

    /**
     * 面试题：查询单链表倒数第k个节点
     * @param i 倒数位置
     * @return
     */
    public T getReverse(int i) {
        // 思路一
        if (i > size) {
            throw new IndexOutOfBoundsException(i);
        }
        if (i == 0) {
            i = size; // 避免零值问题，倒数第0节点等价于倒数第k个节点
        }
        int ind = size - i;
        T ret1 = get(ind);
        // 思路二：双指针
        Node<T> fast = head;
        for (int j = 0; j < i; j++) {
            fast = fast.next;
        }
        Node<T> slow = head;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        T ret2 = slow.t;

        return ret2;
    }

    public static void main(String[] args) {
        Test1<Integer> test1 = new Test1<>();
        for (int i = 0; i < 5; i++) {
            test1.insert(i, i);
        }
        test1.printList();
        System.out.println(test1.getReverse(1));
        System.out.println(test1.getReverse(test1.size));
        System.out.println(test1.getReverse((test1.size) / 2));
        System.out.println(test1.getReverse(0));
    }
}
