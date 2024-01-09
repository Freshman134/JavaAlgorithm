package com.howieLuk.search;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.linkedList.MyList;

import java.util.ArrayList;
import java.util.List;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/9 20:20
 * @Version 1.0
 **/
public class FibSearch extends AbstractSearch<Integer> {

    private static List<Integer> fib = new ArrayList<>();

    static {
        fib.add(0, 0);
        fib.add(1, 1);
    }

    private static int fib(int i) {
        if (i < 0) {
            throw new RuntimeException("值不能为负数:" + i);
        }
        if (fib.size() > i) {
            return fib.get(i);
        }
        fib.add(i, fib(i - 1) + fib(i - 2));
        return fib.get(i);
    }

    public int search(MyList<Integer> list, Integer e, int startInd, int endInd) {
        if (list == null || list.isEmpty()) {
            return -1;
        }
        int k = 0;
        while (endInd + 1 > fib(k)) {
            k++;
        }
        List<Integer> temp = new DoubleLinkedList<>();
        for (int i = 0; i < fib(k) - 1; i++) {
            temp.add(i, list.get(Math.min(i, list.size() - 1)));
        }

        while (startInd <= endInd) {
            int mid = fib(k - 1) - 1 + startInd;
            if (temp.get(mid).compareTo(e) == 0) {
                return Math.min(mid, list.size() - 1);
            }
            if (temp.get(mid).compareTo(e) > 0) {
                endInd = mid - 1;
                k-=1;
            } else {
                startInd = mid + 1;
                k-=2;
            }
        }
        return -1;
    }
}
