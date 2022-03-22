/**
 * @author : wendi
 * @description: TODO
 * @date :2022/3/22 16:22
 */
public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int capacity;
    private int start;
    private int end;
    private double ratio = (double) size / items.length;


    public ArrayDeque() {
        size = 0;
        items = (T []) new Object[8];
        capacity = 8;
        start = -1;
        end = 0;
        size = 0;
    }

    public void addFirst(T item) {
        start = (start + capacity) % capacity;
        items[start] =item;
        if ( ratio <= 0.25 && capacity > 16) {
            resize(size * 4);
        }
    }

    private void resize(int new_size) {
        T[] a = (T []) new Object[new_size];
        for (int i=0; i<size;++i){
            a[(i+capacity)%capacity]=items[(start+i)%capacity];
        }
        capacity = new_size;
        items = a;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[end] = item;
        end = (end + 1) % capacity;
        size = size+1;
        if ( ratio <= 0.25 && capacity > 16) {
            resize(size * 4);
        }
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int iterator = start;
        while (iterator != end) {
            System.out.print(items[iterator] + " ");
            iterator = (iterator + 1) % capacity;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size = size -1;
        T num = items[start];
        start = (start + 1) % capacity;
        if ( ratio <= 0.25 && capacity > 16) {
            resize(size * 4);
        }
        return num;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size = size -1;
        T num = items[end];
        end = (end - 1 + capacity) % capacity;
        if ( ratio <= 0.25 && capacity > 16) {
            resize(size * 4);
        }
        return num;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(index + start) % capacity];
    }
}
