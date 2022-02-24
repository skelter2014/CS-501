package cs501.interfaces;

public interface QueueInterface<E> {
    int size();
    boolean isEmpty();
    void enqueue(E e);
    E first();
    E dequeue();
}

