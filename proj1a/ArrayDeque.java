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

    private static final double INCREASE = 2;
    private static final double DECREASE = 0.5;


    public ArrayDeque() {
        size = 0;
        items = (T []) new Object[8];
        capacity = 8;
        start = 0;
        end = 0;
        size = 0;
    }

    private void resize(double refactor) {
        int capacityCopy = capacity;
        capacity = (int)(capacity*refactor);
        T[] a = (T []) new Object[capacity];
        boolean isCross = (end <= start);
        if (isCross) {
            System.arraycopy(items,start,a,0,capacityCopy-start);
            System.arraycopy(items,0,a,capacityCopy-start,end);
        }else {
            System.arraycopy(items,start,a,0,end-start);
        }
        items = a;
        start=0;
        end=size;
    }

    public void addFirst(T item) {
        if(size == capacity) {
            resize(INCREASE);
        }
        size = size+1;
        start = (start + capacity-1) % capacity;
        items[start] =item;
    }


    public void addLast(T item) {
        if (size == capacity) {
            resize(INCREASE);
        }
        items[end] = item;
        end = (end + 1) % capacity;
        size = size+1;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int iterator = start;
        int count = 0;
        while (count != size) {
            System.out.print(items[iterator] + " ");
            iterator = (iterator+1)%capacity;
            count = count+1;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size = size -1;
        T num = items[start];
        start = (start + 1) % capacity;
        if ( (double) size / capacity < 0.25 && capacity >= 16) {
            resize(DECREASE);
        }
        return num;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size = size -1;
        end = (end - 1 + capacity) % capacity;
        T num = items[end];
        if ( (double) size / capacity < 0.25 && capacity > 16) {
            resize(DECREASE);
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
