package cs501;

import cs501.interfaces.Position;
import cs501.interfaces.PositionalList;

public class DoublyLinkedPositionList<E> implements PositionalList<E> {

    // nested Node Class
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;

        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }
    }

    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    public Position<E> first() {
        // TODO Auto-generated method stub
        return null;
    }

    public Position<E> last() {
        // TODO Auto-generated method stub
        return null;
    }

    public Position<E> before(Position<E> p) {
        // TODO Auto-generated method stub
        return null;
    }

    public Position<E> after(Position<E> p) {
        // TODO Auto-generated method stub
        return null;
    }

    public Position<E> addFirst(E e) {
        // TODO Auto-generated method stub
        return null;
    }

    public Position<E> addLast(E e) {
        // TODO Auto-generated method stub
        return null;
    }

    public Position<E> addBefore(Position<E> p, E e) {
        // TODO Auto-generated method stub
        return null;
    }

    public Position<E> addAfter(Position<E> p, E e) {
        // TODO Auto-generated method stub
        return null;
    }

    public E set(Position<E> p, E e) {
        // TODO Auto-generated method stub
        return null;
    }

    public E remove(Position<E> p) {
        // TODO Auto-generated method stub
        return null;
    }

}
