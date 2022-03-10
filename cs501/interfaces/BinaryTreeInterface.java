package cs501.interfaces;

public interface BinaryTreeInterface<E> extends TreeInterface<E> {
  Position<E> left(Position<E> p) throws IllegalArgumentException;
  Position<E> right(Position<E> p) throws IllegalArgumentException;
  Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}
