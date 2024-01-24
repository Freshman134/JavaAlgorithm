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

    public static int jump(int[] nums) {
        if (nums.length <= 1) {
            return 1;
        }
        return jump(nums, 0);
    }

    public static int jump(int[] nums, int start) {
        if (start == nums.length - 1) {
            return 0;
        }
        int nextStep = 0, count = 0, i = 0;
        while (i < nums.length - 1) {
            int jumpSize = nums[i], nextI = i;
            if (jumpSize == 0) {
                return -1;
            }
            if (nums[i] + i >= nums.length - 1) {
                return ++count;
            }
            for (int j = 1; j <= jumpSize; j++) {
                if (nums[i + j] + i + j > nextStep) {
                    nextStep = nums[i + j] + i + j;
                    nextI = i + j;
                }
            }
            i = nextI;
            nextStep = 0;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(jump(new int[]{1,2,1,1,1}));
    }

}
