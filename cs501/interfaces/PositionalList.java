package cs501.interfaces;

import java.util.Iterator;

public interface PositionalList<E> {
    /**Returns number of elements in list */
    int size();
    /**Returns true if list contains zero elements */
    boolean isEmpty();
    /**Returns the first position in the list */
    Position<E> first();
    /**Returns the last position in the list */
    Position<E> last();
    /**Returns the Position immediately before Position p */
    Position<E> before(Position<E> p) throws IllegalArgumentException;
    /**Returns the Position immediately after Position p */
    Position<E> after(Position<E> p) throws IllegalArgumentException ;
    /**Inserts element e at front of list */
    Position<E> addFirst(E e);
    /**Inserts element e at back of list */
    Position<E> addLast(E e);
    
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;
    E set(Position<E> p, E e) throws IllegalArgumentException;
    E remove(Position<E> p) throws IllegalArgumentException;
    Iterable <Position<E>> positions();
    Iterator<E> iterator();


    
}
