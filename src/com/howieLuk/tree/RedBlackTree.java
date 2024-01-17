package com.howieLuk.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Deacription 红黑树
 * @Author HowieLuk
 * @Date 2024/1/16 20:04
 * @Version 1.0
 **/
public class RedBlackTree<T extends Comparable<T>> {

    TreeNode<T> root;
    int count;

    static class TreeNode<T extends Comparable<T>> {
        T t;
        TreeNode<T> left, right, parent;
        boolean red = false;

        TreeNode(T t) {
            this.t = t;
            setNIL(this);
        }

        TreeNode(T t, TreeNode<T> parent) {
            this.t = t;
            this.parent = parent;
        }

        void addNodeLeft(T t) {
            left.t = t;
            left.red = true;
            setNIL(left);
        }

        void  addNodeRight(T t) {
            right.t = t;
            right.red = true;
            setNIL(right);
        }

        boolean isLeaf() {
            return leftIsNIL() && rightIsNIL();
        }

        boolean leftIsNIL() {
            return left == null || left.t == null;
        }

        boolean rightIsNIL() {
            return right == null || right.t == null;
        }

        boolean isRed() {
            return red;
        }

        TreeNode<T> getMaxNode() {
            return right == null ? this : right.getMaxNode();
        }

        TreeNode<T> getMinNode() {
            return left == null ? this : left.getMinNode();
        }

        void setNIL(TreeNode<T> parent) {
            parent.left = new TreeNode<>(null, parent);
            parent.right = new TreeNode<>(null, parent);
        }

        TreeNode<T> getNIL(TreeNode<T> parent) {
            return new TreeNode<>(null, parent);
        }

        public void setParent(TreeNode<T> parent) {
            if (parent != null) {
                Integer val = (Integer) parent.t;
                if (val == 432) {
                    System.out.println();
                }
            } else {
                System.out.println();
            }
            this.parent = parent;
        }

        @Override
        public String toString() {
            String color = red ? "red" : "black";
            return "TreeNode{" +
                    "t=" + t +
                    ',' + color + '}';
        }
    }

    private void deleteNode(TreeNode<T> parent, TreeNode<T> deleteNode, T t) {
        if (deleteNode.isLeaf()) {
            boolean deleteNodeIsRed = deleteNode.isRed();
            if (parent.left == deleteNode) {
                parent.left = parent.getNIL(parent);
            } else {
                parent.right = parent.getNIL(parent);
            }
            if (!deleteNodeIsRed) {
                deleteFixNode(parent, deleteNode);
            }
        } else {
            if (!deleteNode.leftIsNIL()) {
                TreeNode<T> prev = deleteNode.left.getMaxNode();
                swapT(prev, deleteNode);
                deleteNode = searchNode(deleteNode.left, t);
                deleteNode(deleteNode.parent, deleteNode, t);
            } else {
                TreeNode<T> rear = deleteNode.right.getMinNode();
                swapT(rear, deleteNode);
                deleteNode = searchNode(deleteNode.right, t);
                deleteNode(deleteNode.parent, deleteNode, t);
            }
        }
    }

    TreeNode<T> searchNode(TreeNode<T> node, T t) {
        TreeNode<T> retNode = null;
        if (node.t.compareTo(t) == 0) {
            retNode = node;
        } else if (node.t.compareTo(t) > 0 && !node.leftIsNIL()) {
            retNode = searchNode(node.left, t);
        } else if (!node.rightIsNIL()) {
            retNode = searchNode(node.right, t);
        }
        return retNode;
    }

    private void testCycle(List<T> list, TreeNode<T> node) {
        if (list.contains(node.t)) {
            System.out.println();
        }
        list.add(node.t);
        if (node.isLeaf()) {
            return;
        }
        if (!node.leftIsNIL()) {
            testCycle(list, node.left);
        }
        if (!node.rightIsNIL()) {
            testCycle(list, node.right);
        }
    }

    private void addNode(TreeNode<T> node, T t, int n) {
        if (node.t.compareTo(t) == 0) {
            throw new UnsupportedOperationException("已存在相同元素：" + t);
        } else if (node.t.compareTo(t) > 0) {
            if (node.leftIsNIL()) {
                node.addNodeLeft(t);
            } else {
                addNode(node.left, t, n + 1);
            }
        } else {
            if (node.rightIsNIL()) {
                node.addNodeRight(t);
            } else {
                addNode(node.right, t, n + 1);
            }
        }
        fixNode(node);
    }

