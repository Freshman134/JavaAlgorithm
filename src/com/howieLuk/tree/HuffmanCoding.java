package com.howieLuk.tree;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Deacription 哈夫曼编码
 * @Author HowieLuk
 * @Date 2024/1/12 21:08
 * @Version 1.0
 **/
public class HuffmanCoding {

    private final byte[] msg;

    public HuffmanCoding(byte[] msg) {
        this.msg = msg;
    }

    public Map<Byte, String> getCodeMap() {
        Map<Byte, Integer> countingMap = getCountingMap();
        HuffmanTree<Integer, Byte> huffmanTree = HuffmanTree.toHuffmanTree(getHuffmanTreeList(countingMap));
//        huffmanTree.pre();

        Map<Byte, String> retMap = new HashMap<>();
        preHuffmanTreeAndPutCodeInto(retMap, "", huffmanTree.root);
        return retMap;
    }

    private Map<Byte, Integer> getCountingMap() {
        Map<Byte, Integer> map = new HashMap<>();
        for (int i = 0; i < msg.length; i++) {
            if (!map.containsKey(msg[i])) {
                map.put(msg[i], 1);
            } else {
                map.put(msg[i], map.get(msg[i]) + 1);
            }
        }
        return map;
    }

    private List<HuffmanTree<Integer, Byte>> getHuffmanTreeList(Map<Byte, Integer> countingMap) {
        List<HuffmanTree<Integer, Byte>> retList = new ArrayList<>();
        for (Map.Entry<Byte, Integer> e : countingMap.entrySet()) {
            HuffmanTree<Integer, Byte> tree = new HuffmanTree<>(e.getKey(), e.getValue());
            retList.add(tree);
        }
        return retList;
    }

    private void preHuffmanTreeAndPutCodeInto(Map<Byte, String> retMap, String code,
                                              HuffmanTree.HuffmanTreeNode<Integer, Byte> node) {
        if (node.isLeaf()) {
            retMap.put(node.e, code);
        }
        if (node.left != null) {
            preHuffmanTreeAndPutCodeInto(retMap, code + "0", node.left); // 往左0
        }
        if (node.right != null) {
            preHuffmanTreeAndPutCodeInto(retMap, code + "1", node.right);// 往右1
        }
    }

    public static void main(String[] args) {
        String test = "i like like like java do you like a java";
        byte[] bytes = test.getBytes(StandardCharsets.UTF_8);
        HuffmanCoding huffmanCoding = new HuffmanCoding(bytes);
        for (int i = 0; i < bytes.length; i++) {

        }
    }

}
