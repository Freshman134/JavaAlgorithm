package com.howieLuk.leetcode;

/**
 * @author HowieLuk
 * @date 2024/1/26 1:12
 */
public class HIndex {

    public static int hIndex(int[] citations) {
        int min = citations[0], max = citations[0];
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] < min) {
                min = citations[i];
            }
            if (citations[i] > max) {
                max = citations[i];
            }
        }
        int [] countArr = new int[max - min + 1];
        for (int i = 0; i < citations.length; i++) {
            countArr[citations[i] - min] += 1;
        }
        if (countArr.length > 1) {
            for (int i = countArr.length - 1; i > 0; i--) {
                countArr[i - 1] = countArr[i] + countArr[i - 1];
            }
            for (int h = countArr.length - 1; h >= 0; h--) {
                if (countArr[h] >= h + min) {
                    return h + min;
                }
            }
            return Math.min(min, citations.length);
        } else {
            return Math.min(min, countArr[0]);
        }
    }

    public static void main(String[] args) {
        System.out.println(hIndex(new int[]{3,0,6,1,5}));
    }

}
