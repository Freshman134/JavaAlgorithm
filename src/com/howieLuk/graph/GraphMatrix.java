package com.howieLuk.graph;

//import com.howieLuk.queue.ArrayQueue;
//import com.howieLuk.queue.Queue;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Deacription 邻接矩阵有向图
 * @Author HowieLuk
 * @Date 2024/1/19 21:24
 * @Version 1.0
 **/
public class GraphMatrix<K, V> implements Graph<K, V> {

    private static final int INIT_SIZE = 1 << 4;

    private int[][] matrix = new int[INIT_SIZE][INIT_SIZE];

    private Map<K, MatrixVertex> verticesMap = new HashMap<>();

    private Queue<Integer> canUseInd = new LinkedList<>();
    {
        IntStream.range(0, INIT_SIZE - 1).forEach(canUseInd::add);
    }

    private int verticesNum = 0;

    private int edgesNum = 0;

    private class MatrixVertex implements Vertex<K, V> {
        V val;
        int ind;
        K key;

        public MatrixVertex(K key, V val, int ind) {
            this.key = key;
            this.val = val;
            this.ind = ind;
        }


        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getVal() {
            return val;
        }

        @Override
        public void setVal(V v) {
            this.val = v;
        }

        @Override
        public String toString() {
            return "{key:" + key + "," +
                    "val:" + val +
                    "}";
        }
    }

    @Override
    public T removeVertex(int v) {
        if (vertices.get(v) == null || !clearEdges(v)) {
            return null;
        }
        Vertex retV = vertices.set(v, null);
        revoke(v);
        verticesNum--;
        return retV.val;
    }

    @Override
    public List<Integer> getRelateVertexIndByInd(int verInd) {
        return null;
    }


    @Override
    public boolean setEdge(int v1, int v2, int weight) {
        if (weight < 0) {
            throw new RuntimeException("weight不能是负数 " + weight);
        }
        if (vertices.get(v1) == null || vertices.get(v2) == null || v1 == v2) {
            return false;
        }
        if (weight > 0 && matrix[v1][v2] == 0 && matrix[v2][v1] == 0) { // 只有该关系不存在时才添加边的数量
            edgesNum++;
        } else if (weight == 0) { // 无向图需要
            edgesNum--;
        }
//        else if (weight == 0 && matrix[v1][v2] != 0 && matrix[v2][v1] == 0) { // 只有该关系完全被删除时，才减少边的数量
//            edgesNum--;
//        }
        matrix[v1][v2] = weight;
        matrix[v2][v1] = weight; // 无向图需要
        return true;
    }

    @Override
    public boolean removeEdge(int v1, int v2) {
        return setEdge(v1, v2, 0)
//                && setEdge(v2, v1, 0) // 有向图需要
                ;
    }

    @Override
    public boolean clearEdges(int v) {
        if (vertices.get(v) == null) {
            return false;
        }
        for (int i = 0; i < matrix[v].length; i++) {
            if (v == i) {
                continue;
            }
            if (matrix[v][i] != 0 || matrix[i][v] != 0) {
                removeEdge(v, i);
            }
        }
        return true;
    }

    @Override
    public void add(K k, V v) {
        if (getSizeOfVertices() >= INIT_SIZE || canUseInd.isEmpty()) {
            throw new RuntimeException("邻接矩阵已满");
        }
        MatrixVertex vertex = new MatrixVertex(k, v, canUseInd.poll());
        verticesMap.put(vertex.key, vertex);
        verticesNum++;
    }

    @Override
    public Collection<K> getVerticesKey() {
        return verticesMap.keySet();
    }

    @Override
    public int getSizeOfVertices() {
        return verticesNum;
    }

    @Override
    public void setEdge(K k1, K k2, int weight) {

    }

    @Override
    public Collection<Edge<K, V>> getEdges(K k) {
        return null;
    }

    @Override
    public boolean removeEdge(K k1, K k2) {
        return false;
    }

    @Override
    public int getSizeOfEdges() {
        return edgesNum;
    }

    @Override
    public void showGraphBy(K k) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private int getEdgeWeightByInd(int v1, int v2) {
        return matrix[v1][v2];
    }

