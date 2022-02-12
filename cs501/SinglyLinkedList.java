package cs501;

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
        int[] a = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        SinglyLinkedList<GameEntry> list = new SinglyLinkedList<>();

        // Load with 10 scores of test data
        printList(list);
        for (int i = 0; i < a.length; i++) {
            list.addNewScore(new GameEntry("Score_" + (i + 1), a[i]));
        }
        printList(list);

        // Add insert a new score into the list
        list.addNewScore(new GameEntry("Score_X", 7));
        printList(list);

        // Test removing 8 nodes
        while (list.size > 2) {
            list.removeNode(1);
        }
        printList(list);
        // add a couple more
        list.addNewScore(new GameEntry("Test", 11));
        list.addFirst(new GameEntry("First", 2)); // Add directly to head. Not subject to sort
        list.addLast(new GameEntry("Last", 99));// Add directly to tail. Not subject to sort.

        printList(list);

        // test the removeNode method
        if (!list.isEmpty()) {
            list.removeNode(1);
            list.removeNode(1);
            list.removeNode(1);
        }

        printList(list);

    }

    /** Iterate the entire list and print the scores */
    public static void printList(SinglyLinkedList<GameEntry> list) {
        if (list.isEmpty()) {
            System.out.println("\n------------------[Empty list]--------------------------\n");
        } else {
            System.out.println("--------------------------------------------------------");
            System.out.print("head: " + list.head.element + "\ttail: " + list.tail.element + "\n");
            System.out.println("--------------------------------------------------------");
            Node<GameEntry> pointer = list.head;
            pointer = list.head;
            while (pointer != null) {
                System.out.println(pointer.element + " ");
                pointer = pointer.next;
            }
        }

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
            Node<E> pointer = head.next;
            int j = 2;

            while (i > j + 1) {
                pointer = pointer.next;
                j++;
            }
            if (pointer.next == tail) {
                removeLastNode();
            } else {
                pointer.next = pointer.next.next;
            }
            size--;
        }

    }

    /** Adds a new score node to the list in the proper DEC SORTED position */
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
        while (size > 10) {
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
        size--;
    }

    // helper methods
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
        if (size == 10) {
            throw new UnsupportedOperationException("List already has MAX elements.");
        }
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
            return String.format("%s :    %02d", name, score);
        }
    }
}