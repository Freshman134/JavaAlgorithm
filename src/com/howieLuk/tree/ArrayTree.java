package com.howieLuk.tree;

/**
 * @author HowieLuk
 * @date 2024/1/11 1:00
 */
public class ArrayTree<T> {

    private static final int INIT_SIZE = 1 << 4;

    Object[] arr = new Object[INIT_SIZE];

    public void pre(int n) {
        System.out.println(arr[0]);
        int left = getLeft(n);
        if (left != -1) {
            pre(left);
        }
        int right = getRight(n);
        if (right != -1) {
            pre(right);
        }
    }

    public void infix(int n) {
        int left = getLeft(n);
        if (left != -1) {
            infix(left);
        }
        System.out.println(arr[0]);
        int right = getRight(n);
        if (right != -1) {
            infix(right);
        }
    }

    public void post(int n) {
        int left = getLeft(n);
        if (left != -1) {
            post(left);
        }
        int right = getRight(n);
        if (right != -1) {
            post(right);
        }
        System.out.println(arr[0]);
    }

    private int getLeft(int n) {
        int ret = 2*n + 1;
        return ret < arr.length ? ret : -1;
    }

    private int getRight(int n) {
        int ret = 2*n + 2;
        return ret < arr.length ? ret : -1;
    }

}
