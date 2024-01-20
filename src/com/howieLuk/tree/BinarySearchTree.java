package com.howieLuk.tree;

/**
 * @author HowieLuk
 * @date 2024/1/16 2:41
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private BinarySearchTreeNode<T> root;
    private int size;

    static class BinarySearchTreeNode<T extends Comparable<T>> {
        T t;
        BinarySearchTreeNode<T> left, right;

        public BinarySearchTreeNode(T t) {
            this(t, null, null);
        }

        public BinarySearchTreeNode(T t, BinarySearchTreeNode<T> left, BinarySearchTreeNode<T> right) {
            this.t = t;
            this.left = left;
            this.right = right;
        }

    }

}
