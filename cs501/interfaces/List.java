package cs501.interfaces;

public interface List<E> extends Iterable {
    int size();
    boolean isEmpty();
    E get(int i);
    E set(int i, E e);
    void add(int i, E e );
    E remove (int i);
}
