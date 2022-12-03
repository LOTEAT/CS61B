public interface Deque<T> {

    // add an item of type T to the front of the deque
    public void addFirst(T value);
    // add an item of type T to the back of the deque
    public void addLast(T value);

    // return true if deque is empty, false otherwise
    public boolean isEmpty();

    // return the number of items in the deque
    public int size();

    // print the items in the deque from first to last,
    // separated by a space
    public void printDeque();

    // remove and return the item at the front of the deque.
    // If no such item exists, returns null
    public T removeFirst();

    // remove and return the item at the back of the deque.
    // If no such item exists, returns null
    public T removeLast();

    // get the item at the given index, where 0 is the front,
    // 1 is the next item, and so forth.
    // If no such item exists, returns null.
    // must not alter the deque!
    public T get(int index);

}
