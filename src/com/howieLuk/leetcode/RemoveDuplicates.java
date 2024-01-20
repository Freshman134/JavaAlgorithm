package com.howieLuk.leetcode;

/**
 * @author HowieLuk
 * @date 2024/1/21 1:52
 */
public class RemoveDuplicates {

    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
            j++;
        }
        return i + 1;
    }

    public static int removeDuplicates2(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int i = 0, j = 1, n = 1;
        while (j < nums.length) {
            if (nums[i] == nums[j] && n < 2) {
                nums[++i] = nums[j++];
                n++;
                continue;
            }
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
                n = 1;
            }
            j++;
        }
        return i + 1;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates2(new int[]{0,0,1,1,1,1,2,3,3}));
    }

}
