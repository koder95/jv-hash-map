package core.basesyntax;

import java.util.Objects;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private static <K, V> Node<K, V> getNode(Node<K, V>[] array, K key) {
        Node<K, V> node = array[Math.abs(Objects.hashCode(key)) % array.length];
        while (node != null) {
            if (Objects.equals(node.key, key)) {
                return node;
            }
            node = node.next;
        }
        return node;
    }

    private static <K, V> void addLast(Node<K, V> first, Node<K, V> node) {
        if (first == null) {
            throw new IllegalArgumentException();
        }
        Node<K, V> prev = first;
        Node<K, V> next = first.next;
        while (next != null) {
            prev = next;
            next = next.next;
        }
        prev.next = node;
    }

    private static <K, V> int putInto(Node<K, V>[] array, K key, V value, int size) {
        Node<K, V> node = getNode(array, key);
        if (node == null) {
            int bucket = Math.abs(Objects.hashCode(key)) % array.length;
            node = new Node<>(key);
            node.value = value;
            Node<K, V> first = array[bucket];
            if (first == null) {
                array[bucket] = node;
            } else {
                addLast(first, node);
            }
            return size + 1;
        } else {
            node.value = value;
            return size;
        }
    }

    private static <K, V> Node<K, V>[] growUp(Node<K, V>[] nodes, int size, int newCapacity) {
        Node<K, V>[] newNodes = new Node[newCapacity];
        for (Node<K, V> node : nodes) {
            if (node == null) {
                continue;
            }
            Node<K, V> prev = node;
            while (prev != null) {
                putInto(newNodes, prev.key, prev.value, size);
                Node<K, V> next = prev.next;
                prev.next = null;
                prev = next;
            }
        }
        return newNodes;
    }
    
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private int size = 0;

    @Override
    public void put(K key, V value) {

    }

    @Override
    public V getValue(K key) {
        return null;
    }

    @Override
    public int getSize() {
        return size;
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
