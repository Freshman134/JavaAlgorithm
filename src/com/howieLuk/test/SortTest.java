package com.howieLuk.test;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.linkedList.MyList;
import com.howieLuk.sort.HeapSort;
import com.howieLuk.utils.StopWatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/9 20:25
 * @Version 1.0
 **/
public class SortTest {

    public static <T extends Comparable<T>> void test(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            T i1 = list.get(i);
            T i2 = list.get(i + 1);
//            Integer i11 = javaList.get(i);
//            Integer i22 = javaList.get(i + 1);
            if (i1.compareTo(i2) > 0) {
                throw new RuntimeException("排序错误:" + i);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new DoubleLinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 2000; i++) {
            int e = random.nextInt(2000);
            list.add(i, e);
        }
        StopWatch watch = new StopWatch();
        watch.start();
        HeapSort.sort(list);
        watch.stop();
        for (int i = 0; i < list.size() - 1; i++) {
            Integer i1 = list.get(i);
            Integer i2 = list.get(i + 1);
            if (i1.compareTo(i2) > 0) {
                throw new RuntimeException("排序错误:" + i);
            }
        }
    }
}
