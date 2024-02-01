package com.howieLuk.leetcode;

/**
 * @author HowieLuk
 * @date 2024/2/2 2:53
 */
public class CanCompleteCircuit {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int startInd = 0;
        int length = gas.length;
        int[] nextGasCost = new int[length];
        for (int i = length - 1; i > 0; i--) {
            nextGasCost[(i + 1) % length] = nextGasCost[i];
        }



        for (;startInd < length; startInd++) {

        }
        return -1;
    }
}
