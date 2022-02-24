package cs501.interfaces;

public interface DequeInterface<E> {
    int size();
    boolean isEmpty();
    E first();
    E last();
    void addFirst(E e);
    void addLast(E e);
    E removeLast();
    E removeFirst();
    
}
