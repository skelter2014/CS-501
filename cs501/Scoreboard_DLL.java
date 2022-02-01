package cs501;

/**
 * A Scoreboard class to store the top 10 GameEntry scores in Reverse order
 * highest to lowest. Assuming a non-empty list, the head always points to the highest score and
 * the tail always points to lowest score. 
 * 
 * A more recent score will be placed higher in the scoreboard if it is equal to
 * a prior score.
 */
public class Scoreboard_DLL {
    static final int MAX_ENTRIES = 10;

    // instance variables
    private int numOfEntries;
    private Node head;
    private Node tail;

    public static void main(String[] args) {
        // Create an array to hold multiple test sets~~
        Tester[] testSet = new Tester[7];

        // #region Test Score Arrays
        // unsorted scores with duplicate in the middle -
        Tester test0 = new Tester(
                new int[] { 34, 12, 3, 55, 45, 68, 33, 11, 10, 9, 4, 5, 2, 1, 77, 5, 55, 2, 67, 39, 20 });
        testSet[0] = test0;

        // unsorted scores <less than 10>, no order
        Tester test1 = new Tester(new int[] { 5, 55, 2, 67, 39, 20 });
        testSet[1] = test1;

        // presorted scores <less than 10 ASC order>
        Tester test2 = new Tester(new int[] { 1, 2, 3, 4, 5, 6 });
        testSet[2] = test2;

        // presorted scores <greater than 10 DEC>
        Tester test3 = new Tester(new int[] { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 });
        testSet[3] = test3;

        // unsorted scores with duplicate on the ends
        Tester test4 = new Tester(
                new int[] { 34, 12, 77, 55, 45, 68, 33, 11, 10, 33, 20, 5, 2, 1, 77, 5, 55, 2, 39, 20 });
        testSet[4] = test4;

        // unsorted all duplicate scores
        Tester test5 = new Tester(new int[] { 10, 10, 10, 10, 10 });
        testSet[5] = test5;

        // unsorted all zero scores
        Tester test6 = new Tester(new int[] { 0, 0, 0, 0, 0 });
        testSet[6] = test6;
        // #endregion Test Score Arrays

        // Iterate through the set of testData
        for (Tester tester : testSet) {
            tester.Test();
        }

        // Test the remove(index i) function
        Scoreboard_DLL testBoard =  test0.getBoard();
        displayScores(testBoard);
        testBoard.remove(10); // remove the tail
        testBoard.remove(1); // remove the head
        testBoard.remove(5); //remove one in the middle
        displayScores(testBoard);
    }

    public Scoreboard_DLL() {
        // The scoreboard is currently empty and has no low (or high) scores.
        numOfEntries = 0;
        head = null;
        tail = null;
    }

    /** A utility method to display the current scoreboard entries. */
    private static void displayScores(Scoreboard_DLL board) {
        System.out.println("-------------------------------------------");
        System.out.printf("\tHigh Score: %02d   Low Score: %02d\n", board.highScore(), board.lowScore());
        System.out.println("-------------------------------------------");
        Node ptr = board.head;
        while (ptr != null) {
            System.out.println(ptr.element);
            ptr = ptr.next;
        }

    }

    /** Returns the current low score on the scoreboard */
    private int lowScore() {
        return tail != null ? tail.element.score : 0;
    }
    /** Returns the current high score on the scoreboard */
    private int highScore() {
        return head != null ? head.element.score : 0;
    }
    /**
     * Add an arbitrary GameEntry object to the scoreboard. Entries
     * are stored in Descending order by score.
     */
    public void add(GameEntry e) {
        // if the list is empty or the score higher than the high score, insert at the
        // front.
        if (head == null || e.score >= highScore()) {
            addFirst(e);
        }
        // check to see if the score board is not full or if the score is higher than
        // the low score
        else if (numOfEntries < MAX_ENTRIES || e.score >= lowScore()) {

            Node pointer = head;
            // Walk the entire list. Check the
            while (pointer != tail && pointer.next.element.score > e.score) {
                pointer = pointer.next;
            }
            // Replacing the tail is a special case.
            if (pointer == tail) {
                addLast(e);
            } else {
                insertNode(pointer, e);
            }
            trimScoreBoard();
        }
    }

