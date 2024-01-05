package com.howieLuk.sort;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.linkedList.MyLinkedList;
import com.howieLuk.linkedList.MyList;
import com.howieLuk.utils.StopWatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/5 14:37
 * @Version 1.0
 **/
public class SelectSort {

    static <T extends Comparable<T>> void sort(MyList<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            T ei = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                T ej = list.get(j);
                if (ej.compareTo(ei) < 0) {
                    list.set(i, ej);
                    list.set(j, ei);
                    ei = ej;
                }
            }
        }
    }

    static <T extends Comparable<T>> void sort(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            T ei = list.get(i);
            int min = i;
            for (int j = i + 1; j < list.size(); j++) {
                T ej = list.get(j);
                if (ej.compareTo(ei) < 0) {
                    ei = ej;
                    min = j;
                }
            }
            if (min != i) {
                list.set(min, list.get(i));
                list.set(i, ei);
            }
        }
    }

    public static void main(String[] args) {
        MyList<Integer> list = new DoubleLinkedList<>();
        List<Integer> javaList = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 2000; i++) {
            int e = random.nextInt(2000);
            list.insert(i, e);
            javaList.add(i, e);
        }
//        Shuffle.execute(list);
//        list.printList();
        StopWatch watch = new StopWatch();
        watch.start();
        sort(list);
        watch.stop();
        watch.restart();
        sort(javaList);
        watch.stop();
//        list.printList();
        for (int i = 0; i < list.size() - 1; i++) {
            Integer i1 = list.get(i);
            Integer i2 = list.get(i + 1);
            if (i1.compareTo(i2) > 0) {
                throw new RuntimeException("排序错误:" + i);
            }
        }
    }
}
