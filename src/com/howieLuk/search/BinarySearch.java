package com.howieLuk.search;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.linkedList.MyList;

/**
 * 折半查找
 * @author HowieLuk
 * @date 2024/1/8 7:37
 */
public class BinarySearch<T extends Comparable<T>> extends AbstractSearch<T> {

    public int search(MyList<T> list, T e, int startInd, int endInd) {
        if (startInd > endInd) {
            return -1;
        }
        int midInd = ((endInd - startInd) >> 1) + startInd;
        if (list.get(midInd).equals(e)) {
            return midInd;
        } else if (list.get(midInd).compareTo(e) < 0) {
            // 右
            return search(list, e, midInd + 1, endInd);
        } else {
            // 左
            return search(list, e, startInd, endInd - 1);
        }
    }
}
