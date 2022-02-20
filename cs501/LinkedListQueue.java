package cs501;

public class LinkedListQueue<E> implements QueueInterface<E> {

    public static void main(String[] args){
        LinkedListQueue<String> queue = new LinkedListQueue<>();
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        queue.enqueue("five");
        queue.enqueue("six");
        queue.enqueue("seven");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("eight");
        queue.enqueue("nine");
        queue.enqueue("ten");
        queue.enqueue("eleven");
        queue.enqueue("twelve");
        queue.enqueue("thirteen");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        queue.enqueue("five");
        queue.enqueue("six");

        while (queue.isEmpty() == false){
            System.out.print(queue.dequeue()+ " ");
        }


    }


    SinglyLinkedList<E> list = new SinglyLinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enqueue(E e) {
        list.addLast(e);
    }

    public E first() {
        return list.first().getElement();
    }

    public E dequeue() {
        return list.removeFirst();
    }

}