    private void swapT(TreeNode<T> node1, TreeNode<T> node2) {
        T tmp = node1.t;
        node1.t = node2.t;
        node2.t = tmp;
    }

    private void fixNode(TreeNode<T> node) {
        if (node.isRed() && (node.left.isRed() || node.right.isRed())) {
            TreeNode<T> parent = node.parent;
            if (parent == null) {
                if (node.left.isRed()) {
                    root = rightRotate(node);
                } else {
                    root = leftRotate(node);
                }
                root.red = false;
                return;
            }
            boolean isLeft = parent.left == node;
            if (isLeft ? parent.right.isRed() : parent.left.isRed()) {
                flipColor(parent);
                return;
            }
            TreeNode<T> newNode = null;
            boolean nodeInParentLeft = parent.left == node;
            if (node.left.isRed() && node.left.left.isRed()) { // LL
                newNode = rightRotate(node);
                newNode.red = false;
                parent.left = newNode;
            } else if (node.right.isRed() && node.right.right.isRed()) { // RR
                newNode = leftRotate(node);
                newNode.red = false;
                parent.right = newNode;
            }
            if (newNode != null) {
                if (nodeInParentLeft) {
                    parent.left = newNode;
                } else {
                    parent.right = newNode;
                }
                return;
            }
            if ((isLeft && node.left.isRed()) || (!isLeft && node.right.isRed())) { // LL、RR
                parent.red = true;
            } else if (isLeft && node.right.isRed()) { // LR
                parent.left = leftRotate(node);
                parent.red = true;
            } else { // RL
                parent.right = rightRotate(node);
                parent.red = true;
            }
        }
        testCycle(new ArrayList<>(), root);
    }

    private void deleteFixNode(TreeNode<T> fixNode, TreeNode<T> deletedNode) {
        boolean broIsLeft = fixNode.left == deletedNode;
        TreeNode<T> broNode = broIsLeft ? fixNode.right : fixNode.left;
        TreeNode<T> newNode = null, parent = fixNode.parent;
        if (parent != null) {
            if (!fixNode.isRed() && !broNode.isRed()) {
                broNode.red = true;
                deleteFixNode(parent, fixNode);
                return;
            }
            if (fixNode.isRed()) {
                if (!broNode.left.isRed() && !broNode.right.isRed()) {
                    broNode.red = true;
                    fixNode.red = false;
                    return;
                } else if (broIsLeft) {
                    newNode = rotate(fixNode, broNode, broNode.left.isRed() ? broNode.left : broNode.right);
                } else {
                    newNode = rotate(fixNode, broNode, broNode.right.isRed() ? broNode.right : broNode.left);
                }
            } else if (broNode.isRed()) {
                if (fixNode.left == deletedNode) {
                    newNode = rightRotate(fixNode);
                    newNode.right.red = true;
                } else {
                    newNode = leftRotate(fixNode);
                    newNode.left.red = true;
                }
            }
        }
        if (newNode != null) { // not root
            if (parent.left == fixNode) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        } else if (!broNode.left.isRed() && !broNode.right.isRed()) {
            broNode.red = true;
        } else if (broNode.left.isRed()) {
            root = rotate(fixNode, broNode, broNode.left);
        } else {
            root = rotate(fixNode, broNode, broNode.right);
        }
    }

    private TreeNode<T> leftRotate(TreeNode<T> node) {
        TreeNode<T> retNode = node.right;
        node.right = retNode.left;
        retNode.setParent(node.parent);
        node.setParent(retNode);
        node.right.parent = node;
        retNode.left = node;
        return retNode;
    }

    private TreeNode<T> rightRotate(TreeNode<T> node) {
        TreeNode<T> retNode = node.left;
        node.left = retNode.right;
        retNode.setParent(node.parent);
        node.setParent(retNode);
        node.left.parent = node;
        retNode.right = node;
        return retNode;
    }

    private void flipColor(TreeNode<T> node) {
        node.red = true;
        node.left.red = false;
        node.right.red = false;
    }

