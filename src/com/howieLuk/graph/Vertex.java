package com.howieLuk.graph;

import java.util.Collection;

public interface Vertex<T> {

    T getVal();

    void setVal(T t);

    /**
     * 获取该顶点的所有边
     * @return 边的集合
     */
    Collection<Edge<T>> getRelateEdges();

}
