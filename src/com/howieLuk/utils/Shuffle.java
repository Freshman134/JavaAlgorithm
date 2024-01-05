package com.howieLuk.utils;

import com.howieLuk.linkedList.MyLinkedList;
import com.howieLuk.linkedList.MyList;

import java.util.Random;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/5 14:23
 * @Version 1.0
 **/
public class Shuffle {

    public static <T> void execute(MyList<T> list) {
        Random random = new Random();
        for (int i = 0; i < list.size(); i++) {
            int newInd = random.nextInt(list.size());
            T tmp = list.get(i);
            list.set(i, list.get(newInd));
            list.set(newInd, tmp);
        }
    }

}
