package com.howieLuk.sort;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.linkedList.MyList;

import java.util.List;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/11 20:14
 * @Version 1.0
 **/
public class HeapSort {

    public static <T extends Comparable<T>> void sort(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int heapEndInd = list.size() - 1;
        while (heapEndInd > 0) {
            for (int i = heapEndInd; i > 0; i--) {
                int parentInd = parentNodeOf(i);
                if (list.get(i).compareTo(list.get(parentInd)) > 0) {
                    T tmp = list.get(parentInd);
                    list.set(parentInd, list.get(i));
                    list.set(i, tmp);
                }
            }
            T max = list.get(0);
            T endEle = list.get(heapEndInd);
            list.set(0, endEle);
            list.set(heapEndInd--, max);
        }
    }

    private static int parentNodeOf(int n) {
        return (n - 1) >> 1;
    }



}
