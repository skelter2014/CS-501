

public class SinglyLinkedList<E> {

    // instance variables
    // SinglyLinkedList<GameEntry> _scores;
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    /** Constructore */
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }


    /** Removes the nth node in the list */
    public void removeNode(int i) {
        if (i < 1 || i > size) {
            throw new IndexOutOfBoundsException();
        }

        if (i == 1) {
            removeFirst();
        } else if (i == size) {
            removeLastNode();
        } else {
            Node<E> pointer = head.getNext();
            int j = 2;

            while (i > j + 1) {
                pointer = pointer.getNext();
                j++;
            }
            if (pointer.getNext() == tail) {
                removeLastNode();
            } else {
                Node<E> nextNode = pointer.getNext().getNext();
                pointer.setNext(nextNode);
            }
            size--;
        }

    }


    /** Removes the last node of the list */
    public void removeLastNode() {
        Node<E> ptr = head;

        if (head == null) {
            return;
        }
        while (ptr.getNext() != tail) {
            ptr = ptr.getNext();
        }
        ptr.setNext(null);
        tail = ptr;
        size--;
    }

    // helper methods
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node<E> first() {
        if (isEmpty()) {
            return null;
        }
        return head;
    }

    public Node<E> last() {
        if (isEmpty()) {
            return null;
        }
        return tail;
    }

    /** Add a score directly to head of list. Does not ensure proper ordering. */
    public void addFirst(E e) {
        head = new Node<>(e, head);
        if (size == 0) {
            tail = head;
        }
        size++;
        // Make sure still mainain MAX records in list.
        if (size > 10) {
            removeLastNode();
        }
    }

    /** Add score directly to end of list. Does not ensure proper ordering */
    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest); // connect new node to current tail.
        }
        tail = newest; // new node becomes the tail
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E element = head.getElement();
        head = head.getNext();

        if (size == 0) {
            tail = null;
        }
        size--;
        return element; // return the node element.

    }

    /** If list is empty, insert at head, otherwise insert after prev */
    public void insert(E e, Node<E> prev) {
        if (isEmpty()) {
            addFirst(e);

        } else {
            prev.setNext( new Node<>(e, prev.getNext()) );
            size++;
        }
    }


}