package com.howieLuk.algorithm;

import java.util.Arrays;

/**
 * @Deacription 汉诺塔
 * @Author HowieLuk
 * @Date 2024/1/19 19:18
 * @Version 1.0
 **/
public class TowerOfHanoi {

    private static class Tower {
        int[] tower;
        int top;
        static final int TOWER_SIZE = 1 << 6;
        String name;
        Tower(int n, String name) {
            tower = new int[TOWER_SIZE];
            this.name = name;
            for (int i = 0; i < n; i++) {
                tower[i] = n - i;
            }
            top = n - 1;
        }

        void push(int i) {
            if (top >= 0 && (i > tower[top] || top >= tower.length)) {
                throw new RuntimeException("无法放置");
            }
            tower[++top] = i;
        }

        int pop() {
            if (top < 0) {
                throw new RuntimeException("塔以空");
            }
            return tower[top--];
        }
    }

    static Tower[] solution(Tower[] towers) {
        long step = move(towers[0], towers[1], towers[2], towers[0].top + 1, 0);
        System.out.println("移动了" + step + "步");
        return towers;
    }

    static long move(Tower srcTower, Tower midTower, Tower desTower, int n, long step) {
        if (n == 1) {
            int tmp = srcTower.pop();
            desTower.push(tmp);
//            System.out.println(tmp + " 从" + srcTower.name + "到" + desTower.name);
            return step + 1;
        }
        step = move(srcTower, desTower, midTower, n - 1, step);
        int tmp = srcTower.pop();
//        System.out.println(tmp + " 从" + srcTower.name + "到" + desTower.name);
        desTower.push(tmp);
        step++;
        return move(midTower, srcTower, desTower, n - 1, step);
    }

    public static void main(String[] args) {
        Tower[] towers = new Tower[]
                {new Tower(32, "塔1"), new Tower(0, "塔2"), new Tower(0, "塔3")};
        solution(towers);
    }

}
