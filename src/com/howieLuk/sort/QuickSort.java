package com.howieLuk.sort;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.linkedList.MyList;
import com.howieLuk.utils.StopWatch;

import java.util.Random;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/6 13:26
 * @Version 1.0
 **/
public class QuickSort {

    public static <T extends Comparable<T>> void sort(MyList<T> list) {
        if (list == null && !list.isEmpty()) {
            return;
        }
        sort(0, list.size() - 1, list);
    }

    private static <T extends Comparable<T>> void sort(int startInd, int endInd, MyList<T> list) {
        if (startInd >= endInd) {
            return;
        }
        int left, mid, right;
        left = mid = startInd;
        right = endInd;
        T midE = list.get(mid);
        while (left < right) {
            if (mid == left && midE.compareTo(list.get(right)) <= 0) {
                right--;
            } else {
                T rightE = list.get(right);
                list.set(left, rightE);
                list.set(right, midE);
                mid = right;
                left++;
            }
            if (mid == right && midE.compareTo(list.get(left)) >= 0) {
                left++;
            } else {
                T leftE = list.get(left);
                list.set(right, leftE);
                list.set(left, midE);
                mid = left;
                right--;
            }
        }
        sort(startInd, mid - 1, list);
        sort(mid + 1, endInd, list);
    }

    public static void main(String[] args) {
        MyList<Integer> list = new DoubleLinkedList<>();
//        List<Integer> javaList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 2000; i++) {
            int e = random.nextInt(2000);
            list.insert(i, e);
//            javaList.add(i, e);
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
