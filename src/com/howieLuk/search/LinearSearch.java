package com.howieLuk.search;

import com.howieLuk.linkedList.MyList;

/**
 * 线性查找
 * @author HowieLuk
 * @date 2024/1/8 7:34
 */
public class LinearSearch {
    /**
     * 返回对应值索引
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> int search(MyList<T> list, T e) {
        for (int i = 0; i < list.size(); i++) {
            if (e.equals(list.get(i))) {
                return i;
            }
        }
        return -1;
    }
}
