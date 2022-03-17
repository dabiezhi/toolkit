package com.bloom.bloompoi.core.utils;

/**
 * Pair
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 12:10
 */
public class Pair<K, V> {

    private K k;
    private V v;

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }
}
