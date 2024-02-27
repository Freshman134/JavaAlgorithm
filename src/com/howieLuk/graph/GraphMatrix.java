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
public class GraphMatrix<T> implements Graph<T> {

    private static final int INIT_SIZE = 1 << 4;

    private int[][] matrix = new int[INIT_SIZE][INIT_SIZE];

    private Map<Integer, MatrixVertex> verticesMap = new HashMap<>();

    private Map<T, Integer> tIndMap = new HashMap<>();

    private Queue<Integer> canUseInd = new LinkedList<>();
    {
        IntStream.range(0, INIT_SIZE - 1).forEach(canUseInd::add);
    }

    private int verticesNum = 0;

    private int edgesNum = 0;

    private class MatrixVertex implements Vertex<T> {
        T t;
        int ind;

        public MatrixVertex(T t, int ind) {
            this.t = t;
            this.ind = ind;
        }

        @Override
        public T getVal() {
            return t;
        }

        @Override
        public void setVal(T t) {
            this.t = t;
        }

        @Override
        public Collection<Edge<T>> getRelateEdges() {
            List<Edge<T>> list = new LinkedList<>();
            for (int i = 0; i < matrix[ind].length; i++) {
                if (i == ind) {
                    continue;
                }
                if (matrix[ind][i] != 0) {
                    MatrixVertex v = verticesMap.get(i);
                    MatrixEdge edge = new MatrixEdge(ind, v.ind);
                    list.add(edge);
                }
            }
            return list;
        }

        @Override
        public String toString() {
            return t.toString();
        }
    }

    private class MatrixEdge implements Edge<T> {
        private final MatrixVertex v1;
        private final MatrixVertex v2;

        MatrixEdge(int v1, int v2) {
            this.v1 = verticesMap.get(v1);
            this.v2 = verticesMap.get(v2);
        }

        @Override
        public boolean isDiEdge() {
            return false;
        }

        @Override
        public int getWeight() {
            return matrix[v1.ind][v2.ind];
        }

        @Override
        public Vertex<T> getFrom() {
            return v1;
        }

        @Override
        public Vertex<T> getTo() {
            return v2;
        }
    }

    /**
     * 删除顶点的所有边
     * @param v 顶点ind
     * @return 如果删除成功返回true，如果顶点本身就没有边返回false
     */
    private boolean clearEdges(int v) {
        if (verticesMap.get(v) == null) {
            throw new RuntimeException("顶点" + v + "没有找到");
        }
        for (int i = 0; i < matrix[v].length; i++) {
            if (v == i) {
                continue;
            }
            if (matrix[v][i] != 0) {
                setEdge(v, i, 0);
            }
        }
        return true;
    }

    @Override
    public boolean add(T t) {
        if (getSizeOfVertices() >= INIT_SIZE || canUseInd.isEmpty()) {
            throw new RuntimeException("邻接矩阵已满");
        }
        if (tIndMap.get(t) != null) {
            return false;
        }
        MatrixVertex vertex = new MatrixVertex(t, canUseInd.poll());
        verticesMap.put(vertex.ind, vertex);
        tIndMap.put(t, vertex.ind);
        verticesNum++;
        return true;
    }

    @Override
    public Vertex<T> getVertex(T t) {
        Integer ind = tIndMap.get(t);
        return ind != null ? getVertexByInd(ind) : null;
    }

    @Override
    public Vertex<T> getVertexByInd(int ind) {
        return verticesMap.get(ind);
    }

    @Override
    public boolean removeVertex(int ind) {
        MatrixVertex v = (MatrixVertex) getVertexByInd(ind);
        if (v == null) {
            return false;
        }
        revokeMatrixIndex(v.ind);
        clearEdges(v.ind);
        verticesMap.remove(v.ind);
        tIndMap.remove(v.t);
        verticesNum--;
        return true;
    }

    @Override
    public Collection<Integer> getVerticesInd() {
        return tIndMap.values();
    }

    @Override
    public int getSizeOfVertices() {
        return verticesNum;
    }

    @Override
    public boolean setEdge(int v1, int v2, int weight) {
        if (weight < 0) {
            throw new RuntimeException("weight不能是负数 " + weight);
        }
        checkVertexExistsByInd(v1);
        checkVertexExistsByInd(v2);
        return doSetEdge(v1, v2, weight);
    }

    private boolean doSetEdge(int v1, int v2, int weight) {
        if (weight == 0) {
            // 删除边时，weight = 0
            if (matrix[v1][v2] != 0) {
                edgesNum--;
            } else {
                return false;
            }
        } else if (matrix[v1][v2] > 0) {
            return false;
        } else {
            edgesNum++;
        }
        matrix[v1][v2] = matrix[v2][v1] = weight;
        return true;
    }

