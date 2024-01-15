package com.howieLuk.tree;

/**
 * 前序线索化二叉树
 * @author HowieLuk
 * @date 2024/1/11 2:50
 */
public class ThreadedMyTree<T extends Comparable<T>> extends MyTree<T> {

    ThreadedTreeNode<T> root;

    private static class ThreadedTreeNode<T extends Comparable<T>> extends TreeNode<T> {
        boolean leftIsThreaded = false;
        boolean rightIsThreaded = false;

        @Override
        void pre() {
            System.out.print(e + "\t");
            if (left != null) {
                left.pre();
            }
            if (right != null) {
                right.pre();
            }
        }

        @Override
        boolean isLeaf() {
            return super.isLeaf() || (leftIsThreaded && rightIsThreaded);
        }

        @Override
        TreeNode<T> getMinNode() {
            if (left != null && !leftIsThreaded) {
                return left.getMinNode();
            }
            return this;
        }

        @Override
        TreeNode<T> getMaxNode() {
            if (right != null && !rightIsThreaded) {
                return right.getMaxNode();
            }
            return this;
        }

        @Override
        T get(T t) {
            return super.get(t);
        }

        @Override
        T put(T t) {
            if (e.equals(t)) {
                return e;
            } else if (e.compareTo(t) > 0) {
                if (left == null || leftIsThreaded) {
                    ThreadedTreeNode<T> rearNode = right != null && !rightIsThreaded ?
                            (ThreadedTreeNode<T>) right.getMinNode() : (ThreadedTreeNode<T>) right;
                    left = getTreeNode(this, rearNode, t);
                    leftIsThreaded = false;
                    return null;
                } else {
                    return left.put(t);
                }
            } else {
                if (right == null || rightIsThreaded) {
                    ThreadedTreeNode<T> preNode = left != null && !leftIsThreaded ?
                            (ThreadedTreeNode<T>) left.getMaxNode() : (ThreadedTreeNode<T>) left;
//                    right = getTreeNode();
                    rightIsThreaded = false;
                    return null;
                } else {
                    return right.put(t);
                }
            }
        }

        private ThreadedTreeNode<T> getTreeNode(ThreadedTreeNode<T> preNode, ThreadedTreeNode<T> rearNode, T e) {
            ThreadedTreeNode<T> retNode = new ThreadedTreeNode<>();
            retNode.e = e;
            if (preNode != null) {
                retNode.leftIsThreaded = true;
                retNode.left = preNode;
                if (preNode.rightIsThreaded) {
                    preNode.right = retNode;
                }
            }
            if (rearNode != null) {
                retNode.rightIsThreaded = true;
                retNode.right = rearNode;
                if (rearNode.leftIsThreaded) {
                    rearNode.left = retNode;
                }
            }
            return rearNode;
        }
    }

    public void pre() {
        if (root == null) {
            return;
        }
        ThreadedTreeNode<T> node = root;
        while (node != null) {
            System.out.print(node.e + "\t");
            if (!node.leftIsThreaded && node.left != null) {
                node = (ThreadedTreeNode<T>) node.left;
            } else {
                node = (ThreadedTreeNode<T>) node.right;
            }
        }
    }
}
