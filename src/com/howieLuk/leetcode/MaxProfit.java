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



}
