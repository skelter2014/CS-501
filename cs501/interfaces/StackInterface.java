package cs501.interfaces;

public interface StackInterface<E> {
    int size();
    boolean isEmpty();
    void push(E e);
    E top();
    E pop();
}