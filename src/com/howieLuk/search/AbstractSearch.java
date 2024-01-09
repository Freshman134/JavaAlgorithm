package com.howieLuk.search;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.linkedList.MyList;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/9 22:41
 * @Version 1.0
 **/
public abstract class AbstractSearch<T extends Comparable<T>> {

    public MyList<Integer> search(MyList<T> list, T e) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int i = search(list, e, 0, list.size() - 1);
        if (i != -1) {
            MyList<Integer> retList = new DoubleLinkedList<>();
            int midInd = i;
            // 往左扫描
            while (midInd >= 0 && list.get(midInd).equals(e)) {
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

    public abstract int search(MyList<T> list, T e, int startInd, int endInd);

}
