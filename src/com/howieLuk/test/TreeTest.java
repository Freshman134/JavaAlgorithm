package com.howieLuk.test;

import com.howieLuk.linkedList.DoubleLinkedList;
import com.howieLuk.tree.ArrayTree;
import com.howieLuk.tree.MyTree;

import java.util.List;
import java.util.Random;

/**
 * @author HowieLuk
 * @date 2024/1/11 0:53
 */
public class TreeTest {

    public static void main(String[] args) {
        ArrayTree<Integer> tree = new ArrayTree<>();
        Random random = new Random();
        List<Integer> eleList = new DoubleLinkedList<>();
        // test put
        for (int i = 0; i < 100; i++) {
            int e = random.nextInt(100);
            if (tree.put(e) == null) {
                eleList.add(e);
            }
        }

        // test get
        for (int i = 0; i < eleList.size(); i++) {
            Integer e = eleList.get(i);
            if (!e.equals(tree.get(e))) {
                throw new RuntimeException("get错误！");
            }
        }

        // test delete
        System.out.println("size:" + eleList.size());
        for (int i = 0; i < 10; i++) {
            int ind = random.nextInt(eleList.size());
            int e = eleList.remove(ind);
//            System.out.println("delete:" + e);
            tree.remove(e);
            if (tree.get(e) != null) {
                throw new RuntimeException("删除错误");
            }
        }

        // 测试误删情况
        for (int i = 0; i < eleList.size(); i++) {
            if (tree.get(eleList.get(i)) == null) {
                throw new RuntimeException("误删情况！:" + eleList.get(i));
            }
        }


    }

}
