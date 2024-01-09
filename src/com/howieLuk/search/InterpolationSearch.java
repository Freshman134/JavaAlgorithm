package com.howieLuk.search;

import com.howieLuk.linkedList.MyList;

/**
 * 插值查找
 * @author HowieLuk
 * @date 2024/1/9 1:21
 */
public class InterpolationSearch extends AbstractSearch<Integer> {

    @Override
    public int search(MyList<Integer> list, Integer e, int startInd, int endInd) {
        if (list == null || list.isEmpty()) {
            return -1;
        }
        Integer min = list.get(startInd);
        Integer max = list.get(endInd);
        if (e.compareTo(min) >= 0 && e.compareTo(max) <= 0) {
            max = list.get(endInd);
            min = list.get(startInd);
            while (startInd <= endInd) {
                int midInd = startInd + ((e - min) * (endInd - startInd) / (max - min));
                if (list.get(midInd).compareTo(e) == 0) {
                    return midInd;
                } else if (list.get(midInd).compareTo(e) > 0) {
                    endInd = midInd - 1;
                } else {
                    startInd = midInd + 1;
                }
            }
        }
        return -1;
    }

}
