package com.howieLuk.graph;

import java.util.Collection;

/**
 * @Deacription Dijkstra算法
 * @Author HowieLuk
 * @Date 2024/2/20 3:18
 * @Version 1.0
 **/
public class Dijkstra {

    public static Graph<Integer> getBy(Graph<Integer> graph) {
        if (graph.emptyGraph()) {
            return null;
        }
        GraphMatrix<Integer> retGraph = new GraphMatrix<>();
        // 通过vertices遍历所有节点，再把经过的节点放入
        


        return retGraph;
    }

    private static class Edge {
        int from;
        int _to;
    }

}
