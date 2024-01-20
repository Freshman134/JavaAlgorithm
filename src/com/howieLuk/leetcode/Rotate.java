package com.howieLuk.leetcode;

import java.util.Arrays;

/**
 * @author HowieLuk
 * @date 2024/1/21 2:33
 */
public class Rotate {

    static public void rotate(int[] nums, int k) {
        int rotateElem = nums[0], ind = 0;
        for (int i = 0; i < nums.length; i++) {
            int next = (ind + k) % nums.length;
            int tmp = nums[next];
            nums[next] = rotateElem;

            ind = next;
        }
    }

    public static void main(String[] args) {
        int [] nums = new int[]{-1, -100, 3, 99};
        rotate(nums, 2);
        System.out.println(Arrays.toString(nums));
    }
}
