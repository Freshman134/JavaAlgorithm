package com.howieLuk.sort;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.linkedList.MyLinkedList;
import com.howieLuk.linkedList.MyList;
import com.howieLuk.utils.Shuffle;
import com.howieLuk.utils.StopWatch;

import java.util.Random;

/**
 * @author HowieLuk
 * @date 2024/1/5 0:47
 */
public class BubbleSort {

    static <T extends Comparable<T>> void sort(MyList<T> list) {
        boolean noSwap = true;
        for (int i = list.size(); i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                T e = list.get(j);
                if (e.compareTo(list.get(j + 1)) > 0) {
                    noSwap = false;
                    T tmp = list.get(j + 1);
                    list.set(j + 1, e);
                    list.set(j, tmp);
                }
            }
            if (noSwap) {
                break;
            }
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
