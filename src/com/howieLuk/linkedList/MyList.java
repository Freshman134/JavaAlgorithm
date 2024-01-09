package com.howieLuk.linkedList;

import java.util.List;

public interface MyList<T> extends List<T> {

//    void add(T t);

    T get(int i);

//    int indexOf(T t);

    void insert(int i, T t);

//    void set(int i, T t);

    T remove(int i);

//    T remove(T t);

    void printList();

    MyList<T> reverse();

    int size();

    boolean isEmpty();

}
