package com.howieLuk.sort;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.linkedList.MyList;
import com.howieLuk.utils.StopWatch;

import java.util.Random;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/5 21:44
 * @Version 1.0
 **/
public class InsertSort {

    public static <T extends Comparable<T>> void sort(MyList<T> list) {
        for (int i = 1; i < list.size(); i++) {
            T tmp = list.get(i);
            T e;
            int j = i;
            for (; j > 0 && tmp.compareTo(e = list.get(j - 1)) < 0; j--) {
                list.set(j, e);
            }
            list.set(j, tmp);
        }
    }

    public static void main(String[] args) {
        MyList<Integer> list = new DoubleLinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 2000; i++) {
            list.insert(i, random.nextInt(1000));
        }
//        Shuffle.execute(list);
//        list.printList();
        StopWatch watch = new StopWatch();
        watch.start();
        sort(list);
        watch.stop();
        for (int i = 0; i < list.size() - 1; i++) {
            Integer i1 = list.get(i);
            Integer i2 = list.get(i + 1);
            if (i1.compareTo(i2) > 0) {
                throw new RuntimeException("排序错误:" + i);
            }
        }
//        list.printList();
    }

}