    @Override
    public List<Integer> getRelationVertex(int v) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < matrix[v].length; i++) {
            if (matrix[v][i] != 0) {
                list.add(i);
            }
        }
        return list;
    }

    @Override
    public void showGraphBy(int v) {
        boolean[] visited = new boolean[matrix.length];
        dfs(visited, v);
        visited = new boolean[matrix.length];
        bfs(visited, v);
    }

    @Override
    public void showGraph() {
        boolean[] visited = new boolean[matrix.length];
        for (Vertex vertex : vertices) {
            if (vertex == null) {
                continue;
            }
            if (!visited[vertex.ind]) {
                dfs(visited, vertex.ind);
            }
        }
        System.out.println();
        visited = new boolean[matrix.length];
        for (Vertex vertex : vertices) {
            if (vertex == null) {
                continue;
            }
            if (!visited[vertex.ind]) {
                bfs(visited, vertex.ind);
            }
        }
    }

    @Override
    public boolean emptyGraph() {
        return vertices.isEmpty();
    }

    private void dfs(boolean[] visited, int v) {
        visited[v] = true;
        System.out.print(getVal(v) + "\t");
        for (int i = 0; i < matrix[v].length; i++) {
            if (visited[i] || matrix[v][i] == 0) {
                continue;
            }
            dfs(visited, i);
        }
    }

    private void bfs(boolean[] visited, int v) {
        Queue<Vertex> vertexQueue = new LinkedList<>();
        vertexQueue.add(vertices.get(v));
        while (!vertexQueue.isEmpty()) {
            Vertex top = vertexQueue.peek();
            visited[top.ind] = true;
            for (int i = 0; i < matrix[v].length; i++) {
                if (visited[i] || matrix[top.ind][i] == 0 || vertexQueue.contains(vertices.get(i))) {
                    continue;
                }
                vertexQueue.add(vertices.get(i));
            }
            System.out.print(vertexQueue.poll().val + "\t");
        }
    }

    private void revoke(int v) {
        // once insertSort
        for (int i = 0; i < canUseInd.size(); i++) {
            if (v < canUseInd.get(i)) {
                canUseInd.add(i, v);
                return;
            }
        }
        // largest
        canUseInd.add(v);
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new GraphMatrix<>();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            graph.insertVertex(i);
        }
        graph.setEdge(10, 10, 1);
        System.out.println(graph.getVertexIndex(8));
        for (int i = 0; i < 4; i++) {
            System.out.println(graph.removeVertex(random.nextInt(8)));
        }
        List<Integer[]> edges = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int v1 = random.nextInt(16);
            while (graph.getVal(v1) == null) {
                v1 = random.nextInt(16);
            }
            for (int j = 0; j < 4; j++) {
                int v2 = random.nextInt(16);
                while (v1 == v2 || graph.getVal(v2) == null) {
                    v2 = random.nextInt(16);
                }
                graph.setEdge(v1, v2, 1);
                edges.add(new Integer[]{v1, v2});
            }
        }
        for (Integer[] edge : edges) {
            System.out.println(graph.getWeight(edge[0], edge[1]) + "\t" + edge[0] + "," + edge[1]);
        }
        System.out.println();
        graph.showGraph();
        for (int i = 0; i < 16; i++) {
            graph.removeVertex(i);
        }
        for (int i = 0; i < 9; i++) {
            graph.insertVertex(i);
        }
        graph.setEdge(1, 2, 1);
        graph.setEdge(2, 1, 1);
        graph.setEdge(1, 3, 1);
        graph.setEdge(3, 1, 1);
        graph.setEdge(2, 4, 1);
        graph.setEdge(4, 2, 1);
        graph.setEdge(2, 5, 1);
        graph.setEdge(5, 2, 1);
        graph.setEdge(4, 8, 1);
        graph.setEdge(8, 4, 1);
        graph.setEdge(5, 8, 1);
        graph.setEdge(8, 5, 1);
        graph.setEdge(3, 6, 1);
        graph.setEdge(6, 3, 1);
        graph.setEdge(3, 7, 1);
        graph.setEdge(7, 3, 1);
        graph.setEdge(6, 7, 1);
        graph.setEdge(7, 6, 1);
        System.out.println();
        graph.showGraph();
    }

}
