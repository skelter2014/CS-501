
public class SinglyLinkedList<E> {

    // instance variables
    SinglyLinkedList<GameEntry> _scores;
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    /** Constructore */
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
        _scores = (SinglyLinkedList<GameEntry>) this; // reference to self
    }

    public static void main(String[] args) {
        int[] a = new int[] { 10, 9, 7, 5, 4, 2, 1,12,15,13,19 };
        SinglyLinkedList<GameEntry> list = new SinglyLinkedList<>();

        // Load with Test Data
        // list.addFirst(a[0]);
        for (int i = 0; i < a.length; i++) {
            list.addNewScore(new GameEntry("Score_"+i, a[i]));
        }
        // list.addNewScore(11);
        // list.addNewScore(4);
        // list.addNewScore(5);
        // list.addNewScore(1);
        // list.addNewScore(8);
        // list.addNewScore(0);

        System.out.println("--------------------------------------------------------");
        System.out.print("head: " + list.head.element+"\ttail: " + list.tail.element+"\n");
        System.out.println("--------------------------------------------------------");

        Node<GameEntry> pointer = list.head;
        pointer = list.head;
        while (pointer != null) {
            System.out.println(pointer.element + " ");
            pointer = pointer.next;
        }

    }

    /** Adds a new score node to the list in the proper position */
    public void addNewScore(GameEntry entry) {

        Node<GameEntry> pointer = _scores.head;
        // pointer = list.head;
        int ptrScore = pointer != null ? pointer.element.score : 0;

        // is list empty or is it bigger than head? just add to head
        if (isEmpty() || entry.score > ptrScore) {
            _scores.addFirst(entry);
        } else {
            // find the spot to insert
            while (pointer != tail && entry.score < pointer.next.element.score) {
                pointer = pointer.next;
            }

            if (pointer == _scores.tail) {
                _scores.addLast(entry);
            } else {
                // found the spot to insert between head and tail
                _scores.insert(entry, pointer);
            }
        }

        if (size > 10) {
            removeLastNode();
        }
    }
    /** Removes the last node of the list */
    public void removeLastNode() {
        Node<E> ptr = head;

        if (head == null) {
            return;
        }
        while (ptr.next != tail) {
            ptr = ptr.next;
        }
        ptr.next = null;
        tail = ptr;
    }
    // access methods
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }
    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }
    // update methods
    public void addFirst(E e) {
        head = new Node<>(e, head);
        if (size == 0) {
            tail = head;
        }
        size++;
    }
    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.next = newest; // connect new node to current tail.
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
            prev.next = new Node<>(e, prev.next);
            size++;
        }
    }

    // nested Node Class
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
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
    }
    /** Class to score Player's Name and High Score */
    private static class GameEntry {
        private String name;
        private int score;

        public GameEntry(String n, int s) {
            name = n;
            score = s;
        }

        // #region Getters & Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        // #endregion

        /** Returns a formatted string representation of this entry. */
        public String toString() {
            return String.format("%s :\t %02d", name, score);
        }
    }
}