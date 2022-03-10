package cs501;

import cs501.interfaces.Position;
import java.util.Iterator;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

  protected Node<E> root = null;
  private int size = 0;

  public static void main(String[] args) {
    LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();

    Position<Integer> root;
    Position<Integer> p1;
    Position<Integer> p2;

    root = tree.addRoot(1);
    p1 = tree.addLeft(root, 2);
    p2 = tree.addRight(root, 3);
    tree.addLeft(p1, 4);
    tree.addRight(p1, 5);
    tree.addLeft(p2, 6);
    tree.addRight(p2, 7);

    System.out.println("Preorder.");
    Iterator<Position<Integer>> iter = tree.positions().iterator();
    while (iter.hasNext()) {
      Position<Integer> i = iter.next();
      System.out.println(i.getElement());
    }

    System.out.println("Postorder.");
    iter = tree.postorder().iterator();
    while (iter.hasNext()) {
      Position<Integer> i = iter.next();
      System.out.println(i.getElement());
    }
  }

  protected Node<E> createNode(
    E e,
    Node<E> parent,
    Node<E> left,
    Node<E> right
  ) {
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
        "p already has a left child: " + parent.left.element
      );
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
        "p already has a right child: " + parent.right.element
      );
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
    LinkedBinaryTree<E> t2
  )
    throws IllegalArgumentException {
    Node<E> node = validate(p);
    if (isInternal(p)) {
      throw new IllegalArgumentException("p must be leaf");
    }
    size += t1.size + t2.size;
    if (!t1.isEmpty()) {
      t1.root.setParent(node);
      t1.root = null; //dereference
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
    if (child != null) child.setParent(node.getParent());

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
    if (!isEmpty()) throw new IllegalStateException("Tree is not empty.");
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

  protected static class Node<E> implements Position<E> {

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

    //update methods
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
