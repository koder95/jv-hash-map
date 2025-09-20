package core.basesyntax;

import java.util.Objects;

public class MyHashMap<K, V> implements MyMap<K, V> {

    @Override
    public void put(K key, V value) {

    }

    @Override
    public V getValue(K key) {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    private static class Node<K, V> {
        private final int hashcode;
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(K key) {
            this.key = key;
            this.hashcode = Objects.hashCode(key);
        }
    }
}
