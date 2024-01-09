package com.howieLuk.utils;

import com.howieLuk.sort.MergeSort;
import com.howieLuk.sort.QuickSort;
import com.howieLuk.sort.RadixSort;

import java.util.List;

/**
 * @Deacription 排序测试用例
 * @Author HowieLuk
 * @Date 2024/1/9 19:58
 * @Version 1.0
 **/
public class SortUtils {

    public static void radixSort(List<Integer> list) {
        RadixSort.sort(list);
    }

    public static <T extends Comparable<T>> void sort(List<T> list) {
        QuickSort.sort(list);
    }

}
