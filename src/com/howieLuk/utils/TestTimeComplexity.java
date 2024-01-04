package com.howieLuk.utils;

/**
 * @author HowieLuk
 * @date 2024/1/5 1:32
 */
public class TestTimeComplexity {

    private static int count;

    private static void function() {
        count++;
    }

    private static void reset() {
        count = 0;
    }

    private static void print() {
        System.out.println("count:" + count);
    }

    public static void main(String[] args) {
        StopWatch watch = new StopWatch();
        int n = 1000; // 问题规模
        // 常数阶
        System.out.println("常数阶:");
        watch.start();
        function();
        watch.stop();
        print();reset();

        // 对数阶
        System.out.println("对数阶:");
        watch.restart();
        int logN = Double.valueOf(Math.log(n)).intValue();
        for (int i = 0; i < logN; i++) {
            function();
        }
        watch.stop();
        print();reset();

        // 线性阶
        System.out.println("线性阶");
        watch.restart();
        for (int i = 0; i < n; i++) {
            function();
        }
        watch.stop();
        print();reset();

        // 平方阶
        System.out.println("平方阶");
        watch.restart();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                function();
            }
        }
        watch.stop();
        print();reset();

        // 立方阶
        System.out.println("立方阶");
        watch.restart();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    function();
                }
            }
        }
        watch.stop();
        print();reset();

        // 指数阶
        System.out.println("指数阶");
        watch.restart();
        long pow = Double.valueOf(Math.pow(10, 10)).longValue();
        for (long i = 0; i < pow; i++) {
            function();
        }
        watch.stop();
        print();reset();

    }
}
