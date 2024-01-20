package com.howieLuk.leetcode;

/**
 * @author HowieLuk
 * @date 2024/1/21 2:23
 */
public class MajorityElement {

    static public int majorityElement(int[] nums) {
        int n = 1, majorityElem = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (majorityElem !=nums[i]) {
                n--;
            } else {
                n++;
            }
            if (n < 0) {
                majorityElem = nums[i];
                n = 1;
            }
        }
        return majorityElem;
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

}
