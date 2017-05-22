package lab8;

import com.sun.corba.se.impl.io.ValueUtility;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.security.PublicKey;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by mwang on 5/21/17.
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>, Iterable<K> {
    public BSTTree root;
    public int size;

    private class BSTTree {

        private K key;
        private V value;
        private BSTTree left;
        private BSTTree right;

        public BSTTree(K key, V value, BSTTree left, BSTTree right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
        public BSTTree(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public BSTMap() {
        root = null;
        size = 0;
    }

    public BSTMap(K key, V value) {
        root = new BSTTree(key, value, null, null);
        size = 1;
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    public class KeyIterator implements Iterator<K> {

        public boolean hasNext() {
            return false;
        }

        public K next() {
            return null;
        }

    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return containsKey(key, root);
    }

    public boolean containsKey(K key, BSTTree t) {
        if (t == null) {
            return false;
        }
        else if (t.key.equals(key)) {
            return true;
        }
        else if (key.compareTo(t.key) > 0) {
            return containsKey(key, t.right);
        }
        else {
            return containsKey(key, t.left);
        }
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (! containsKey(key)) {
            return null;
        }
        else {
            return get(key, root);
        }
    }

    public V get(K key, BSTTree t) {
        if (t.key.equals(key)) {
            return t.value;
        }
        else if (key.compareTo(t.key) > 0) {
            return get(key, t.right);
        }
        else {
            return get(key, t.left);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    public BSTTree put(K key, V value, BSTTree t) {
        if (t == null) {
            size += 1;
            return new BSTTree(key, value);
        }
        if (t.key.equals(key)) {
            t.value = value;
        }
        else if (key.compareTo(t.key) > 0) {
            t.right = put(key, value, t.right);
        }
        else {
            t.left = put(key, value, t.left);
        }
        return t;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        HashSet<K> keySet = new HashSet<K>();
        for (K key: this) {
            keySet.add(key);
        }
        return keySet;


    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        printInOrder(root);
    }
    public void printInOrder(BSTTree t) {
        if (t == null) return;
        printInOrder(t.left);
        System.out.println(t.key);
        printInOrder(t.right);
    }
}
