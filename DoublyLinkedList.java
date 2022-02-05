import java.util.Random;

import javax.swing.plaf.basic.BasicSliderUI.TrackListener;
import javax.swing.text.StyledEditorKit.BoldAction;

/** A basic doubly linked list */
public class DoublyLinkedList<E> {
    // instance variables
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;
    private final int MAX_SCORES = 10;
    private int hops = 0;
    private static boolean HEAD_ONLY = false; //an override for testing hops
    private static boolean TAIL_ONLY = true; //provide an override for testing

    public static void main(String[] args) {
        DoublyLinkedList<GameEntry> _scores = new DoublyLinkedList<GameEntry>();

        Random rnd = new Random(System.currentTimeMillis());

        int[] a = new int[] {10,9,8,7,6,5,4,3,2,1};
        int[] b = new int[] {1,2,3,4,5,6,7,8,9,10};
        int[] c = new int[] {10,1,9,2,8,3,7,4,6,5}; //worse case #1. 
        int[] d = new int[] {1,10,2,9,3,8,4,7,5,6}; //worse case #2. 

        // Load with test data

        System.out.println("\nTest A HEAD_ONLY/TAIL_ONLY = "+ HEAD_ONLY+":"+TAIL_ONLY);
        for (int i = 0; i < a.length; i++) {
            _scores.addNewScore(new GameEntry("Score_" + (i + 1), a[i]));
        }
        _scores.printList();
        System.out.println("Total Hops:" + _scores.hops);

        _scores = new DoublyLinkedList<>();
        System.out.println("\nTest B HEAD_ONLY/TAIL_ONLY = "+ HEAD_ONLY+":"+TAIL_ONLY);
        for (int i = 0; i < a.length; i++) {
            _scores.addNewScore(new GameEntry("Score_" + (i + 1), b[i]));
        }
        _scores.printList();
        System.out.println("Total Hops:" + _scores.hops);
       
        _scores = new DoublyLinkedList<>();
        System.out.println("\nTest C HEAD_ONLY/TAIL_ONLY = "+ HEAD_ONLY+":"+TAIL_ONLY);
        for (int i = 0; i < a.length; i++) {
            _scores.addNewScore(new GameEntry("Score_" + (i + 1), c[i]));
        }
        _scores.printList();
        System.out.println("Total Hops:" + _scores.hops);
        
        _scores = new DoublyLinkedList<>();
        System.out.println("\nTest D HEAD_ONLY/TAIL_ONLY = "+ HEAD_ONLY+":"+TAIL_ONLY);
        for (int i = 0; i < a.length; i++) {
            _scores.addNewScore(new GameEntry("Score_" + (i + 1), d[i]));
        }
        _scores.printList();
        System.out.println("Total Hops:" + _scores.hops);


    }

    public void printList() {
        if (isEmpty()) {
            System.out.println("\n------------------[Empty list]--------------------------\n");
        } else {
            System.out.println("--------------------------------------------------------");
            System.out.print("head: " + highScore() + "\ttail: " + lowScore() + "\t\tcount: "+ size + "\n");
            System.out.println("--------------------------------------------------------");
            Node<E> pointer = header.next;

            while (pointer != trailer) {
                System.out.println(pointer.element + " ");
                pointer = pointer.next;
            }
        }
    }

    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.next = trailer;
    }

    /** Return current highscore (which is the first node) */
    public int highScore() {
        return !isEmpty() ? ((GameEntry) header.next.element).score : 0;
    }

    /** Return current lowscore (which is the last node) */
    public int lowScore() {
        return !isEmpty() ? ((GameEntry) trailer.prev.element).score : 0;
    }

    /** Adds a new score node to the list in the proper DEC SORTED position 
     * This is the only public method to add scores to this list ensuring that 
     * all scores are sorted and inserted properly
     * 
     * 
    */
    public void addNewScore(E entry) {
        int newScore = ((GameEntry) entry).score;

        if (isEmpty() || newScore >= highScore()) {
            addFirst(entry);
        } else if  (size <= MAX_SCORES || newScore > lowScore() ) { //only walk the list if it is higher than low score
            // determine pointer. Start at header or trailer
            int mean = (highScore() + lowScore()) / 2;
            Node<E> pointer;
            //if (START_AT_HEAD_ONLY){
            if ( ! TAIL_ONLY &&  (HEAD_ONLY || newScore > mean) ) { //Search left to right
                pointer = header.next;
                pointer = searchFromHeader(pointer, newScore);
                if (pointer == trailer) {
                    addLast(entry);
                } else {
                    addBetween(entry, pointer.prev, pointer);
                }                
            } else { //search right to left
                pointer = trailer.prev;
                pointer = searchFromTrailer(pointer, newScore);
                if (pointer == header) {
                    addFirst(entry);
                } else {
                    addBetween(entry, pointer, pointer.next);
                }
            }
            //Make sure we only store top 10 scores
            while (size > MAX_SCORES) { removeLast();}
        }

    }

    //Find the correct insertion point from the head of the list.
    private Node<E> searchFromHeader(Node<E> pointer, int newScore){
            // Walk from header to insert point
            while (pointer != trailer && newScore < ((GameEntry) pointer.element).score) {
                pointer = pointer.next;
                hops++;
            }
            return pointer;
    }
    //Find the correct insertion point from the tail of the list
    private Node<E> searchFromTrailer(Node<E> pointer, int newScore){
        while (pointer!= header && newScore > ((GameEntry) pointer.element).score){
            pointer = pointer.prev;
            hops++;
        }
        return pointer;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        return !isEmpty() ? header.next.element : null;
    }

    public E last() {
        return !isEmpty() ? trailer.prev.element : null;
    }

    /** Add Element to Front of List */
    private void addFirst(E e) {
        addBetween(e, header, header.next);
    }

    /** Adds Element to end of list. */
    private void addLast(E e) {
        addBetween(e, trailer.prev, trailer);
    }

    /** Remove the first element in the list */
    private E removeFirst() {
        Node<E> deletedNode = null;;
        if ( !isEmpty() ) {
            deletedNode = header.next; //stored the deleted node.
            header.next = header.next.next; // move the pointer one step right
            size--;

            return deletedNode.element;
        }
        else {return null;}
    }

    /** Removes the last element in the list */
    private E removeLast() {
        Node<E> deletedNode = null;
        if ( !isEmpty() ) {
            deletedNode = trailer.prev; //store the deleted node
            trailer = trailer.prev; //mote the pointer one step left
            size--;

            return deletedNode.element;
        }
        else {return null;}
    }

    /** Remove the element at position i in the LEAST number of iterations */
    public E remove(int i) {
        throw new UnsupportedOperationException();

    }

    /** Adds element between given before / after nodes */
    private void addBetween(E e, Node<E> before, Node<E> after) {
        Node<E> newNode = new Node<>(e, before, after); // new node is connected
        before.next = newNode;
        after.prev = newNode;
        size++;
    }

    private E remove(Node<E> node) {
        Node<E> before = node.prev;
        Node<E> after = node.next;
        before.next = after; // connect before and after nodes.
        after.prev = before; // connect before and after nodes
        size--;
        return node.getElement();
    }

    // #region private static inner classes
    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

        public void SetPrev(Node<E> p) {
            prev = p;
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
            return String.format("%s :\t%02d", name, score);
        }
    }
    // #endregion - static inner classes
}
