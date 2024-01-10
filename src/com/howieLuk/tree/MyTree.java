package com.howieLuk.tree;

/**
 * @Deacription 树
 * @Author HowieLuk
 * @Date 2024/1/10 19:20
 * @Version 1.0
 **/
public class MyTree<T extends Comparable<T>> {

    private class TreeNode {
        T e;
        TreeNode left, right;
        boolean deleted = false;

        void pre() {
            if (left != null) {
                pre();
            }
            System.out.print(e + "\t");
            if (right != null) {
                pre();
            }
        }

        void infix() {
            System.out.print(e + "\t");
            if (left != null) {
                infix();
            }
            if (right != null) {
                infix();
            }
        }

        void post() {
            if (left != null) {
                post();
            }
            if (right != null) {
                post();
            }
            System.out.print(e + "\t");
        }

        TreeNode getMinNode() {
            if (left != null) {
                return left.getMinNode();
            }
            return this;
        }

        TreeNode getMaxNode() {
            if (right != null) {
                return right.getMaxNode();
            }
            return this;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

        boolean isDeleted() {
            return deleted;
        }

    }

    private TreeNode delNode(TreeNode delNode, T val) {
        if (val.compareTo(delNode.e) == 0) {
            if (delNode.isLeaf()) {
                return null;
            }
            if (delNode.left != null) {
                TreeNode maxNode = delNode.left.getMaxNode();
                // swap
                swapVal(delNode, maxNode);
                delNode.left = delNode(delNode.left, val);
            } else {
                TreeNode minNode = delNode.right.getMinNode();
                swapVal(delNode, minNode);
                delNode.right = delNode(delNode.right, val);
            }
        } else if (val.compareTo(delNode.e) < 0) {
            delNode.left = delNode(delNode.left, val);
        } else {
            delNode.right = delNode(delNode.right, val);
        }
        return delNode;
    }

    private void swapVal(TreeNode node1, TreeNode node2) {
        T tmp = node1.e;
        node1.e = node2.e;
        node2.e = tmp;
    }

}
