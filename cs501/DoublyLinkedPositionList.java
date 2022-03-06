package cs501;

import cs501.interfaces.Position;
import cs501.interfaces.PositionalList;

public class DoublyLinkedPositionList<E> implements PositionalList<E> {

  private Node<E> header;
  private Node<E> trailer;
  private int size = 0;

  public static void main(String[] args) {
    DoublyLinkedPositionList<String> list = new DoublyLinkedPositionList<>();
    Position<String> p = null;

    for (int i = 1; i < 16; i++) {
      Node<String> node = (Node<String>) p;
      if (i % 2 == 0) {
        list.addBetween(Integer.toString(i), node, node.next);
      } else if (i % 3 == 0) {
        p = list.addLast(Integer.toString(i));
      } else {
        p = list.addFirst(Integer.toString(i));
      }
    }

    Node<String> pointer = (Node<String>) list.first();

    while (pointer.next != null) {
      System.out.println(pointer.element);
      pointer = pointer.next;
    }
    System.out.println("P: " + p.getElement());
    list.remove(p);
    try {
      list.addAfter(p, "test");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  public DoublyLinkedPositionList() {
    header = new Node<>(null, null, null);
    trailer = new Node<>(null, header, null);
    header.setNext(trailer);
  }

  //#region private utilities
  private Node<E> validate(Position<E> p) throws IllegalArgumentException {
    if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");

    Node<E> node = (Node<E>) p;
    if (node.getNext() == null) {
      throw new IllegalArgumentException("p is no longer in the list.");
    }
    return node;
  }

  private Position<E> position(Node<E> node) {
    if (node == header || node == trailer) {
      return null;
    }
    return node;
  }

  private Position<E> addBetween(E e, Node<E> before, Node<E> after) {
    Node<E> newest = new Node<>(e, before, after);
    before.setNext(newest);
    after.setPrev(newest);
    size++;
    return newest;
  }

  //#region

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public Position<E> first() {
    return position(header.getNext());
  }

  public Position<E> last() {
    return position(trailer.getPrev());
  }

  /**Returns the Position immediately before Position P */
  public Position<E> before(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return position(node.getPrev());
  }

  /**Returns the Position immediatly after Position p */
  public Position<E> after(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return position(node.getNext());
  }

  /**Inserts element at from of linked list and returns Position */
  public Position<E> addFirst(E e) {
    return addBetween(e, header, header.next);
  }

  /**Adds element to end of list and returns Position */
  public Position<E> addLast(E e) {
    return addBetween(e, trailer.prev, trailer);
  }

  /**Inserts element immediately before Position p */
  public Position<E> addBefore(Position<E> p, E e)
    throws IllegalArgumentException {
    Node<E> node = validate(p);
    return addBetween(e, node.prev, node);
  }

  /**Inserts element immediately after Position p */
  public Position<E> addAfter(Position<E> p, E e)
    throws IllegalArgumentException {
    Node<E> node = validate(p);
    return addBetween(e, node, node.next);
  }

  /**Replaces the element stored at Position p */
  public E set(Position<E> p, E e) {
    Node<E> node = validate(p);
    E temp = node.element; //save current value.
    node.setElement(e);
    return temp; //return old value
  }

  /**Removes the element at Position p and invalidates it */
  public E remove(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    Node<E> before = node.prev;
    Node<E> after = node.next;

    before.next = after;
    after.prev = before;
    size--;
    E temp = node.element;
    node.element = null;
    node.next = null;
    node.prev = null;
    return temp;
  }

  private static class Node<E> implements Position<E> {

    private E element;
    private Node<E> next;
    private Node<E> prev;

    public Node(E e, Node<E> p, Node<E> n) {
      element = e;
      prev = p;
      next = n;
    }

    public E getElement() throws IllegalStateException {
      if (next == null) {
        throw new IllegalStateException("Position is no longer valid.");
      }
      return element;
    }

    public void setElement(E e) {
      element = e;
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
}
