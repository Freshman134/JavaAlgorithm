package com.howieLuk.test;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.linkedList.MyList;
import com.howieLuk.search.BinarySearch;
import com.howieLuk.search.FibSearch;
import com.howieLuk.search.InterpolationSearch;
import com.howieLuk.sort.RadixSort;

import java.util.*;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/9 20:26
 * @Version 1.0
 **/
public class SearchTest {

    public static void main(String[] args) {
        Random random = new Random();
        MyList<Integer> list = new DoubleLinkedList<>();

        for (int i = 0; i < 2000; i++) {
            list.add(i);
        }
        int t1 = list.get(0);
        int tMax = list.get(list.size() - 1);
        InterpolationSearch search = new InterpolationSearch();
        if (search.search(list, t1).get(0) != t1 ||
                search.search(list, tMax).get(0) != list.size() - 1) {
            throw new RuntimeException("边界值错误");
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < 2000; i++) {
            Integer val = random.nextInt(2000);
            list.set(i, val);
            int count = Optional.ofNullable(countMap.get(val)).orElse(0);
            countMap.put(val, ++count);
        }
        RadixSort.sort(list);
        SortTest.test(list);
        for (int i = 0; i < 100; i++) {
            Integer searchInd = random.nextInt(2000);
            int searchVal = list.get(searchInd);
            List<Integer> retList = search.search(list, searchVal);
            if (retList.size() != countMap.get(searchVal)) {
                throw new RuntimeException("查找数量错误！" + searchVal + "有" + countMap.get(searchVal) +
                        "个，只查出" + retList.size() + "个");
            }
            for (int j = 0; j < retList.size(); j++) {
                int testInd = retList.get(j);
                int testVal = list.get(testInd);
                if (testVal != searchVal) {
                    throw new RuntimeException("查找错误！查找" + searchVal + "时应返回" + searchInd +
                            "\n实际返回索引" + testInd + "查找值为：" + searchVal + "重复数量为：" + countMap.get(searchVal));
                }
            }
        }


    }
}
