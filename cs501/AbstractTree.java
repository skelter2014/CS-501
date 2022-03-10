package cs501;

import cs501.interfaces.Position;
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
  private int height(Position<E> p) {
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

  public Iterator iterator() {
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
    snapshot.add(p);
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
    snapshot.add(p);
  }
  public Iterable<Position<E>> postorder(){
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty()){
      postOrderSubtree(root(), snapshot);
    }
    return snapshot;

  }
}
