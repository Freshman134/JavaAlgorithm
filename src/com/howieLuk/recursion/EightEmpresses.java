package com.howieLuk.recursion;

/**
 * @Deacription 八皇后问题
 * @Author HowieLuk
 * @Date 2024/1/3 21:55
 * @Version 1.0
 **/
public class EightEmpresses {

    private static int[] empresses = new int[9];

    public static int getAnswer() {
        empress(1);
        return empresses[0];
    }

    private static void empress(int e) {
        if (e == empresses.length) {
            empresses[0]+=1;
            return;
        }
        for (int i = 1; i < empresses.length; i++) {
            empresses[e] = i;
            if (noConflict(e)) {
                empress(e + 1);
            }
        }
    }

    private static boolean noConflict(int e) {
        for (int i = e - 1; i > 0; i--) {
            if (i != e && (empresses[i] == empresses[e] ||
                    Math.abs(empresses[i] - empresses[e]) == Math.abs(i - e))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(getAnswer());
    }

}
