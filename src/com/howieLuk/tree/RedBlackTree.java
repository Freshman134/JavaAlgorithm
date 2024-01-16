package com.howieLuk.tree;

/**
 * @Deacription 红黑树
 * @Author HowieLuk
 * @Date 2024/1/16 20:04
 * @Version 1.0
 **/
public class RedBlackTree<T extends Comparable<T>> {

    private TreeNode<T> root;
    private int count;

    private static class TreeNode<T extends Comparable<T>> {
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

        void setNIL(TreeNode<T> node) {
            node.left = new TreeNode<>(null, node);
            node.right = new TreeNode<>(null, node);
        }

    }

    private void deleteNode(TreeNode<T> parent, TreeNode<T> deleteNode, T t) {
        if (deleteNode.isLeaf()) {
            if (parent.left == deleteNode) {
                parent.left = null;
            } else {
                parent.right = null;
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

    private TreeNode<T> searchNode(TreeNode<T> node, T t) {
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

    private void addNode(TreeNode<T> node, T t) {
        if (node.t.compareTo(t) == 0) {
            throw new UnsupportedOperationException("已存在相同元素：" + t);
        } else if (node.t.compareTo(t) > 0) {
            if (node.leftIsNIL()) {
                node.addNodeLeft(t);
            } else {
                addNode(node.left, t);
            }
        } else {
            if (node.rightIsNIL()) {
                node.addNodeRight(t);
            } else {
                addNode(node.right, t);
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
            TreeNode<T> newNode;
            if (isLeft && node.left.isRed()) {
                // LL
                newNode = rightRotate(node);
                newNode.red = false;
                parent.left = newNode;
            } else if (!isLeft && node.right.isRed()) {
                // RR
                newNode = leftRotate(node);
                newNode.red = false;
                parent.right = newNode;
            } else if (isLeft && node.right.isRed()) {
                // LR
                parent.left = leftRotate(node);
                parent.red = true;
            } else {
                // RL
                parent.right = rightRotate(node);
                parent.red = true;
            }
        }
    }

    private TreeNode<T> leftRotate(TreeNode<T> node) {
        TreeNode<T> retNode = node.right;
        node.right = retNode.left;
        retNode.parent = node.parent;
        node.parent = retNode;
        retNode.left = node;
        return retNode;
    }

    private TreeNode<T> rightRotate(TreeNode<T> node) {
        TreeNode<T> retNode = node.left;
        node.left = retNode.right;
        retNode.parent = node.parent;
        node.parent = retNode;
        retNode.right = node;
        return retNode;
    }

    private void flipColor(TreeNode<T> node) {
        node.red = true;
        node.left.red = false;
        node.right.red = false;
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
            addNode(root, t);
        }
        count++;
    }

    private void infix(TreeNode<T> node) {
        if (node.isLeaf()) {
            System.out.print(node.t + "\t");
            return;
        }
        if (node.left != null) {
            infix(node.left);
        }
        System.out.print(node.t + "\t");
        if (node.right != null) {
            infix(node.right);
        }
    }



    public static void main(String[] args) {
        Integer[] arr = new Integer[]{7, 3, 10, 12, 5, 1, 9, 2};
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        for (int i = 0; i < arr.length; i++) {
            tree.add(arr[i]);
        }
        tree.infix(tree.root);
        System.out.println();
        System.out.println("size:" + tree.count);
        tree.remove(2);
        tree.remove(5);
        tree.remove(9);
        tree.remove(12);
        tree.remove(7);
        tree.remove(3);
        tree.infix(tree.root);
        System.out.println();
        tree.remove(100);
        tree.remove(1);
        tree.remove(10);
        System.out.println(tree.isEmpty());

    }

}
