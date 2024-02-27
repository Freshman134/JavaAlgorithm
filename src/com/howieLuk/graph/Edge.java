package com.howieLuk.graph;

public interface Edge<T> {

    boolean isDiEdge();

    int getWeight();

    Vertex<T> getFrom();

    Vertex<T> getTo();
}
