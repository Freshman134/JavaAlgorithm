package com.howieLuk.algorithm;

import java.util.*;

/**
 * 贪婪算法（以集合覆盖为例）
 * @author HowieLuk
 * @date 2024/1/24 1:55
 */
public class GreedyAlgorithm {

    /**
     *
     * @param rs 电台集合
     * @return
     */
    public static List<String> solution(Map<String, Set<String>> rs) {
        Set<String> ds = new HashSet<>();
        rs.values().forEach(ds::addAll);
        List<String> retList = new ArrayList<>();
        while (!ds.isEmpty()) {
            String maxR = null;
            int maxCover = 0;
            for (Map.Entry<String, Set<String>> entry : rs.entrySet()) {
                entry.getValue().retainAll(ds);
                if (entry.getValue().size() > maxCover) {
                    maxCover = entry.getValue().size();
                    maxR = entry.getKey();
                }
            }
            ds.removeAll(rs.get(maxR));
            retList.add(maxR);
            rs.remove(maxR);
        }
        return retList;
    }
}
