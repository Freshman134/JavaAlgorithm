package com.howieLuk.algorithm;

import java.util.*;

/**
 * @author HowieLuk
 * @date 2024/1/27 1:17
 */
public class RandomizedSet {
    List<Integer> container = new ArrayList<>();

    Map<Integer, Integer> valIndMap = new HashMap<>();

    static Random random = new Random();

    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if (valIndMap.containsKey(val)) {
            return false;
        }
        int ind = container.size();
        container.add(val);
        valIndMap.put(val, ind);
        return true;
    }

    public boolean remove(int val) {
        Integer ind = valIndMap.get(val);
        if (ind == null) {
            return false;
        }
        valIndMap.remove(val);
        if (ind < container.size() - 1) {
            Integer swapVal = container.remove(container.size() - 1);
            container.set(ind, swapVal);
            valIndMap.put(swapVal, ind);
        } else {
            container.remove(ind.intValue());
        }
        return true;
    }

    public int getRandom() {
        if (container.size() <= 1) {
            return container.size() == 0 ? 0 : container.get(0);
        }
        int randInd = random.nextInt(container.size());
        return container.get(randInd);
    }

    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
        set.insert(1);
        set.insert(10);
//        set.remove(0);
        set.insert(20);
        set.insert(30);
//        set.remove(1);
        for (int i = 0; i < 1000; i++) {
            int ret = set.getRandom();
            if (ret == 30) {
                System.out.println(ret);
            }
        }
    }
}
