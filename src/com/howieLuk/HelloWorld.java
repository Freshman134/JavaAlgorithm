package com.howieLuk;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2023/12/29 14:13
 * @Version 1.0
 **/
public class HelloWorld {

    public static void main(String[] args) {
//        System.out.println("hello, world!");
//        int[] nums1 = new int[]{0};
//        int[] nums2 = new int[]{1};
//        Solution.merge(nums1, 0, nums2, 1);
        Map<Integer, Integer> testMap = new HashMap<>();
        IntStream.range(0, 10).forEach(i -> testMap.put(i, i));
        testMap.keySet().add(11);
    }
}

class Solution {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n - 1;
        m--; n--;
        while(i >= 0 && i > m && n >= 0 && m >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[i--] = nums1[m--];
                continue;
            } else {
                nums1[i--] = nums2[n--];
            }
        }
        while (n >= 0) {
            nums1[i--] = nums2[n--];
        }
    }
}
