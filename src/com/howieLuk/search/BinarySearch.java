package com.howieLuk.search;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.linkedList.MyList;

/**
 * 折半查找
 * @author HowieLuk
 * @date 2024/1/8 7:37
 */
public class BinarySearch {

    public static <T extends Comparable<T>> int search(MyList<T> list, T e, int startInd, int endInd) {
        if (startInd > endInd) {
            return -1;
        }
        int midInd = ((endInd - startInd) >> 1) + startInd;
        if (list.get(midInd).equals(e)) {
            return midInd;
        } else if (list.get(midInd).compareTo(e) > 0) {
            // 右
            return search(list, e, midInd + 1, endInd);
        } else {
            // 左
            return search(list, e, startInd, endInd - 1);
        }
    }

    public static <T extends Comparable<T>> MyList<Integer> search(MyList<T> list, T e) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int i = search(list, e, 0, list.size());
        if (i != -1) {
            MyList<Integer> retList = new DoubleLinkedList<>();
            int midInd = i;
            // 往左扫描
            while (midInd > 0 && list.get(midInd).equals(e)) {
                retList.insert(0, midInd--);
            }
            // 往右扫描
            midInd = i + 1;
            while (midInd < list.size() && list.get(midInd).equals(e)) {
                retList.add(midInd++);
            }
            return retList;
        }
        return null;
    }

}
