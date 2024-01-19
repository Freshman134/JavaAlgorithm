package com.howieLuk.graph;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/19 20:16
 * @Version 1.0
 **/
public interface Graph<T> {

    void insertVertex(T t);

    boolean putVertexVal(int v, T t);

    T removeVertex(int v);

    boolean setEdge(int v1, int v2, int weight);

    boolean removeEdge(int v1, int v2);

    boolean clearEdges(int v);

    int getSizeOfVertex();

    int getSizeOfEdges();

    T getVal(int v);

    int getVertexIndex(T t);

    int getWeight(int v1, int v2);

    void showGraphBy(int v);

    void showGraph();

}