    @Override
    public boolean removeEdge(int v1, int v2) {
        return setEdge(v1, v2, 0);
    }

    @Override
    public Edge<T> getEdge(int v1, int v2) {
        checkVertexExistsByInd(v1);
        checkVertexExistsByInd(v2);
        int weight = matrix[v1][v2];
        if (weight != 0) {
            return new MatrixEdge(v1, v2);
        }
        return null;
    }

    @Override
    public int getSizeOfEdges() {
        return edgesNum;
    }

    @Override
    public void showGraphBy(int v) {
        boolean[] visited = new boolean[matrix.length];
        dfs(visited, v);
        visited = new boolean[matrix.length];
        bfs(visited, v);
    }

    @Override
    public boolean isEmpty() {
        return getSizeOfVertices() == 0;
    }

    private int getEdgeWeightByInd(int v1, int v2) {
        return matrix[v1][v2];
    }

    public void showGraph() {
        boolean[] visited = new boolean[matrix.length];
        for (MatrixVertex vertex : verticesMap.values()) {
            if (vertex == null) {
                continue;
            }
            if (!visited[vertex.ind]) {
                dfs(visited, vertex.ind);
            }
        }
        System.out.println();
        visited = new boolean[matrix.length];
        for (MatrixVertex vertex : verticesMap.values()) {
            if (vertex == null) {
                continue;
            }
            if (!visited[vertex.ind]) {
                bfs(visited, vertex.ind);
            }
        }
    }


    private void dfs(boolean[] visited, int v) {
        visited[v] = true;
        System.out.print(getVertexByInd(v).getVal() + "\t");
        for (int i = 0; i < matrix[v].length; i++) {
            if (visited[i] || matrix[v][i] == 0) {
                continue;
            }
            dfs(visited, i);
        }
    }

    private void bfs(boolean[] visited, int v) {
        Queue<MatrixVertex> vertexQueue = new LinkedList<>();
        vertexQueue.add(verticesMap.get(v));
        while (!vertexQueue.isEmpty()) {
            MatrixVertex top = vertexQueue.peek();
            visited[top.ind] = true;
            for (int i = 0; i < matrix[v].length; i++) {
                if (visited[i] || matrix[top.ind][i] == 0 || vertexQueue.contains(verticesMap.get(i))) {
                    continue;
                }
                vertexQueue.add(verticesMap.get(i));
            }
            System.out.print(vertexQueue.poll().t + "\t");
        }
    }

    private void revokeMatrixIndex(int v) {
        if (canUseInd.contains(v)) {
            throw new RuntimeException("索引" + v + "以被收回");
        }
        canUseInd.add(v);
    }

    private void checkVertexExistsByInd(int v) {
        if (verticesMap.get(v) == null) {
            throw new RuntimeException("顶点" + v + "不存在");
        }
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new GraphMatrix<>();
        Random random = new Random();
        IntStream.range(0, 15).forEach(i -> graph.add(i));
        System.out.println(graph.getSizeOfVertices());
        int k = 0;
        graph.setEdge(1, 2, 1);
        graph.setEdge(2, 1, 1);
        graph.setEdge(1, 3, 1);
        System.out.println(graph.setEdge(2, 3, 1));
        System.out.println(graph.setEdge(2, 3, 1));
        while (!graph.isEmpty()) {
            graph.removeVertex(k++);
        }
        graph.setEdge(10, 10, 1);
        System.out.println(graph.getVertex(8));
        for (int i = 0; i < 4; i++) {
            System.out.println(graph.removeVertex(random.nextInt(8)));
        }
        List<Integer[]> edges = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int v1 = random.nextInt(16);
            while (graph.getVertex(v1).getVal() == null) {
                v1 = random.nextInt(16);
            }
            for (int j = 0; j < 4; j++) {
                int v2 = random.nextInt(16);
                while (v1 == v2 || graph.getVertex(v2).getVal() == null) {
                    v2 = random.nextInt(16);
                }
                graph.setEdge(v1, v2, 1);
                edges.add(new Integer[]{v1, v2});
            }
        }
        for (Integer[] edge : edges) {
            System.out.println(graph.getEdge(edge[0], edge[1]).getWeight() + "\t" + edge[0] + "," + edge[1]);
        }
        System.out.println();
        graph.showGraph();
        for (int i = 0; i < 16; i++) {
            graph.removeVertex(i);
        }
        for (int i = 0; i < 9; i++) {
            graph.add(i);
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
