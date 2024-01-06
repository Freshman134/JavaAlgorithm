package com.howieLuk.sort;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.linkedList.MyList;
import com.howieLuk.utils.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author HowieLuk
 * @date 2024/1/6 1:08
 */
public class ShellSort {

    static <T extends Comparable<T>> void sort(MyList<T> list) {
        for (int step = list.size() >> 1; step > 0; step = step >> 1) {
            for (int i = 0; i < step; i++) {
                for (int j = step; j < list.size(); j+=step) {
                    int k = j;
                    T tmp = list.get(j);
                    T e;
                    for (; k > 0 && tmp.compareTo(e = list.get(k - step)) < 0; k-=step) {
                        list.set(k, e);
                    }
                    list.set(k, tmp);
                }
            }
        }
    }

    static <T extends Comparable<T>> void sort(List<T> list) {
        for (int step = list.size() >> 1; step > 0; step = step >> 1) {
            for (int i = 0; i < step; i++) {
                for (int j = step; j < list.size(); j+=step) {
                    int k = j;
                    T tmp = list.get(j);
                    T e;
                    for (; k > 0 && tmp.compareTo(e = list.get(k - step)) < 0; k-=step) {
                        list.set(k, e);
                    }
                    list.set(k, tmp);
                }
            }
        }
    }

    public static void main(String[] args) {
        MyList<Integer> list = new DoubleLinkedList<>();
        List<Integer> javaList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1111; i++) {
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
//        list.printList();
        watch.start();
        sort(javaList);
        watch.stop();
        for (int i = 0; i < list.size() - 1; i++) {
            Integer i1 = list.get(i);
            Integer i2 = list.get(i + 1);
            Integer i11 = javaList.get(i);
            Integer i22 = javaList.get(i + 1);
            if (i1.compareTo(i2) > 0 && i11.compareTo(i22) > 0) {
                throw new RuntimeException("排序错误:" + i);
            }
        }
    }

}
