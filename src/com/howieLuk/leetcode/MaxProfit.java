package com.howieLuk.leetcode;

/**
 * @author HowieLuk
 * @date 2024/1/23 23:53
 */
public class MaxProfit {

    static public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int min = prices[0], profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if ((prices[i] - min) > profit) {
                profit = prices[i] - min;
            }
        }
        return profit;
    }

    static public int maxProfit2(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int min = prices[0], max = 0, profit = 0;
        for (int i = 1; i < prices.length; i++) {
            while (i < prices.length && prices[i] <= min) {
                min = prices[i++];
            }
            while (i < prices.length && prices[i] >= max) {
                max = prices[i++];
            }
            if (max > min) {
                profit += max - min;
                if (i < prices.length - 1) {
                    min = prices[i];
                    max = 0;
                }
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit2(new int[]{8, 6, 4, 3, 3, 2, 3, 5, 8, 3, 8, 2, 6}));
    }

}
