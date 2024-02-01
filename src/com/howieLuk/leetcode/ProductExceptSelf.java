package com.howieLuk.leetcode;

/**
 * @author HowieLuk
 * @date 2024/1/31 2:41
 */
public class ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int hasZero = 0;
        int total = 1;
        int[] retArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                hasZero++;
                continue;
            }
            total *= nums[i];
        }
        if (hasZero > 1) {
            return retArr;
        }
        for (int i = 0; i < retArr.length; i++) {
            if (hasZero > 0 && nums[i] != 0) {
                retArr[i] = 0;
            } else if (hasZero == 0) {
                retArr[i] = total / nums[i];
            } else {
                retArr[i] = total;
            }
        }
        return retArr;
    }

}
