package cs501;

import cs501.interfaces.Position;
import java.util.Iterator;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

  protected Node<E> root = null;
  private int size = 0;
  private int count = 0;
  private int level = 0;
  private int levelCounter = 0;

  /**
   *   1
   *  2 3
   * 4 5 6 7
   *
   *      A
   *   K     L
   *  M R   C D
   */

  public static void main(String[] args) {
    LinkedBinaryTree<Object> tree = new LinkedBinaryTree<>();

    Position<Object> A;
    Position<Object> K;
    Position<Object> L;
    Position<Object> M;
    Position<Object> R;
    Position<Object> C;
    Position<Object> D;
    Position<Object> P;
    Position<Object> Q;
    Position<Object> O;
    Position<Object> S;
    Position<Object> N;
    
/**
Pre order           -A- -K- [M R] [N O S] -L- [C] [D P Q]       (root, left, right)
inorder             [M R] -K- [S O N] -A- [C] -L- [P D Q]    (left, root, right)
 */

    tree.count++;
    tree.level = 0; // 2^0 = 1;
    A = tree.addRoot('A');// 
    K = tree.addLeft(A, 'K');// 
    L = tree.addRight(A, 'L');//
    M = tree.addLeft(K, 'M');// 
    N = tree.addRight(K,'N');
    C = tree.addLeft(L, 'C');// 
    D = tree.addRight(L, 'D');//
    R = tree.addRight(M, 'R');//
    O = tree.addLeft(N, 'O');//
    S = tree.addLeft(O,'S');
    P = tree.addLeft(D,'P');//
    Q = tree.addRight(D, 'Q');//

    /**
     *                                     A
     *                            K              L
     *                      M         N      C      D 
     *                        R     O             P   Q 
     *                            S
     */

    // lefty = tree.addLeft(p3, 8);

    printTraversals(tree);
  }

  private void addNode(Position<Object> p, int i, LinkedBinaryTree<Object> tree) {
    int maxNodes = (1 << tree.level + 1) - 1; // 2^h+1 - 1

    Position<Object> parent;

    // Add a new level and left node.
    if (count == maxNodes) {
      tree.level++;
      tree.count++;
      tree.addLeft(p, i);
      tree.levelCounter = (1 << tree.level) - 1; // points to left most node on new level
    } else {
      // start at root 000,001,010,011,100,101,110,111
      for (int j = 0; j < level - 1; j++) {

      }
    }

    // returns the left most leaf node. If heights are equal, add to this node.
    Object[] leftMost = left_height((Position<Object>) p);
    // returns the right most leaf node. If leftMost height != rightMost height, add
    // to this node
    Object[] rightMost = right_height((Position<Object>) p);
  }

  /** Returns the left height and a reference to the left-most node */
  static Object[] left_height(Position<Object> p) {
    int ht = 0;
    Position<Object> temp = null;
    while (p != null) {
      temp = p;
      ht++;
      p = ((Node<Object>) p).getLeft();
    }

    // Return the right height obtained
    Object[] result = new Object[2];
    result[0] = ht;
    result[1] = temp;
    return result;
  }

  /** Returns the right height and a reference to the right-most node */
  static Object[] right_height(Position<Object> node) {
    int ht = 0;
    Position<Object> temp = null;
    while (node != null) {
      temp = node;
      ht++;
      node = ((Node<Object>) node).getRight();
    }

    // Return the right height obtained
    Object[] result = new Object[2];
    result[0] = ht;
    result[1] = temp;
    return result;
  }

  private static void printTraversals(LinkedBinaryTree<Object> tree) {
    // System.out.println("Element Iterator. - uses Element Iterator (preorder)");
    // Iterator<Object> elems = (Iterator<Object>) tree.iterator();
    // while (elems.hasNext()) {
    // Object i = elems.next();
    // System.out.println(i);
    // }

    System.out.println("Preorder.");
    Iterator<Position<Object>> iter = tree.positions().iterator();
    while (iter.hasNext()) {
      Position<Object> i = iter.next();
      System.out.print(i.getElement() + " ");
    }
    System.out.println("");

    System.out.println("InOrder");
    iter = tree.inorder().iterator();
    while (iter.hasNext()) {
      Position<Object> i = iter.next();
      System.out.print(i.getElement() + " ");
    }
    System.out.println("");

    // System.out.println("Postorder.");
    // iter = tree.postorder().iterator();
    // while (iter.hasNext()) {
    //   Position<Object> i = iter.next();
    //   System.out.print(i.getElement() + " ");
    // }
    // System.out.println("");

    // System.out.println("breadthfirst.");
    // iter = tree.breadthFirst().iterator();
    // while (iter.hasNext()) {
    //   Position<Object> i = iter.next();
    //   System.out.print(i.getElement() + " ");
    // }
    // System.out.println("");
  }

  protected Node<E> createNode(
      E e,
      Node<E> parent,
      Node<E> left,
      Node<E> right) {
    return new Node<E>(e, parent, left, right);
  }

  protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
    if (!(p instanceof Node)) {
      throw new IllegalArgumentException("not a valid position type");
    }
    Node<E> node = (Node<E>) p;
    if (node.getParent() == node) {
      throw new IllegalArgumentException("Node is no longer in the tree");
    }
    return node;
  }

  public Position<E> left(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.left;
  }

  public Position<E> addLeft(Position<E> p, E e)
      throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if (parent.getLeft() != null) {
      throw new IllegalArgumentException(
          "p already has a left child: " + parent.left.element);
    }
    Node<E> child = createNode(e, parent, null, null);
    parent.setLeft(child);
    size++;
    return child;
  }

  public Position<E> addRight(Position<E> p, E e)
      throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if (parent.getRight() != null) {
      throw new IllegalArgumentException(
          "p already has a right child: " + parent.right.element);
    }
    Node<E> child = createNode(e, parent, null, null);
    parent.setRight(child);
    size++;
    return child;
  }

  public Position<E> right(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.right;
  }

  public E set(Position<E> p, E e) {
    Node<E> node = validate(p);
    E temp = node.getElement();
    node.setElement(e);
    return temp;
  }

  public void attach(
      Position<E> p,
      LinkedBinaryTree<E> t1,
      LinkedBinaryTree<E> t2)
      throws IllegalArgumentException {
    Node<E> node = validate(p);
    if (isInternal(p)) {
      throw new IllegalArgumentException("p must be leaf");
    }
    size += t1.size + t2.size;
    if (!t1.isEmpty()) {
      t1.root.setParent(node);
      t1.root = null; // dereference
      t1.size = 0;
    }
    if (!t2.isEmpty()) {
      t2.root.setParent(node);
      t2.root = null;
      t2.size = 0;
    }
  }

  public E remove(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    if (numChildren(p) == 2) {
      throw new IllegalArgumentException("p has two children");
    }
    Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
    if (child != null)
      child.setParent(node.getParent());

    if (node == root) {
      root = child;
    } else {
      Node<E> parent = node.getParent();
      if (node == parent.getLeft()) {
        parent.setLeft(child);
      } else {
        parent.setRight(child);
      }
    }
    size--;
    E temp = node.getElement();
    node.setElement(null);
    node.setLeft(null);
    node.setRight(null);
    node.setParent(null);

    return temp;
  }

  public Position<E> root() {
    return root;
  }

  public Position<E> addRoot(E e) throws IllegalStateException {
    if (!isEmpty())
      throw new IllegalStateException("Tree is not empty.");
    root = createNode(e, null, null, null);
    size = 1;
    return root;
  }

  public Position<E> parent(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.parent;
  }

  public int size() {
    return size;
  }

  public static class Node<E> implements Position<E> {

    private E element;
    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;

    public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
      element = e;
      parent = p;
      left = l;
      right = r;
    }

    public Node<E> getParent() {
      return parent;
    }

    public Node<E> getLeft() {
      return left;
    }

    public Node<E> getRight() {
      return right;
    }

    public E getElement() {
      return element;
    }

    // update methods
    public void setElement(E e) {
      element = e;
    }

    public void setParent(Node<E> p) {
      parent = p;
    }

    public void setLeft(Node<E> l) {
      left = l;
    }

    public void setRight(Node<E> r) {
      right = r;
    }
  }
}
