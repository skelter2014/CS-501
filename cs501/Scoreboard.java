package cs501;

import java.util.Random;

public class Scoreboard {
    private int currentLow;
    static final int MAX_SCORES = 10;
    private int numOfEntries;
    // instance variables
    private Node head;
    private Node tail;

    public static void main(String[] args) {
        Scoreboard scoreboard = new Scoreboard();

        GameEntry e;

        Random rnd = new Random();

        for (int i = 1; i <= 150; i++ ) {
            e = new GameEntry("Name_" + i, rnd.nextInt(100));
            scoreboard.add(e);
        }
        displayScores(scoreboard);
        scoreboard.add(new GameEntry("sample entry", 4));
        displayScores(scoreboard);

    }

    /**
     * Shifts the board to the right n entries. Entries at the end of the list wrap
     * around to the head effectively
     * sorting the list in reverse order
     */
    // private static void shiftBoardRight(Scoreboard scoreboard) {
    //     Node ptr = scoreboard.head;
    //     for (int i = 1; i <= scoreboard.numOfEntries - 5; i++) {
    //         scoreboard.removeFirst();
    //         scoreboard.addLast(ptr.element);
    //         ptr = ptr.next;
    //     }
    // }

    private static void displayScores(Scoreboard board) {
        Node ptr = board.head;
        while (ptr != null) {
            System.out.println(ptr.element);
            ptr = ptr.next;
        }
        System.out.println("---------------");

    }

    private int lowScore() {
        return tail != null ? tail.element.score : 0;
    }
    private int highScore(){
        return head!=null ? head.element.score : 0;
    }

    /**Add an arbitrary GameEntry object to the scoreboard. Entries
     * are stored in Descending order by score. 
     */
    public void add(GameEntry e) {
        if (head == null) {
            addFirst(e);
            return;
        }
        Node pointer = head;
        if (e.score > highScore() ){
            addFirst(e);
        } 
        else if (numOfEntries < MAX_SCORES || e.score < highScore() && e.score >= lowScore() ){

            while (pointer.element.score > e.score){
                pointer = pointer.next;
            }
            //Replacing the tail is a special case.
            if (pointer == tail) {
                addLast(e);
            }
            else { 
                insertNode(pointer, e);
            }
            trimScoreBoard();

        }




    }
    private void trimScoreBoard(){
        if (numOfEntries > MAX_SCORES){
            Node pointer = head;

            for (int i=1; i < 10; i++ ){
                pointer = pointer.next; //move the pointer til the end.
            }

            tail = pointer; //reset the tail 
            tail.next = null; // and chop off the rest
            numOfEntries = MAX_SCORES; // reset back to the maximum
        }        

    }
    //Insert a new node into the chain
    private void insertNode(Node pointer, GameEntry e){
         //Insert the node
         if (pointer != null){
             Node newNode = new Node(e,pointer.next);
            pointer.next = newNode;
            numOfEntries++;
        }        
    }

    public Scoreboard() {
        // The scoreboard is currently empty and has no low (or high) scores.
        numOfEntries = 0;
        currentLow = 0;
        head = null;
        tail = null;

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

    // update methods
    // Adds a new Node to Head. If Tail is null, it is also set to Head.
    public Node addFirst(GameEntry e) {
        head = new Node(e, head);
        if (numOfEntries == 0) {
            tail = head;
        }
        numOfEntries++;
        return head;
    }

    public Node addLast(GameEntry e) {
        Node newest = new Node(e, null);
        if (isEmpty()) {
            head = newest;
            head.next = tail;
        } else {
            tail.next = newest; // connect new node to current tail.
        }
        tail = newest; // new node becomes the tail
        numOfEntries++;
        return tail;
    }

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

    static class GameEntry {
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
            return "(" + name + ", " + score + ")";
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

}