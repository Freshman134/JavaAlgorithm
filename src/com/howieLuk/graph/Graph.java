package com.howieLuk.graph;

import java.util.Collection;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/19 20:16
 * @Version 1.0
 **/
public interface Graph<K, V> {
    /**
     * 添加定点
     * @param k 顶点key
     * @param v 顶点值
     */
    void add(K k, V v);

    /**
     * 获取顶点集合
     * @return
     */
    Collection<K> getVerticesKey();

    /**
     * 获取定点个数
     * @return
     */
    int getSizeOfVertices();

    /**
     * 设置边
     * @param k1
     * @param k2
     * @param weight
     */
    void setEdge(K k1, K k2, int weight);

    /**
     * 获取该顶点的所有边
     * @param k 顶点key
     * @return 边的集合
     */
    Collection<Edge<K, V>> getEdges(K k);

    /**
     * 移除k1, k2相连的边
     * @param k1
     * @param k2
     * @return 是否删除成功，如果原本就没有关系，返回false，如果删除成功返回true
     */
    boolean removeEdge(K k1, K k2);

    /**
     * 图的边总和
     * @return 图的边总和
     */
    int getSizeOfEdges();

    /**
     * 从k开始图的遍历
     * @param k
     */
    void showGraphBy(K k);

    /**
     * 判断图是否为空
     * @return
     */
    boolean isEmpty();

}
