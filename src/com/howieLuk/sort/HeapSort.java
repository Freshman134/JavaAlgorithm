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
        for (int i = heapEndInd; i > 0; i--) {
            int parentInd = parentNodeOf(i);
            if (list.get(i).compareTo(list.get(parentInd)) > 0) {
                T tmp = list.get(parentInd);
                list.set(parentInd, list.get(i));
                list.set(i, tmp);
                fixHeap(list, i, heapEndInd);
            }
        }
        while (heapEndInd > 0) {
            T max = list.get(0);
            T endEle = list.get(heapEndInd);
            list.set(0, endEle);
            list.set(heapEndInd--, max);
            fixHeap(list, 0, heapEndInd);
        }
    }

    private static <T extends Comparable<T>> void fixHeap(List<T> list, int n, int heapEndInd) {
        for (int i = n; i != -1;) {
            int leftInd = getLeft(i, heapEndInd);
            int rightInd = getRight(i, heapEndInd);
            if (leftInd != -1 && list.get(leftInd).compareTo(list.get(i)) > 0 &&
                    (rightInd == - 1 || list.get(leftInd).compareTo(list.get(rightInd)) > 0)) {
                swap(list, i, leftInd);
                i = leftInd;
            } else if (rightInd != -1 && list.get(rightInd).compareTo(list.get(i)) > 0) {
                swap(list, i, rightInd);
                i = rightInd;
            } else {
                break;
            }
        }
    }

    private static <T extends Comparable<T>> void swap(List<T> list, int a1, int a2) {
        T tmp = list.get(a1);
        list.set(a1, list.get(a2));
        list.set(a2, tmp);
    }

    private static int getLeft(int n, int heapEndInd) {
        int ret = 2*n + 1;
        return ret <= heapEndInd ? ret : -1;
    }

    private static int getRight(int n, int heapEndInd) {
        int ret = 2*n + 2;
        return ret <= heapEndInd ? ret : -1;
    }

    private static int parentNodeOf(int n) {
        return (n - 1) >> 1;
    }



}
