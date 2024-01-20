package com.howieLuk.leetcode;

/**
 * @author HowieLuk
 * @date 2024/1/20 2:39
 */
public class RemoveElement {

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,2,3,0,4,2};
        Solution.removeElement(nums, 2);
    }
}

class Solution {
    static public int removeElement(int[] nums, int val) {
        int i = 0;
        while (i < nums.length && nums[i] != val) {
            i++;
        }
        int j = i + 1;
        while (j < nums.length) {
            if (nums[j] != val) {
                nums[i++] = nums[j++];
            } else {
                j++;
            }
        }
        return i;
    }
}
