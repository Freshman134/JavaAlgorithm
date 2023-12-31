package com.howieLuk.linkedList;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2023/12/31 18:52
 * @Version 1.0
 **/
public class Josephus<T> extends CycleLinkedList<T> {

    /**
     * 从第k个开始，报数第m个开始退出队列
     * @param k 从第k个开始数
     * @param step 报数步长
     * @return
     */
    public MyList<T> getJosephusList(int k, int step) {
        if (isEmpty() || k < 0 || k >= size || step <= 0) {
            return null;
        }
        MyList<T> list = new MyLinkedList<>();
        Node<T> node = head;
        Node<T> prior = null;
        for (int i = 0; i < k; i++) {
            prior = node;
            node = node.next;
        }
        while (!isEmpty()) {
            for (int m = 0; m < step; ) {
                prior = node;
                node = node.next;
                // 头结点不计算
                if (node != head) {
                    m++;
                }
            }
            prior.next = node.next;
            size--;
            list.add(node.t);
            node = prior;
        }
        return list;
    }

    public static void main(String[] args) {
        Josephus<Integer> josephus = new Josephus<>();
        for (int i = 1; i <= 25; i++) {
            josephus.add(i);
        }
        josephus.printList();
        MyList<Integer> ret = josephus.getJosephusList(9, 30);
        ret.printList();
        System.out.println(ret.get(ret.size() - 1));
    }
}
