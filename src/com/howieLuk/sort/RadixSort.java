package com.howieLuk.sort;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.linkedList.MyList;
import com.howieLuk.utils.StopWatch;

import java.util.List;
import java.util.Random;

/**
 * @author HowieLuk
 * @date 2024/1/7 1:51
 */
public class RadixSort {

    public static void sort(MyList<Integer> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        MyList<MyList<Integer>> bucket = new DoubleLinkedList<>();
        for (int i = 0; i < 10; i++) {
            bucket.insert(0, new DoubleLinkedList<>());
        }
        Integer max = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            Integer e = list.get(i);
            if (e.compareTo(max) > 0) {
                max = e;
            }
        }
        int maxRadix = Double.valueOf(Math.log10(max)).intValue() + 1;
        int radix = 1;
        for (int i = 0; i < maxRadix; i++, radix*=10) {
            for (int j = 0; j < list.size(); j++) {
                Integer e = list.get(j);
                bucket.get((e / radix) % 10).add(e);
            }
            int listI = 0;
            for (int j = 0; j < bucket.size(); j++) {
                MyList<Integer> bList = bucket.get(j);
                for (int k = 0; k < bList.size(); k++) {
                    Integer e = bList.get(k);
                    list.set(listI++, e);
                }
                bucket.set(j, new DoubleLinkedList<>());
            }
        }
    }

    public static void sort(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        MyList<MyList<Integer>> bucket = new DoubleLinkedList<>();
        for (int i = 0; i < 10; i++) {
            bucket.insert(0, new DoubleLinkedList<>());
        }
        Integer max = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            Integer e = list.get(i);
            if (e.compareTo(max) > 0) {
                max = e;
            }
        }
        int maxRadix = Double.valueOf(Math.log10(max)).intValue() + 1;
        int radix = 1;
        for (int i = 0; i < maxRadix; i++, radix*=10) {
            for (int j = 0; j < list.size(); j++) {
                Integer e = list.get(j);
                bucket.get((e / radix) % 10).add(e);
            }
            int listI = 0;
            for (int j = 0; j < bucket.size(); j++) {
                MyList<Integer> bList = bucket.get(j);
                for (int k = 0; k < bList.size(); k++) {
                    Integer e = bList.get(k);
                    list.set(listI++, e);
                }
                bucket.set(j, new DoubleLinkedList<>());
            }
        }
    }

    public static void main(String[] args) {
        MyList<Integer> list = new DoubleLinkedList<>();
//        List<Integer> javaList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 2000; i++) {
            list.insert(i, random.nextInt(2000));
//            javaList.add(i, random.nextInt(2000));
        }
//        Shuffle.execute(list);
//        list.printList();
        StopWatch watch = new StopWatch();
        watch.start();
        sort(list);
        watch.stop();
//        list.printList();
//        watch.start();
//        sort(javaList);
//        watch.stop();
        for (int i = 0; i < list.size() - 1; i++) {
            Integer i1 = list.get(i);
            Integer i2 = list.get(i + 1);
//            Integer i11 = javaList.get(i);
//            Integer i22 = javaList.get(i + 1);
            if (i1.compareTo(i2) > 0) {
                throw new RuntimeException("排序错误:" + i);
            }
        }
    }

}
