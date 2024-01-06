package com.howieLuk.sort;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.linkedList.MyList;
import com.howieLuk.utils.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/6 19:14
 * @Version 1.0
 **/
public class MergeSort {

    public static  <T extends Comparable<T>> void sort(MyList<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        sort(0, list.size() - 1, list);
    }

    public static  <T extends Comparable<T>> void sort(int startInd, int endInd, MyList<T> list) {
        if (startInd >= endInd) {
            return;
        }
        int midInd = ((endInd - startInd) >> 1) + startInd;
        sort(startInd, midInd, list);
        sort(midInd + 1, endInd, list);
        Object[] tmpArr = new Object[midInd - startInd + 1];
        for (int i = 0; i < tmpArr.length; i++) {
            tmpArr[i] = list.get(startInd + i);
        }
        int left, right, i;
        left = 0;
        right = midInd + 1;
        i = startInd;
        T leftE, rightE;
        while (i < right && right <= endInd) {
            leftE = (T)tmpArr[left];
            rightE = list.get(right);
            if (rightE.compareTo(leftE) < 0) {
                list.set(i++, rightE);
                right++;
            } else {
                list.set(i++, leftE);
                left++;
            }
        }
        while (left < tmpArr.length) {
            list.set(i++, (T) tmpArr[left++]);
        }
    }

    public static void main(String[] args) {
        MyList<Integer> list = new DoubleLinkedList<>();
//        List<Integer> javaList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 4000; i++) {
            list.insert(i, random.nextInt(2000));
//            javaList.add(i, random.nextInt(2000));
        }
//        Shuffle.execute(list);
//        list.printList();
        StopWatch watch = new StopWatch();
        watch.start();
        sort(list);
        watch.stop();
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
