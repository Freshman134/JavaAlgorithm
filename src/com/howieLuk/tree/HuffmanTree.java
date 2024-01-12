package com.howieLuk.tree;

import com.howieLuk.sort.HeapSort;
import com.howieLuk.sort.MergeSort;

import java.util.*;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/12 19:26
 * @Version 1.0
 **/
public class HuffmanTree<K extends Integer, T> implements Comparable<HuffmanTree<K, T>> {

    HuffmanTreeNode<K, T> root;
    int size;

    static class HuffmanTreeNode<K extends Integer, T> implements Comparable<HuffmanTreeNode<K, T>> {

        T e; K key;

        HuffmanTreeNode<K, T> left, right;

        boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(HuffmanTreeNode<K, T> o) {
            return key.compareTo(o.key);
        }

        void pre() {
            if (e != null) {
                System.out.print(e + ":" + key + "\t");
            }
            if (left != null) {
                left.pre();
            }
            if (right != null) {
                right.pre();
            }
        }
    }

    HuffmanTree(T t, K key) {
        root = new HuffmanTreeNode<>();
        root.e = t;
        root.key = key;
        size = 1;
    }

    public static <T> HuffmanTree<Integer, T> getHuffmanTree(Map<Integer, T> listMap) {
        if (listMap == null || listMap.isEmpty()) {
            return null;
        }
        List<HuffmanTree<Integer, T>> treeList = getTreeList(listMap);
        return toHuffmanTree(treeList);
    }

    @Override
    public String toString() {
        return String.valueOf(root.e);
    }

    @Override
    public int compareTo(HuffmanTree<K, T> o) {
        return root.compareTo(o.root);
    }

    public void pre() {
        if (root == null) {
            return;
        }
        root.pre();
    }

    static <T> HuffmanTree<Integer, T> toHuffmanTree(List<HuffmanTree<Integer, T>> treeList) {
        MergeSort.sort(treeList);
        while (treeList.size() > 1) {
            HuffmanTree<Integer, T> tree1 = treeList.remove(0);
            HuffmanTree<Integer, T> tree2 = treeList.remove(0);
            HuffmanTree<Integer, T> newTree = getHuffmanTree(tree1, tree2);
            int i = 0;
            while (i < treeList.size() && newTree.compareTo(treeList.get(i)) > 0) {
                i++;
            }
            treeList.add(i, newTree);
        }
        return treeList.get(0);
    }

    private static <T> List<HuffmanTree<Integer, T>> getTreeList(Map<Integer, T> listMap) {
        List<HuffmanTree<Integer, T>> list = new ArrayList<>();
        for (Map.Entry<Integer, T> e : listMap.entrySet()) {
            list.add(new HuffmanTree<>(e.getValue(), e.getKey()));
        }
        return list;
    }

    private static <T> HuffmanTree<Integer, T>
    getHuffmanTree(HuffmanTree<Integer, T> tree1, HuffmanTree<Integer, T> tree2) {
        int newRootKey = tree1.root.key + tree2.root.key;
        HuffmanTree<Integer, T> huffmanTree = new HuffmanTree<>(null, newRootKey);
        huffmanTree.root.left = tree1.root;
        huffmanTree.root.right = tree2.root;
        huffmanTree.size = tree1.size + tree2.size + 1;
        return huffmanTree;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> listMap = new HashMap<>();
        Random random = new Random();
//        int [] arr = {13, 7, 8, 3, 29, 6, 1};
        for (int i = 0; i < 1000; i++) {
            Integer k = random.nextInt(1000);
            listMap.put(k, k);
        }
        int count = listMap.keySet().stream().reduce(0, Integer::sum);
        HuffmanTree<Integer, Integer> tree = getHuffmanTree(listMap);
        System.out.println(tree.root.key == count);
    }

}
