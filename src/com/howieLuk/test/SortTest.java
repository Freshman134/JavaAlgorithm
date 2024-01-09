package com.howieLuk.test;

import java.util.List;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/9 20:25
 * @Version 1.0
 **/
public class SortTest {

    public static <T extends Comparable<T>> void test(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            T i1 = list.get(i);
            T i2 = list.get(i + 1);
//            Integer i11 = javaList.get(i);
//            Integer i22 = javaList.get(i + 1);
            if (i1.compareTo(i2) > 0) {
                throw new RuntimeException("排序错误:" + i);
            }
        }
    }


}
