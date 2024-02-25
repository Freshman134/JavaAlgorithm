package com.howieLuk.graph;

public interface Vertex<K, V> {

    K getKey();

    V getVal();

    void setVal(V v);

}
