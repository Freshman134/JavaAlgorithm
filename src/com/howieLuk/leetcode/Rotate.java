package com.howieLuk.leetcode;

import java.util.Arrays;

/**
 * @author HowieLuk
 * @date 2024/1/21 2:33
 */
public class Rotate {

    static public void rotate(int[] nums, int k) {
        int rotateElem, ind;
        int count = gcd(k, nums.length);
        for (int i = 0; i < count; i++) {
            ind = i; rotateElem = nums[i];
            do {
                int next = (ind + k) % nums.length;
                int tmp = nums[next];
                nums[next] = rotateElem;
                rotateElem = tmp;
                ind = next;
            } while (ind != i);
        }
    }

    static public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    public static void main(String[] args) {
        int [] nums = new int[]{-1, -100, 3, 99};
        rotate(nums, 2);
        System.out.println(Arrays.toString(nums));
    }
}
