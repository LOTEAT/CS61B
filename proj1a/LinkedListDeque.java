/**
 * @author : wendi
 * @description: TODO
 * @date :2022/3/20 20:33
 */
public class LinkedListDeque<T> {
    private DequeNode first;
    private DequeNode last;
    private int size;

    public class DequeNode {
        private DequeNode prev;
        private T item;
        private DequeNode next;

        public DequeNode() {
            prev = null;
            next = null;
        }

        public DequeNode(T x) {
            item = x;
            prev = null;
            next = null;
        }
    }

    public LinkedListDeque() {
        size = 0;
        first = new DequeNode();
        last = new DequeNode();
        first.next = last;
        last.prev = first;
    }


    public void addFirst(T item) {
        size += 1;
        DequeNode cur = new DequeNode(item);
        DequeNode temp = first.next;
        first.next = cur;
        cur.next = temp;
        cur.prev = first;
        temp.prev = cur;

    }

    public void addLast(T item) {
        size += 1;
        DequeNode temp = last.prev;
        DequeNode cur = new DequeNode(item);
        cur.next = last;
        cur.prev = temp;
        temp.next = cur;
        last.prev = cur;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        DequeNode p = first.next;
        while (p.next != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (first.next == last) {
            return null;
        }
        size -= 1;
        DequeNode rmv = first.next;
        DequeNode temp = rmv.next;
        first.next = temp;
        temp.prev = first;
        return rmv.item;
    }


    public T removeLast() {
        if (last.prev == first) {
            return null;
        }
        size -= 1;
        DequeNode rmv = last.prev;
        DequeNode temp = rmv.prev;
        temp.next = last;
        last.prev = temp;
        return rmv.item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        DequeNode p = first.next;
        for (int i = 0; i < index; ++i) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index){
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(index, first.next);
    }

    private T getRecursiveHelper(int index, DequeNode node){
        if (index == 0){
            return node.item;
        }
        return getRecursiveHelper(index-1, node.next);
    }
}
