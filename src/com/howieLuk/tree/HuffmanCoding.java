package com.howieLuk.tree;

import java.io.Serializable;
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

    public static class HuffmanCode implements Serializable {

        private final Map<Byte, String> codeMap;

        private final byte [] msg;

        private final int msgLen;

        static String testBinaryStr;

        public HuffmanCode(Map<Byte, String> codeMap, byte[] msg, int msgLen) {
            this.codeMap = codeMap;
            this.msg = msg;
            this.msgLen = msgLen;
        }

        public Map<Byte, String> getCodeMap() {
            return codeMap;
        }

        public byte[] getMsg() {
            return msg;
        }

        public int getMsgLen() {
            return msgLen;
        }
    }

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
            preHuffmanTreeAndPutCodeInto(retMap, "0" + code, node.left); // 往左0
        }
        if (node.right != null) {
            preHuffmanTreeAndPutCodeInto(retMap, "1" + code, node.right);// 往右1
        }
    }

    public static HuffmanCode getEncode(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        HuffmanCoding huffmanCoding = new HuffmanCoding(bytes);
        Map<Byte, String> codeMap = huffmanCoding.getCodeMap();
        StringBuilder encodeMsg = new StringBuilder();
        for (byte b : bytes) {
            encodeMsg.append(codeMap.get(b));
        }
        byte[] encodeMsgBytes = new byte[encodeMsg.length() % 8 == 0 ?
                encodeMsg.length() >> 3 : (encodeMsg.length() >> 3) + 1];
        for (int i = 0; i < encodeMsgBytes.length; i++) {
            StringBuilder bs;
            if (i < encodeMsgBytes.length - 1) {
                bs = new StringBuilder(encodeMsg.substring(i << 3, (i << 3) + 8));
            } else {
                bs = new StringBuilder(encodeMsg.substring(i << 3));
                while (bs.length() < 8) {
                    bs.append("0");
                }
            }
            encodeMsgBytes[i] = (byte) Integer.parseInt(bs.toString(), 2);
        }
        HuffmanCode code = new HuffmanCode(codeMap, encodeMsgBytes, encodeMsg.length());
        code.testBinaryStr = encodeMsg.toString();
        return code;
    }

    public static String decode(HuffmanCode code) {
        byte [] encodeArr = code.getMsg();
        StringBuilder encodeStr = new StringBuilder();
        for (int i = 0; i < encodeArr.length; i++) {
            System.out.println(encodeStr.length());
            if (code.getMsgLen() - encodeStr.length() >= 8) {
                if (encodeArr[i] >= 0) {
                    System.out.println(Integer
                            .toBinaryString(encodeArr[i] | 0x100).substring(1)
                            .equals(HuffmanCode.testBinaryStr.substring(i * 8, i * 8 + 8)));
                    encodeStr.append(Integer.toBinaryString(encodeArr[i] | 0x100).substring(1));
                } else {
                    System.out.println(Integer.toBinaryString(encodeArr[i] & 0xff)
                            .equals(HuffmanCode.testBinaryStr.substring(i * 8, i * 8 + 8)));
                    encodeStr.append(Integer.toBinaryString(encodeArr[i] & 0xff));
                }
            } else {
                System.out.println(encodeStr.length());
                int len = code.getMsgLen() - encodeStr.length();
                System.out.println(Integer.toBinaryString((encodeArr[i] >> (8 - len)) | (1 << len)).substring(1));
                encodeStr.append(Integer.toBinaryString((encodeArr[i] >> (8 - len)) | (1 << len)).substring(1));
            }
        }
        int encodeInd = 0;
        System.out.println(HuffmanCode.testBinaryStr.equals(encodeStr.toString()));
        return null;
    }

    public static void main(String[] args) {
        String test = "i like like like java do you like a java";
        HuffmanCode code = HuffmanCoding.getEncode(test);
        HuffmanCoding.decode(code);
        byte b = -88;
        int i = b;
//        System.out.println(b);
//        System.out.println(i);
//        System.out.println(Integer.toBinaryString(i & 0xff));
//        System.out.println(Byte.parseByte("1010", 2));
    }

}
