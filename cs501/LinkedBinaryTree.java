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
     *                        1
     *               2                 3
     *          4        5         6        7
     *        8   9    10   11  | 12  13  14  15
     * --divide level in two.
     *    every left leaf is a twice as big as parent. Right leaf is 2x+1
     * pattern:
     *                       root
     *                0                 1
     *        00        01        10         11
     *    000   001  010  011  100  101   110  111  -- level = 3, 2^3 possible nodes.
     *
     *
     *
     */

  public static void main(String[] args) {
    LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();

    Position<Integer> root;
    Position<Integer> p1;
    Position<Integer> p2;
    Position<Integer> p3;
    Position<Integer> p4;
    Position<Integer> lefty;

    root = tree.addRoot(1);
    tree.count++;
    tree.level = 0; //2^0 = 1;


     p1 = tree.addLeft(root, 2);
    // p2 = tree.addRight(root, 3);
    // p3 =  tree.addLeft(p1, 4);
    // p4 = tree.addRight(p1, 5);
    // tree.addLeft(p2, 6);
    // tree.addRight(p2, 7);
    // lefty = tree.addLeft(p3, 8);
    tree.addNode(root, 0, tree);

    //int b = LinkedBinaryTree.countNodes(4);

    printTraversals(tree);
  }



  //2^h - 1  = total nodes in complete level.

  private void addNode(Position<Integer> p, int i, LinkedBinaryTree<Integer> tree) {
    int maxNodes = (1 << tree.level + 1) - 1; // 2^h+1 - 1

    Position<Integer> parent;

    //Add a new level and left node.
    if (count == maxNodes) {
      tree.level++;
      tree.count++;
      tree.addLeft(p, i);
      tree.levelCounter = (1 << tree.level) - 1;  //points to left most node on new level
    } else {
      //start at root  000,001,010,011,100,101,110,111
      for (int j=0; j < level - 1; j++){

      }
    }


    //returns the left most leaf node. If heights are equal, add to this node.
    Object[] leftMost = left_height((Position<Integer>) p);
    //returns the right most leaf node. If leftMost height != rightMost height, add to this node
    Object[] rightMost = right_height((Position<Integer>) p);
  }

  /**Returns the left height and a reference to the left-most node */
  static Object[] left_height(Position<Integer> p) {
    int ht = 0;
    Position<Integer> temp = null;
    while (p != null) {
      temp = p;
      ht++;
      p = ((Node<Integer>) p).getLeft();
    }

    // Return the right height obtained
    Object[] result = new Object[2];
    result[0] = ht;
    result[1] = temp;
    return result;
  }

  /**Returns the right height and a reference to the right-most node */
  static Object[] right_height(Position<Integer> node) {
    int ht = 0;
    Position<Integer> temp = null;
    while (node != null) {
      temp = node;
      ht++;
      node = ((Node<Integer>) node).getRight();
    }

    // Return the right height obtained
    Object[] result = new Object[2];
    result[0] = ht;
    result[1] = temp;
    return result;
  }

  private static void printTraversals(LinkedBinaryTree<Integer> tree) {
    System.out.println("Element Iterator. - uses Element Iterator (preorder)");
    Iterator<Integer> elems = (Iterator<Integer>) tree.iterator();
    while (elems.hasNext()) {
      Integer i = elems.next();
      System.out.println(i);
    }

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

    System.out.println("breadthfirst.");
    iter = tree.breadthFirst().iterator();
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
