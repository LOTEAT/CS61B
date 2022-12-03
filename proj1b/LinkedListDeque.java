/**
 * My implementation of LinkedListDeque
 *
 * @param <T> generic
 */
public class LinkedListDeque<T> implements Deque<T> {

    // the length of deque
    private int size;
    private DequeNode sentF;
    private DequeNode sentB;

    // node
    public class DequeNode {
        DequeNode pre;
        DequeNode next;
        T item;

        public DequeNode() {
            pre = null;
            next = null;
        }

        public DequeNode(T item) {
            this.item = item;
            pre = null;
            next = null;
        }
    }

    // non-args constructor
    public LinkedListDeque() {
        sentF = new DequeNode();
        sentB = new DequeNode();
        sentF.next = sentB;
        sentB.pre = sentF;
        size = 0;
    }

    // add an item of type T to the front of the deque
    @Override
    public void addFirst(T item) {
        size += 1;
        DequeNode node = new DequeNode(item);
        // copy the first node
        DequeNode second = sentF.next;
        // change link relations
        sentF.next = node;
        node.pre = sentF;
        node.next = second;
        second.pre = node;
    }

    // add an item of type T to the back of the deque
    @Override
    public void addLast(T item) {
        size += 1;
        DequeNode node = new DequeNode(item);
        // copy the second last node
        DequeNode secondLast = sentB.pre;
        sentB.pre = node;
        node.pre = secondLast;
        node.next = sentB;
        secondLast.next = node;
    }

    // return true if deque is empty, false otherwise
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items in the deque
    @Override
    public int size() {
        return size;
    }

    // print the items in the deque from first to last,
    // separated by a space
    @Override
    public void printDeque() {
        DequeNode iterator = sentF.next;
        while (iterator.next != null) {
            System.out.print(iterator.item + " ");
            iterator = iterator.next;
        }
    }

    // remove and return the item at the front of the deque.
    // If no such item exists, returns null
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        DequeNode first = sentF.next;
        sentF.next = first.next;
        first.next.pre = sentF;
        size -= 1;
        return first.item;
    }

    // remove and return the item at the back of the deque.
    // If no such item exists, returns null
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        DequeNode last = sentB.pre;
        sentB.pre = last.pre;
        last.pre.next = sentB;
        size -= 1;
        return last.item;
    }

    // get the item at the given index, where 0 is the front,
    // 1 is the next item, and so forth.
    // If no such item exists, returns null.
    // must not alter the deque!
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int itCount = 0;
        DequeNode iterator = sentF.next;
        while (itCount != index) {
            iterator = iterator.next;
            itCount += 1;
        }
        return iterator.item;
    }

    // recursive
    private T getRecursiveHelper(int index, DequeNode first) {
        if (index == 0) {
            return first.item;
        }
        return getRecursiveHelper(index - 1, first.next);
    }

    // recursive get
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentF.next);
    }
}
