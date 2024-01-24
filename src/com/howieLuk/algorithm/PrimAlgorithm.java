package com.howieLuk.algorithm;

import com.howieLuk.graph.Graph;
import com.howieLuk.graph.GraphMatrix;

/**
 * @author HowieLuk
 * @date 2024/1/25 1:43
 */
public class PrimAlgorithm {


    private static Graph<Integer> getGraph() {
        Graph<Integer> graph = new GraphMatrix<>();
        for (int i = 0; i < 9; i++) {
            graph.insertVertex(i);
        }
        graph.setEdge(1, 2, 1);
        graph.setEdge(1, 3, 1);
        graph.setEdge(2, 4, 1);
        graph.setEdge(2, 5, 1);
        graph.setEdge(4, 8, 1);
        graph.setEdge(5, 8, 1);
        graph.setEdge(3, 6, 1);
        graph.setEdge(3, 7, 1);
        graph.setEdge(6, 7, 1);
        System.out.println();
        graph.showGraph();
        return graph;
    }

    public static void main(String[] args) {
        getGraph();
    }

}
