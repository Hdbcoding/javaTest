package LinkedHash;

import java.util.ArrayList;

//Next big improvement, non-fixed HashSet.
//for that, need to keep track of capacity, and as the table starts to get full, expand
//when expanding, create a new Arr<Node> of a bigger size and copy everything over
public class FixedLinkedHashSet<K, V> {
    private ArrayList<HashNode<K, V>> set;

    public FixedLinkedHashSet(int capacity) {
        set = new ArrayList<>(capacity);
        set.ensureCapacity(capacity);
        for (int i = 0; i < capacity; i++) set.add(null);
    }

    public void put(K key, V val) {
        var node = getNode(key);
        if (node != null) {
            node.value = val;
            return;
        }

        node = new HashNode(key, val);
        int idx = getIndex(key);
        if (set.get(idx) != null) {
            node.next = set.get(idx);
            node.next.prev = node;
        }
        set.set(idx, node);
    }

    public V get(K key){
        if (key == null) return null;
        var node = getNode(key);
        return node == null ? null : node.value;
    }

    public void remove(K key){
        var node = getNode(key);
        if (node == null) return;

        if (node.prev != null) node.prev.next = node.next;
        //if node.prev == null, node is the head at a hashTable index
        else set.set(getIndex(key), node.next);

        if (node.next != null) node.next.prev = node.prev;
    }

    private HashNode<K, V> getNode(K key) {
        int idx = getIndex(key);
        var node = set.get(idx);
        while (node != null) {
            if (node.key == key) break;
            node = node.next;
        }
        return node;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % set.size());
    }


    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;
        private HashNode<K, V> prev;

        private HashNode(K _key, V _value) {
            key = _key;
            value = _value;
        }
    }
}
