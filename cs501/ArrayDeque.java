package cs501;

/**
 * Give a complete implementation of the Deque ADT using a fixed-capacity array,
 * so that each of the update methods runs in O(1) time.
 */
public class ArrayDeque<E> implements cs501.interfaces.DequeInterface<E> {
   
    private E[] data;
    private int f = 0;
    private int sz = 0;
    private final static int CAPACITY = 10;

    public static void main(String [] args){
        ArrayDeque<String> deque = new ArrayDeque<>();

        deque.addFirst("one");
        deque.addLast("two");
        deque.addFirst("three");
        deque.addLast("four");
        deque.addLast("five");
        deque.addLast("six");
        deque.addFirst("seven");
        deque.addFirst("eight");
        deque.removeFirst();
        deque.removeLast();
        deque.addLast("nine");
        deque.addLast("ten");
        deque.addFirst("eleven");
        deque.addLast("twelve");

        while(deque.isEmpty() == false){
            System.out.print(deque.removeFirst() + " ");
        }


    }

    public ArrayDeque() {
        this(ArrayDeque.CAPACITY);
    }

    public ArrayDeque(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public int size() {
        return sz;
    }

    public boolean isEmpty() {
        return sz == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return data[f];
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        int last = (f + sz) % data.length;
        return data[last];
    }

    public void addFirst(E e) {
        if (sz == data.length) {
            throw new IllegalStateException("Deque is full.");
        }

        f = (f - 1 + data.length) % data.length;
        data[f] = e;
        sz++;
    }

    public void addLast(E e) {
        if (sz == data.length) {
            throw new IllegalStateException("Deque is full.");
        }
        // This is the important part. The modulo wraps the
        int last = (f + sz) % data.length;
        data[last] = e;
        sz++;

    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        }

        int last = (f + sz -1) % data.length;
        E result = data[last];
        data[last] = null;
        sz--;
        return result;

    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E result = data[f];
        data[f] = null;
        f = (f + 1) % data.length;
        sz--;
        return result;

    }

}
