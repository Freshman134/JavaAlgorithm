package com.howieLuk.utils;

/**
 * @author HowieLuk
 * @date 2024/1/5 1:26
 */
public class StopWatch {

    private long start;

    private long cost;

    public long getStart() {
        return start;
    }

    public void start() {
        System.out.println("计时开始");
        start = System.currentTimeMillis();
    }

    public long pause() {
        cost = System.currentTimeMillis() - start + cost;
        System.out.println("暂停");
        return cost;
    }

    public void restart() {
        start();
    }

    public long stop() {
        cost = System.currentTimeMillis() - start + cost;
        System.out.println("计时结束，耗时：" + cost + "毫秒");
        long ret = cost;
        cost = 0;
        return ret;
    }

    public void clear() {
        start = cost = 0;
    }

}
