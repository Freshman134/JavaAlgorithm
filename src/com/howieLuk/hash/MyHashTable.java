package com.howieLuk.hash;

import com.howieLuk.linkedList.DoubleLinkedList;

import java.util.List;
import java.util.Random;


/**
 * @Deacription 散列表
 * @Author HowieLuk
 * @Date 2024/1/9 21:56
 * @Version 1.0
 **/
public class MyHashTable<K, V> {

    private static final int INIT_SIZE = 1 << 4;

    private static class Entry<K, V> {
        K key;
        V val;
        Entry<K, V> next;

        public Entry(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public Entry(K key, V val, Entry next) {
            this(key, val);
            this.next = next;
        }

        public void add(Entry<K, V> entry) {
            if (next == null) {
                next = entry;
            } else {
                next.add(entry);
            }
        }

    }

    private Entry<K, V>[] container = new Entry[INIT_SIZE];
    private int count = 0;


    public void put(K key, V val) {
        int ind = simpleHash(key);
        if (container[ind] == null) {
            container[ind] = new Entry(key, val);
        } else {
            container[ind].add(new Entry(key, val));
        }
        count++;
    }

    public V remove(K key) {
        int ind = simpleHash(key);
        Entry<K, V> prev = container[ind];
        if (prev == null) {
            return null;
        }
        if (prev.key.equals(key)) {
            container[ind] = prev.next;
            count--;
            return prev.val;
        }
        while (prev != null) {
            if (prev.next != null && key.equals(prev.next.key)) {
                return removeEntry(prev).val;
            }
            prev = prev.next;
        }
        return null;
    }

    public V get(K key) {
        int ind = simpleHash(key);
        Entry<K, V> e = container[ind];
        while (e != null) {
            if (e.key.equals(key)) {
                break;
            }
            e = e.next;
        }
        return e == null ? null : e.val;
    }

    public void printKeyValues() {
        for (Entry<K, V> entry : container) {
            if (entry != null) {
                while (entry != null) {
                    System.out.print(entry.key + "," + entry.val + "\t");
                    entry = entry.next;
                }
                System.out.println();
            }
        }
        System.out.println("size:" + count);
    }

    private int simpleHash(K key) {
        return key.hashCode() % container.length;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private Entry<K, V> removeEntry(Entry<K, V> prevEntry) {
        Entry<K, V> ret = prevEntry.next;
        prevEntry.next = ret.next;
        count--;
        return ret;
    }

    public static void main(String[] args) {
        MyHashTable<Integer, Integer> table = new MyHashTable<>();
        for (int i = 0; i < 16; i++) {
            table.put(i, i);
        }
        table.printKeyValues();
        int i = 0;
        while (!table.isEmpty()) {
            table.remove(i);
            i++;
        }
        table.printKeyValues();
        Random random = new Random();
        List<Integer> keys = new DoubleLinkedList<>();
        for (int j = 0; j < 2000; j++) {
            int k = random.nextInt(2000);
            if (table.get(k) == null) {
                table.put(k, k);
                keys.add(k);
            }
        }
        System.out.println("inserted:" + keys.size() + "\t" + table.count);
        List<Integer> removeKeys = new DoubleLinkedList<>();
        for (int j = 0; j < 100; j++) {
            int removeKey = random.nextInt(2000);
            if (table.get(removeKey) != null) {
                table.remove(removeKey);
                removeKeys.add(removeKey);
                if (table.get(removeKey) != null) {
                    throw new RuntimeException("delete err");
                }
            }
        }
        System.out.println("deleted:" + removeKeys.size() + "\t" + table.count);
    }
}
