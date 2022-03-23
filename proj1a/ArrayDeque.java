/**
 * My implementation of ArrayDeque
 *
 * @param <T> generic
 */
public class ArrayDeque<T> {

    // the increase refactor
    private static final double INCREASE = 2;
    // the decrease refactor
    private static final double DECREASE = 0.5;

    // the length of ArrayDeque
    private int size;

    // the capacity of ArrayDeque
    private int capacity;

    // core data structure
    private T item[];

    // start & end
    private int start;
    private int end;


    // non-args constructor
    public ArrayDeque() {
        // generic array
        item = (T[]) new Object[8];
        capacity = 8;
        start = 0;
        end = 0;
        size = 0;
    }

    // resize capacity
    private void resize(double refactor) {
        int capacityCopy = capacity;
        capacity = (int) (capacity * refactor);
        T[] newItem = (T[]) new Object[capacity];
        boolean is_cross = end <= start;
        if (is_cross) {
            System.arraycopy(item, start, newItem, 0, capacityCopy - start);
            System.arraycopy(item, 0, newItem, capacityCopy - start, end);
        } else {
            System.arraycopy(item, start, newItem, 0, end - start);
        }
        item = newItem;
        start = 0;
        end = size;
    }

    // add an item of type T to the front of the deque
    public void addFirst(T item) {
        if (size == capacity)
            resize(INCREASE);
        size += 1;
        start = (start + capacity - 1) % capacity;
        this.item[start] = item;
    }

    // add an item of type T to the back of the deque
    public void addLast(T item) {
        if (size == capacity)
            resize(INCREASE);
        size += 1;
        this.item[end] = item;
        end = (end + 1) % capacity;
    }

    // return true if deque is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items in the deque
    public int size() {
        return size;
    }

    // print the items in the deque from first to last, separated by a space
    public void printDeque() {
        int iterator = start;
        int num_count = 0;
        while (num_count != size) {
            System.out.print(item[iterator] + " ");
            iterator = (iterator + 1) % capacity;
            num_count += 1;
        }
    }

    // remove and return the item at the front of the deque. If no such item exists, returns null
    public T removeFirst() {
        if (isEmpty())
            return null;
        size -= 1;
        T first = item[start];
        start = (start + 1) % capacity;
        if ((double) size / capacity < 0.25 && capacity >= 16)
            resize(DECREASE);
        return first;
    }

    // remove and return the item at the back of the deque. If no such item exists, returns null
    public T removeLast() {
        if (isEmpty())
            return null;
        size -= 1;
        end = (end - 1 + capacity) % capacity;
        T last = item[end];
        if ((double) size / capacity < 0.25 && capacity >= 16)
            resize(DECREASE);
        return last;
    }

    // get the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. must not alter the deque!
    public T get(int index) {
        if (index >= size)
            return null;
        return item[(index + start) % capacity];
    }


}
