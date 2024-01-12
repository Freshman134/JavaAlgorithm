package com.howieLuk.tree;

/**
 * @Deacription 树
 * @Author HowieLuk
 * @Date 2024/1/10 19:20
 * @Version 1.0
 **/
public class MyTree<T extends Comparable<T>> {

    TreeNode<T> root;
    int size;

    static class TreeNode<T extends Comparable<T>> {
        T e;
        TreeNode<T> left, right;
        boolean deleted = false;

        void pre() {
            if (left != null) {
                left.pre();
            }
            System.out.print(e + "\t");
            if (right != null) {
                right.pre();
            }
        }

        void infix() {
            System.out.print(e + "\t");
            if (left != null) {
                left.infix();
            }
            if (right != null) {
                right.infix();
            }
        }

        void post() {
            if (left != null) {
                left.post();
            }
            if (right != null) {
                right.post();
            }
            System.out.print(e + "\t");
        }

        T get(T t) {
            if (e.equals(t)) {
                return e;
            } else if (left != null && e.compareTo(t) > 0) {
                return left.get(t);
            } else if (right != null) {
                return right.get(t);
            }
            return null;
        }

        T put(T t) {
            if (e.equals(t)) {
                return e;
            } else if (e.compareTo(t) > 0) {
                if (left == null) {
                    left = new TreeNode();
                    left.e = t;
                    return null;
                } else {
                    return left.put(t);
                }
            } else {
                if (right == null) {
                    right = new TreeNode();
                    right.e = t;
                    return null;
                } else {
                    return right.put(t);
                }
            }
        }

        TreeNode<T> getMinNode() {
            if (left != null) {
                return left.getMinNode();
            }
            return this;
        }

        TreeNode<T> getMaxNode() {
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

    public T remove(T val) {
        if (root == null) {
            return null;
        }
        T t = root.get(val);
        if (t == null) {
            return null;
        }
        root = delNode(root, val);
        size--;
        return t;
    }

    public T get(T val) {
        if (root == null) {
            return null;
        }
        return root.get(val);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T put(T e) {
        if (root == null) {
            root = new TreeNode();
            root.e = e;
            return null;
        }
        T t = root.put(e);
        if (t == null) {
            size++;
            return null;
        }
        return t;
    }

    private void swapVal(TreeNode<T> node1, TreeNode<T> node2) {
        T tmp = node1.e;
        node1.e = node2.e;
        node2.e = tmp;
    }

    private TreeNode<T> delNode(TreeNode<T> delNode, T val) {
        if (val.compareTo(delNode.e) == 0) {
            if (delNode.isLeaf()) {
                return null;
            }
            if (delNode.left != null) {
                TreeNode<T> maxNode = delNode.left.getMaxNode();
                // swap
                swapVal(delNode, maxNode);
                delNode.left = delNode(delNode.left, val);
            } else {
                TreeNode<T> minNode = delNode.right.getMinNode();
                swapVal(delNode, minNode);
                delNode.right = delNode(delNode.right, val);
            }
        } else if (val.compareTo(delNode.e) < 0) {
            if (delNode.left != null) {
                delNode.left = delNode(delNode.left, val);
            }
        } else {
            if (delNode.right != null) {
                delNode.right = delNode(delNode.right, val);
            }
        }
        return delNode;
    }

}
