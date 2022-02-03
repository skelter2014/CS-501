
public class SinglyLinkedList<E> {

    // instance variables
    SinglyLinkedList<Integer> _scores;
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    /** Constructore */
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
        _scores = (SinglyLinkedList<Integer>) this; // reference to self
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[] { 10, 9, 7, 5, 4, 2, 1 };
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        // Load with Test Data
        // list.addFirst(a[0]);
        for (int i = 0; i < a.length; i++) {
            list.addNewScore(a[i]);
        }
        list.addNewScore(11);
        list.addNewScore(4);
        list.addNewScore(5);
        list.addNewScore(1);
        list.addNewScore(8);
        list.addNewScore(0);

        Node<Integer> pointer = list.head;

        pointer = list.head;
        while (pointer != null) {
            System.out.print(pointer.element + " ");
            pointer = pointer.next;
        }
        System.out.println("-----");
        System.out.print("\thead: " + list.head.element);
        System.out.print("\ttail: " + list.tail.element);

    }

    /**Adds a new score node to the list in the proper position */
    public void addNewScore(int newScore) {

        Node<Integer> pointer = _scores.head;
        // pointer = list.head;
        int ptrScore = pointer != null ? pointer.element : 0;

        // is list empty or is it bigger than head? just add to head
        if ( isEmpty() || newScore > ptrScore ) {
            _scores.addFirst(newScore);
        } else {
            // find the spot to insert
            while (pointer != tail && newScore < pointer.next.element) {
                pointer = pointer.next;
            }

            if (pointer == _scores.tail) {
                _scores.addLast(newScore);
            } else {
                // found the spot to insert between head and tail
                _scores.insert(newScore, pointer);
            }
        }
        
        if (size > 10){
            removeLastNode();
        }
    }

    /**Removes the last node of the list */
    public void removeLastNode(){
        Node<E> ptr = head;

        if (head == null){return;}

        while (ptr.next != tail){
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
    public Node<E> addFirst(E e) {
        head = new Node<>(e, head);
        if (size == 0) {
            tail = head;
        }
        size++;
        return head;
    }
    public Node<E> addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.next = newest; // connect new node to current tail.
        }
        tail = newest; // new node becomes the tail
        size++;
        return newest;
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
    /**Class to score Player's Name and High Score */
    private static class GameEntry {
        private String name;
        private int score;

        public GameEntry(String n, int s) {
            name = n;
            score = s;
        }
        // #region Getters & Setters
        public String getName() { return name;}
        public void setName(String name) {this.name = name;}
        public int getScore() {return score;}
        public void setScore(int score) {this.score = score;}

        // #endregion

        /** Returns a formatted string representation of this entry. */
        public String toString() {
            return String.format("%s :\t %02d", name, score);
        }}