    private TreeNode<T> rotate(TreeNode<T> fixNode, TreeNode<T> broNode, TreeNode<T> redNode) {
        TreeNode<T> newNode = null;
        if (fixNode.left == broNode) {
            if (broNode.left == redNode) {
                // LL
                newNode = rightRotate(fixNode);
                flipColor(newNode);
            } else {
                // LR
                fixNode.left = leftRotate(broNode);
                broNode.red = true;
                newNode = rightRotate(fixNode);
                newNode.red = false;
            }
        } else {
            if (broNode.right == redNode) {
                // RR
                newNode = leftRotate(fixNode);
                flipColor(newNode);
            } else {
                // RL
                fixNode.right = rightRotate(broNode);
                broNode.red = true;
                newNode = leftRotate(fixNode);
                newNode.red = false;
            }
        }
        return newNode;
    }

    public boolean isEmpty() {
        return count <= 0;
    }

    public T remove(T t) {
        T removeT = null;
        if (!isEmpty()) {
            TreeNode<T> deleteNode = searchNode(root, t);
            if (deleteNode != null) {
                if (deleteNode.parent == null) {
                    if (root.isLeaf()) {
                        removeT = root.t;
                        root = null;
                    } else {
                        TreeNode<T> swapNode = !root.leftIsNIL() ? root.left.getMaxNode() : root.right.getMinNode();
                        swapT(swapNode, root);
                        deleteNode(swapNode.parent, swapNode, t);
                    }
                } else {
                    deleteNode(deleteNode.parent, deleteNode, t);
                    removeT = deleteNode.t;
                }
                count--;
            }
        }
        return removeT;
    }

    public void add(T t) {
        if (isEmpty()) {
            root = new TreeNode<>(t);
        } else {
            addNode(root, t, 0);
            root.red = false;
        }
        count++;
    }

    private void infix(TreeNode<T> node) {
        if (node.isLeaf()) {
            System.out.print(node.t + "\t");
            return;
        }
        if (!node.leftIsNIL()) {
            infix(node.left);
        }
        System.out.print(node.t + "\t");
        if (!node.rightIsNIL()) {
            infix(node.right);
        }
    }



    public static void main(String[] args) {
        //                                                  1
        Integer[] arr = new Integer[]{973, 6, 432, 939, 17, 484, 58, 245};
        List<Integer> list = new ArrayList<>();
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            Integer e = arr[i];
            if (e == 484) {
                System.out.println();
            }
            if (!list.contains(e)) {
                list.add(e);
                tree.add(e);
            }
            System.out.println("i:" + e);
            TestUtil.constTest(tree, list);
        }
        tree.infix(tree.root);
        System.out.println();
        System.out.println("size:" + tree.count);
        System.out.println(tree.isEmpty());
        TestUtil.testTree(tree);
        System.out.println();
    }

}

class TestUtil {
    static int n = 0;

    static void reset() {
        n = 0;
    }

    static void testTree(RedBlackTree<Integer> tree) {
        RedBlackTree.TreeNode<Integer> node = tree.root;
        while (!node.leftIsNIL()) {
            if (!node.isRed()) {
                n++;
            }
            node = node.left;
        }
        infixTest(tree.root, 0);
        reset();
    }

    static void infixTest(RedBlackTree.TreeNode<Integer> node, int testN) {
        if (!node.isRed()) {
            testN++;
        } else if (node.left.isRed() || node.right.isRed()) {
            System.out.print(node.t + "\t");
            throw new RuntimeException("相同红节点");
        }
        if (node.isLeaf()) {
            System.out.print(node.t + "\t");
            if (n != testN) {
                throw new RuntimeException("黑节点路径不一致");
            }
            return;
        }
        if (!node.leftIsNIL()) {
            infixTest(node.left, testN);
        }
        System.out.print(node.t + "\t");
        if (!node.rightIsNIL()) {
            infixTest(node.right, testN);
        }
    }

    static void constTest(RedBlackTree<Integer> tree, List<Integer> list) {
        for (Integer integer : list) {
            RedBlackTree.TreeNode<Integer> node = tree.searchNode(tree.root, integer);
            System.out.print(node.t + "\t");
        }
    }

}
