package cs501;

public class CircularyLinkedList<E> {
    private Node<E> tail = null;
    private int size = 0;

    public static void main(String[] args){
        CircularyLinkedList<Integer> list = new CircularyLinkedList<>();

        for (int i=0; i <=3; i++) {
            list.addLast(i*i);
        }

        System.out.println("-------------------------------");
        list.printList();
        list.rotate();
        System.out.println("-------------------------------");
        list.printList();
    }


    



    public void printList() {
        Node<E> pointer = tail.next;

        while (pointer != tail){
            System.out.println(pointer.element);
            pointer = pointer.next;
        }
        System.out.println(pointer.element);

    }
    public CircularyLinkedList() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node<E> first() {
        return !isEmpty() ? tail.next : null;
    }
    public void rotate() {
        if (tail != null){
            tail = tail.next;
        }
    }
    public void addFirst(E e){
        if(isEmpty()){
            tail = new Node<>(e,null);
            tail.next = tail;
        } else {
            Node<E> newNode = new Node<>(e,tail.next);
            tail.next = newNode;
        }
        size++;
    }
    public void addLast(E e){
        addFirst(e);
        tail = tail.next;
       
    }
    public Node<E> removeFirst() {
        if (isEmpty()) return null;
        Node<E> head = tail.next;
        if (head == tail) tail = null;
        else tail.next = head.next;
        size --;
        return head;
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

}