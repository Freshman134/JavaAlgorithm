package com.howieLuk.graph;

import java.util.Collection;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/19 20:16
 * @Version 1.0
 **/
public interface Graph<T> {
    /**
     * 添加定点
     * @param t 顶点数据
     * @return 如果顶点已经存在则返回false，如果插入顶点成功返回true
     */
    boolean add(T t);

    Vertex<T> getVertex(T t);

    Vertex<T> getVertexByInd(int ind);

    /**
     * 获取顶点索引集合
     * @return
     */
    Collection<Integer> getVerticesInd();

    /**
     * 删除顶点
     * @param v 顶点索引
     * @return 是否删除成功
     */
    boolean removeVertex(int v);

    /**
     * 获取定点个数
     * @return
     */
    int getSizeOfVertices();

    /**
     * 设置边
     * @param v1 顶点1
     * @param v2 顶点2
     * @param weight
     * @return 是否设置成功，如果已经存在边，则返回false，否则返回true
     */
    boolean setEdge(int v1, int v2, int weight);

    /**
     * 移除k1, k2相连的边
     * @param v1
     * @param v2
     * @return 是否删除成功，如果原本就没有关系，返回false，如果删除成功返回true
     */
    boolean removeEdge(int v1, int v2);

    /**
     * 获取边
     * @param v1 顶点1索引
     * @param v2 顶点2索引
     * @return 如果两个顶点有边，若没有返回null
     */
    Edge<T> getEdge(int v1, int v2);

    /**
     * 图的边总和
     * @return 图的边总和
     */
    int getSizeOfEdges();

    /**
     * 从v开始图的遍历
     * @param v 顶点索引
     */
    void showGraphBy(int v);

    /**
     * 遍历图的所有顶点
     */
    void showGraph();

    /**
     * 判断图是否为空
     * @return
     */
    boolean isEmpty();

}
