package lab9;

import java.util.*;

/**
 * Created by mwang on 5/22/17.
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    public static final Double DEFAULT_LF = 0.75;
    public static final int DEFAULT_SIZE = 2;

    public double loadFactor;
    public int bucketSize;
    public ArrayList<LinkedList<Entry>> buckets;
    public int size;

    public MyHashMap() {
        this(DEFAULT_SIZE, DEFAULT_LF);
    }

    public MyHashMap(int initiaSize) {
        this(initiaSize, DEFAULT_LF);

    }

    public MyHashMap(int initiaSize, double loadFactor) {
        buckets = new ArrayList<>();
        for (int i = 0; i < initiaSize; i++) {
            buckets.add(new LinkedList<>());
        }
        bucketSize = initiaSize;
        this.loadFactor = loadFactor;
        size = 0;
    }


    public class Entry {
        public K key;
        public V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    public class KeyIterator implements Iterator<K> {
        int keyIndex;
        ArrayList<Entry> allKeys = new ArrayList<>();
        public KeyIterator() {
            keyIndex = -1;

            for (int i = 0; i < bucketSize; i++) {
                for (Entry e: buckets.get(i)) {
                    allKeys.add(e);
                }
            }
        }

        public boolean hasNext() {
            return keyIndex < size - 1;
        }

        public K next() {
            keyIndex += 1;
            return allKeys.get(keyIndex).key;
        }
    }


    @Override
    public void clear() {
        buckets = new ArrayList<>();
        for (int i = 0; i < bucketSize; i++) {
            buckets.add(new LinkedList<Entry>());
        }
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int getBucketNum(K key) {
        int hashCode = key.hashCode();
        int bucketNum = (hashCode & 0x7FFFFFFF) % bucketSize;
        return bucketNum;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int bucketNum = getBucketNum(key);
        LinkedList<Entry> temp = buckets.get(bucketNum);
        for (Entry e: temp) {
            if (e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    public void reSize() {
        bucketSize = bucketSize * 2;
        ArrayList<LinkedList<Entry>> bucketsTemp = new ArrayList<>();

        for (int i = 0; i < bucketSize; i++) {
            bucketsTemp.add(new LinkedList<Entry>());
        }

        for (int j = 0; j < bucketSize / 2; j++) {
            for (Entry e: buckets.get(j)) {
                int newHash = getBucketNum(e.key);
                bucketsTemp.get(newHash).add(e);
            }
        }
        buckets = bucketsTemp;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        int bucketNum = getBucketNum(key);
        LinkedList<Entry> temp = buckets.get(bucketNum);
        for (Entry e: temp) {
            if (e.key.equals(key)) {
                e.value = value;
                return;
            }
        }
        temp.add(new Entry(key, value));
        size += 1;

        if (size / bucketSize > loadFactor) {
            reSize();
        }
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();
        for (K k: this) {
            keySet.add(k);
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


}