    /**Removes an entry at index(i) position - index is 1-based.*/

    public GameEntry remove(int index) throws IndexOutOfBoundsException {
        Node deletedNode;
        if (index < 1 || index > numOfEntries) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        // Index = 1 is a special case
        if (index == 1) {
            deletedNode = removeFirst();
        } else {

            int j = 1; // set the pointer to the head
            Node current = head;

            while (index > j + 1) { // look ahead one node with j+1
                current = current.next; // move the node pointer
                j++;
            }
            deletedNode = current.next; // store the deleted node in a variable

            //Did we reach the end of the list? If yes, reset the tail
            if (deletedNode == tail) {
                current.next = null;
                tail = current;
            } else {
                current.next = current.next.next; // connect the current node to the node following the del node
            }
        }
        numOfEntries--;
        return deletedNode.element; // return the GameEntry
    }

    /** Trims the ScoreBoard to be of size MAX_SCORES */
    private void trimScoreBoard() {
        if (numOfEntries > MAX_ENTRIES) {
            Node pointer = head;

            for (int i = 1; i < MAX_ENTRIES; i++) {
                pointer = pointer.next; // move the pointer til the end.
            }

            tail = pointer; // reset the tail
            tail.next = null; // and chop off the rest
            numOfEntries = MAX_ENTRIES; // reset back to the maximum
        }

    }

    // Insert a new node into the chain
    private void insertNode(Node pointer, GameEntry e) {
        // Insert the node
        if (pointer != null) {
            Node newNode = new Node(e, pointer.next);
            pointer.next = newNode;
            numOfEntries++;
        }
    }

    // access methods
    public int size() {
        return numOfEntries;
    }

    public boolean isEmpty() {
        return numOfEntries == 0;
    }

    public GameEntry first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    public GameEntry last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    /** Adds a new node to the head of the list. */
    private Node addFirst(GameEntry e) {
        Node newNode = new Node(e, head);
        newNode.next = head;
        head = newNode;
        if (numOfEntries == 0) {
            tail = head;
        }
        numOfEntries++;
        trimScoreBoard();
        return head;
    }

    /** Adds a new node to the end of the list. */
    private Node addLast(GameEntry e) {
        Node newest = new Node(e, null);
        if (isEmpty()) {
            head = newest;
            head.next = tail;
        } else {
            tail.next = newest; // connect new node to current tail.
        }
        tail = newest; // new node becomes the tail
        numOfEntries++;
        trimScoreBoard();
        return tail;
    }
    /**Removes the first node (head) of the list and resets the head ptr  */
    public Node removeFirst() {
        if (isEmpty()) {
            return null;
        }
        head = head.getNext();
        numOfEntries--;
        if (numOfEntries == 0) {
            tail = null;
        }
        return head; // return the node element.

    }

    // #region - additional solution classes
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
    // nested Node Class
    private static class Node {
        private GameEntry element;
        private Node next;

        public Node(GameEntry e, Node n) {
            element = e;
            next = n;
        }

        public GameEntry getElement() {
            return element;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node n) {
            next = n;
        }
    }
    /**A custom class to run various testing scenarios and print them to the console*/
    private static class Tester {
        private int[] testData;
        Scoreboard_DLL scoreboard ;


        public Tester(int[] data) {
            this.testData = data;
        }

        public Scoreboard_DLL getBoard(){
            return scoreboard;
        }

        public void Test() {
            GameEntry e;
            scoreboard = new Scoreboard_DLL();

            // load the scoreboard with the testData[] scores
            for (int i = 0; i < testData.length; i++) {
                e = new GameEntry(String.format("Score#: %02d", i + 1), testData[i]);
                scoreboard.add(e);
            }
            displayScores(scoreboard);
        }
    }
    // #endregion
}