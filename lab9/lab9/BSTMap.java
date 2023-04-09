package lab9;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */
    private Set<K> set;

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
        size = 0;
        set = new HashSet<K>();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }

        int c = key.compareTo(p.key);

        if (c > 0) {
            return getHelper(key, p.right);
        }
        else if (c < 0) {
            return getHelper(key, p.left);
        }
        else {
            return p.value;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            return new Node(key, value);
        }
        int d = key.compareTo(p.key);
        if (d < 0) {
            p.left = putHelper(key, value, p.left);
        }
        else if (d > 0) {
            p.right = putHelper(key, value, p.right);
        }
        else {
            p.value = value;
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
        size++;
        set.add(key);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return set;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        return removeHelper(key, root).value;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V v = get(key);
        if (value == v) {
            remove(key);
            return v;
        }
        return null;
    }

    public Node removeHelper(K key, Node p) {
        int c = key.compareTo(p.key);
        if (c < 0) {
            removeHelper(key, p.left);
        }
        else if (c > 0) {
            removeHelper(key, p.right);
        }
        else {
            if (p.left == null) {
                return p.right;
            }
            else if (p.right == null) {
                return p.left;
            }
            else {
                Node T = p.right;
                while (T.left != null) {
                    T = T.left;
                }
                T.left = removeMin(p.left);
                T.right = p.right;
            }
        }
        size--;
        set.remove(p);
        return p;
    }

    public Node removeMin(Node p) {
        if (p.left == null) {
            return p.right;
        } else {
            p.left = removeMin(p.left);
        }
        return p;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}