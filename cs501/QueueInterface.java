package cs501;

public interface QueueInterface<E> {
    int size();
    boolean isEmpty();
    void enqueue(E e);
    E first();
    E dequeue();
}

class ArrayQueue<E> implements QueueInterface<E> {
    private static final int CAPACITY = 10;
    private E[] data;
    private int f = 0;
    private int sz = 0;

    public static void main(String[] args) {
        ArrayQueue<String> queue = new ArrayQueue<>();
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        queue.enqueue("five");
        queue.enqueue("six");
        queue.enqueue("seven");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("eight");
        queue.enqueue("nine");
        queue.enqueue("ten");
        queue.enqueue("eleven");
        queue.enqueue("twelve");
        queue.enqueue("thirteen");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        queue.enqueue("five");
        queue.enqueue("six");

    }

    public ArrayQueue() {
        this(CAPACITY);
    }

    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public int size() {
        return sz;
    }

    public boolean isEmpty() {
        return sz == 0;
    }

    public void enqueue(E e) throws IllegalStateException {
        if (sz == data.length) {
            throw new IllegalStateException("Stack is full.");
        }
        int avail = (f + sz) % data.length;
        data[avail] = e;
        sz++;

    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return data[f];
    }

    public E dequeue() {
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
