import javax.swing.text.StyledEditorKit.BoldAction;

/** A basic doubly linked list */
public class DoublyLinkedList<E> {
    // instance variables
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;
    private final int MAX_SCORES = 10;

    public static void main(String[] args) {
        DoublyLinkedList<GameEntry> _scores = new DoublyLinkedList<GameEntry>();

        int[] a = new int[] { 22, 34, 5, 1, 7, 9, 12, 19, 18, 12, 9, 7, 2, 22, 3, 1, 11 };
        // int[] a = new int[] { 4,1,2,3 };
        // Load with 10 scores of test data
        _scores.printList();
        for (int i = 0; i < a.length; i++) {
            _scores.addNewScore(new GameEntry("Score_" + (i + 1), a[i]));
        }
        _scores.printList();

        GameEntry newScore = new GameEntry("Test", 11);
        // _scores.addNewScore(newScore);
        // newScore = new GameEntry("Test", 11);
        // _scores.addNewScore(newScore);
        // _scores.printList();
        // newScore = new GameEntry("Test", 1);
        // _scores.addNewScore(newScore);
        // _scores.printList();

    }

    public void printList() {
        if (isEmpty()) {
            System.out.println("\n------------------[Empty list]--------------------------\n");
        } else {
            System.out.println("--------------------------------------------------------");
            System.out.print("head: " + highScore() + "\ttail: " + lowScore() + "\n");
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

    /** Adds a new score node to the list in the proper DEC SORTED position */
    public void addNewScore(E entry) {
        int newScore = ((GameEntry) entry).score;

        if (isEmpty() || newScore > highScore()) {
            addFirst(entry);
            size++;
        } else {
            // determine pointer. Start at header or trailer
            int mean = highScore() + lowScore() / 2;
            Node<E> pointer;
            // if ( newScore < mean) {
            if (true) {
                pointer = header.next;
            } else {
                pointer = trailer.prev;
            }
            // Walk from header to insert point
            while (pointer != trailer && newScore < ((GameEntry) pointer.element).score) {
                pointer = pointer.next;
            }
            if (pointer == trailer) {
                addLast(entry);
            } else {
                addBetween(entry, pointer.prev, pointer);
            }
            size++;
            //Make sure we only store top 10 scores
            while (size > MAX_SCORES) { removeLast();}
        }

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
        return !isEmpty() ? header.next.element : null;
    }

    /** Removes the last element in the list */
    private E removeLast() {
        return !isEmpty() ? trailer.prev.element : null;
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
            return String.format("%s :    %02d", name, score);
        }
    }
    // #endregion - static inner classes
}
