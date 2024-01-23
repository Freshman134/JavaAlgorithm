package com.howieLuk.leetcode;

/**
 * @author HowieLuk
 * @date 2024/1/24 0:31
 */
public class CanJump {

    static private int[] canJumpArr;

    public static boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        canJumpArr = new int[nums.length];
        return canJump(nums, 0);
    }

    private static boolean canJump(int[] nums, int start) {
        if (start == nums.length - 1) {
            return true;
        } else if (canJumpArr[start] == -1) {
            return false;
        }
        int i = nums[start] + start;
        if (i > nums.length) {
            return true;
        }
        for (; i > start; i--) {
            if (canJump(nums, i)) {
                return true;
            }
        }
        canJumpArr[start] = -1;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2,0}));
    }

}
