package cs501;

import cs501.LinkedBinaryTree.Node;
import cs501.interfaces.Position;
import cs501.interfaces.QueueInterface;
import cs501.interfaces.TreeInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractTree<E> implements TreeInterface<E> {

  public boolean isEmpty() {
    return size() == 0;
  }

  public boolean isExternal(Position<E> p) throws IllegalArgumentException {
    return numChildren(p) == 0;
  }

  public boolean isInternal(Position<E> p) throws IllegalArgumentException {
    return numChildren(p) > 0;
  }

  public boolean isRoot(Position<E> p) throws IllegalArgumentException {
    return p == root();
  }

  /**Returns the height of subtree at Position p */
  public int height(Position<E> p) {
    int h = 0;
    for (Position<E> c : children(p)) {
      h = Math.max(h, 1 + height(c));
    }
    return h;
  }

  private int heightBad() {
    int h = 0;
    for (Position<E> p : positions()) {
      if (isExternal(p)) {
        h = Math.max(h, depth(p));
      }
    }
    return h;
  }

  /**Returns the number of levels separating Position p from root */
  public int depth(Position<E> p) {
    if (isRoot(p)) {
      return 0;
    } else {
      return 1 + depth(p);
    }
  }

  public Iterator<E> iterator() {
    return new ElementIterator();
  }

  private class ElementIterator implements Iterator<E> {

    Iterator<Position<E>> posIterator = positions().iterator();

    public boolean hasNext() {
      return posIterator.hasNext();
    }

    public E next() {
      return posIterator.next().getElement();
    }

    public void remove() {
      posIterator.remove();
    }
  }

  public Iterable<Position<E>> positions() {
    return preorder();
  }

  private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    snapshot.add(p); //Parent then Left Right
    for (Position<E> c : children(p)) {
      preorderSubtree(c, snapshot);
    }
  }

  public Iterable<Position<E>> preorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty()) {
      preorderSubtree(root(), snapshot);
    }
    return snapshot;
  }

  private void postOrderSubtree(Position<E> p, List<Position<E>> snapshot) {
    for (Position<E> c : children(p)) {
      postOrderSubtree(c, snapshot);
    }
    snapshot.add(p); //Children 1st, then parent.
  }

  public Iterable<Position<E>> postorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty()) {
      postOrderSubtree(root(), snapshot);
    }
    return snapshot;
  }

  private void printFringe(LinkedListQueue<Position<E>> queue) {
    SinglyLinkedList<Position<E>> list = queue.getList();

    cs501.Node<Position<E>> pos = list.first();
    StringBuffer buf = new StringBuffer();

    buf.append("< ");

    while (pos != null) {
      buf.append(pos.getElement().getElement() + " ");
      pos = pos.getNext();
    }
    buf.append(">");
    while (buf.length() < 16) {
      buf.append(" ");
    }
    System.out.print(buf.toString());
  }

  private void printSnapShot(List<Position<E>> list) {
    System.out.print("[ ");
    for (Position<E> e : list) {
      System.out.print(e.getElement() + " ");
    }
    System.out.println("]");
  }

  public Iterable<Position<E>> breadthFirst() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty()) {
      QueueInterface<Position<E>> fringe = new LinkedListQueue<>();

      fringe.enqueue(root());
      printFringe((LinkedListQueue<Position<E>>) fringe);
      printSnapShot(snapshot);
      while (!fringe.isEmpty()) {
        Position<E> p = fringe.dequeue();
        snapshot.add(p);

        printFringe((LinkedListQueue<Position<E>>) fringe);
        printSnapShot(snapshot);

        for (Position<E> c : children(p)) {
          fringe.enqueue(c);
          printFringe((LinkedListQueue<Position<E>>) fringe);
          printSnapShot(snapshot);
        }
      }
      System.out.println("--------------");
    }
    return snapshot;
  }
}
