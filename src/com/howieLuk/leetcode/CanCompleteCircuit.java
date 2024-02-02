package com.howieLuk.leetcode;

/**
 * @author HowieLuk
 * @date 2024/2/2 2:53
 */
public class CanCompleteCircuit {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        return other(gas, cost);
    }

    private static int my(int[] gas, int[] cost) {
        int startInd = 0;
        int length = gas.length;
        int fuelTank = 0;
        int arrived = 0, priInd;
        int count = 0;
        while(count < length) {
            if (fuelTank >= 0) {
                fuelTank = fuelTank + gas[arrived] - cost[arrived];
                arrived = (arrived + 1) % length;
            } else {
                priInd = (startInd + length - 1) % length;
                fuelTank = fuelTank + gas[priInd] - cost[priInd];
                startInd = priInd;
            }
            count++;
        }
        if (fuelTank < 0) {
            return -1;
        }
        return startInd;
    }

    private static int other(int[] gas, int[] cost) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int i = 0; i < gas.length; i++){
            sum = sum + gas[i] - cost[i];
            if(sum < min && sum < 0){
                min = sum;
                minIndex = i;
            }
        }
        if(sum < 0) return -1;
        return (minIndex + 1 )%gas.length;
    }

    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[]{5,5,1,3,4}, new int[]{8,1,7,1,1}));
    }

}
