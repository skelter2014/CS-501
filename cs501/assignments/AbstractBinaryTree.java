

import java.util.ArrayList;
import java.util.List;

import cs501.interfaces.BinaryTreeInterface;
import cs501.interfaces.Position;

public abstract class AbstractBinaryTree<E>
    extends AbstractTree<E>
    implements BinaryTreeInterface<E> {

  /** Returns the Position of p's sibling or null if not exist */
  public Position<E> sibling(Position<E> p) {
    Position<E> parent = parent(p);

    if (parent == null)
      return null;

    if (p == left(parent)) {
      return right(parent);
    } else {
      return left(parent);
    }
  }

  /** Returns the number of children of Position p */
  public int numChildren(Position<E> p) {
    int count = 0;
    if (left(p) != null)
      count++;
    if (right(p) != null)
      count++;

    return count;
  }

  public Iterable<Position<E>> children(Position<E> p) {
    List<Position<E>> snapshot = new ArrayList<>(2);
    if (left(p) != null)
      snapshot.add(left(p));
    if (right(p) != null)
      snapshot.add(right(p));

    return snapshot;
  }

  /** Adds positions of the subtree rooted at Position p to the given snapshot. */
  private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    if (left(p) != null)
      inorderSubtree(left(p), snapshot);
    snapshot.add(p);
    if (right(p) != null)
      inorderSubtree(right(p), snapshot);
  }

  /** Adds positions of the subtree rooted at Position p to the given snapshot. */
  public Iterable<Position<E>> inorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
      inorderSubtree(root(), snapshot); // fill the snapshot recursively
    return snapshot;
  }
}
