package com.howieLuk.tree;

/**
 * @author HowieLuk
 * @date 2024/1/11 1:00
 */
public class ArrayTree<T extends Comparable<T>> {

    private static final int INIT_SIZE = 1 << 4;

    Object[] arr = new Object[INIT_SIZE];
    int size = 0;

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

    public T put(T e) {
        int nodeInd = 0;
        while (true) {
            if (arr[nodeInd] == null) {
                arr[nodeInd] = e;
                size++;
                return null;
            }
            T node = (T)arr[nodeInd];
            if (node.equals(e)) {
                T ret = node;
                arr[nodeInd] = e;
                return ret;
            } else if (node.compareTo(e) > 0) {
                if (getLeft(nodeInd) == -1) {
                    expandArr();
                }
                nodeInd = getLeft(nodeInd);
            } else {
                if (getRight(nodeInd) == -1) {
                    expandArr();
                }
                nodeInd = getRight(nodeInd);
            }
        }
    }

    public T get(T val) {
        int nodeInd = 0;
        while (nodeInd != -1) {
            T node = (T)arr[nodeInd];
            if (node == null) {
                return null;
            }
            if (node.equals(val)) {
                return val;
            } else if (node.compareTo(val) > 0) {
                nodeInd = getLeft(nodeInd);
            } else {
                nodeInd = getRight(nodeInd);
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T remove(T val) {
        int nodeInd = 0;
        while (nodeInd != -1) {
            T node = (T)arr[nodeInd];
            if (node == null) {
                return null;
            }
            if (node.equals(val)) {
                if (isLeaf(nodeInd)) {
                    arr[nodeInd] = null;
                    size--;
                    return node;
                } else {
                    int prevInd;
                    if ((prevInd = getPrev(nodeInd)) != -1) {
                        swap(nodeInd, prevInd);
                        nodeInd = getLeft(nodeInd);
                    } else {
                        int rearInd = getRear(nodeInd);
                        swap(nodeInd, rearInd);
                        nodeInd = getRight(nodeInd);
                    }
                }
            } else if (node.compareTo(val) > 0) {
                nodeInd = getLeft(nodeInd);
            } else {
                nodeInd = getRight(nodeInd);
            }
        }
        return null;
    }

    private boolean isLeaf(int n) {
        return (getLeft(n) == -1 || arr[getLeft(n)] == null) &&
                (getRight(n) == -1 || arr[getRight(n)] == null);
    }

    private int getLeft(int n) {
        int ret = 2*n + 1;
        return ret < arr.length ? ret : -1;
    }

    private int getRight(int n) {
        int ret = 2*n + 2;
        return ret < arr.length ? ret : -1;
    }

    private int getPrev(int n) {
        int prevInd = arr[getLeft(n)] != null ? getLeft(n) : -1;
        while (prevInd != -1 && getRight(prevInd) != -1 && arr[getRight(prevInd)] != null) {
            prevInd = getRight(prevInd);
        }
        return prevInd;
    }

    private int getRear(int n) {
        int rearInd = arr[getRight(n)] != null ? getRight(n) : -1;
        while (rearInd != -1 && getLeft(rearInd) != -1 && arr[getLeft(rearInd)] != null) {
            rearInd = getLeft(rearInd);
        }
        return rearInd;
    }

    private int parentOf(int n) {
        return (n - 1) >> 1;
    }

    private void swap(int a1, int a2) {
        T tmp = (T) arr[a1];
        arr[a1] = arr[a2];
        arr[a2] = tmp;
    }

    private void expandArr() {
        Object [] tmpArr = new Object[arr.length << 1];
        System.arraycopy(arr, 0, tmpArr, 0, arr.length);
        arr = tmpArr;
    }

}
