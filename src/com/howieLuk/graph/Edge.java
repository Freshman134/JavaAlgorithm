package com.howieLuk.graph;

public interface Edge<K, V> {

    boolean isDiEdge();

    int getWeight();

    Vertex<K, V> getFrom();

    Vertex<K, V> getTo();
}
