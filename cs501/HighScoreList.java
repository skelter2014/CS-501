package cs501;

public class HighScoreList<E> {

    SinglyLinkedList<GameEntry> list;

    public static void main(String[] args) {

        HighScoreList<Integer> highScore = new HighScoreList<>();

        int[] a = new int[] { 1, 10, 2, 9, 8, 3, 7, 4, 5, 6, 3 };
        highScore.list = new SinglyLinkedList<>();

        for (int i : a) {

            GameEntry entry = new GameEntry("Score_" + i, i);
            if (i % 2 == 0) {
                highScore.addNewScore(entry);
            } else {
                highScore.addNewScore(entry);
            }
        }
        highScore.printList();
        highScore.list.removeNode(4);
        highScore.list.removeNode(3);
        highScore.printList();

    }

    /** Iterate the entire list and print the scores */
    public void printList() {
        if (list.isEmpty()) {
            System.out.println("\n------------------[Empty list]--------------------------\n");
        } else {
            System.out.println("--------------------------------------------------------");
            System.out.print("head: " + list.first().getElement() + "\ttail: " + list.last().getElement() + "\n");
            System.out.println("--------------------------------------------------------");
            Node<GameEntry> pointer = list.first();
            while (pointer != null) {
                System.out.println(pointer.getElement() + " ");
                pointer = pointer.getNext();
            }
        }

    }

    /** Adds a new score node to the list in the proper DEC SORTED position */
    public void addNewScore(GameEntry entry) {

        Node<GameEntry> pointer = list.first();
        // pointer = list.head;
        int ptrScore = pointer != null ? ((GameEntry) pointer.getElement()).score : 0;

        // is list empty or is it bigger than head? just add to head
        if (list.isEmpty() || entry.score > ptrScore) {
            list.addFirst(entry);
        } else {
            // find the spot to insert
            while (pointer != list.last().getNext() &&
                    entry.score < ((GameEntry) pointer.getNext().getElement()).score) {
                pointer = pointer.getNext();
            }

            if (pointer == list.last().getNext()) {
                list.addLast(entry);
            } else {
                // found the spot to insert between head and tail
                list.insert(entry, pointer);
            }
        }
        while (list.size() > 10) {
            list.removeLastNode();
        }
    }

    // public static void printList(SinglyLinkedList<GameEntry> list) {
    // if (list.isEmpty()) {
    // System.out.println("\n------------------[Empty
    // list]--------------------------\n");
    // } else {
    // System.out.println("--------------------------------------------------------");
    // System.out.print("head: " + list.head.element + "\ttail: " +
    // list.tail.element + "\n");
    // System.out.println("--------------------------------------------------------");
    // Node<GameEntry> pointer = list.first();
    // pointer = list.head;
    // while (pointer != null) {
    // System.out.println(pointer.getElement() + " ");
    // pointer = pointer.getNext();
    // }
    // }

    // }

    // /** Class to score Player's Name and High Score */
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
