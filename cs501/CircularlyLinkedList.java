package cs501;

public class CircularlyLinkedList<E> {
    private Node<E> tail = null;
    private int size = 0;

    public static void main(String[] args) {
        CircularlyLinkedList<Integer> list = new CircularlyLinkedList<>();

        for (int i = 0; i <= 3; i++) {
            list.addLast(i * i);
        }

        System.out.println("-------------------------------");
        list.printList();
        list.rotate();
        System.out.println("-------------------------------");
        list.printList();
    }

    public void printList() {
        Node<E> pointer = tail.getNext();

        while (pointer != tail) {
            System.out.println(pointer.getElement());
            pointer = pointer.getNext();
        }
        System.out.println(pointer.getElement());

    }

    public CircularlyLinkedList() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node<E> first() {
        return !isEmpty() ? tail.getNext() : null;
    }

    public void rotate() {
        if (tail != null) {
            tail = tail.getNext();
        }
    }

    public void addFirst(E e) {
        if (isEmpty()) {
            tail = new Node<>(e, null);
            tail.setNext(tail);
        } else {
            Node<E> newNode = new Node<>(e, tail.getNext());
            tail.setNext(newNode);
        }
        size++;
    }

    public void addLast(E e) {
        addFirst(e);
        tail = tail.getNext();

    }

    public Node<E> removeFirst() {
        if (isEmpty())
            return null;
        Node<E> head = tail.getNext();
        if (head == tail)
            tail = null;
        else
            tail.setNext(head.getNext());
        size--;
        return head;
    }

}