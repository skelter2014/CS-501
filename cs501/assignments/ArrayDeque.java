/**
 * Give a complete implementation of the Deque ADT using a fixed-capacity array,
 * so that each of the update methods runs in O(1) time.
 */
public class ArrayDeque<E> implements DequeInterface<E> {

    private E[] data;
    private int f = 0; // index for nextred side of stack
    private int b = 0; // index for blue side of stack
    private int sz = 0;
    private final static int CAPACITY = 1;

    /** Test the Array Deque */
    public static void main(String[] args) {
        ArrayDeque<String> deque = new ArrayDeque<>();

        deque.addFirst("one");
        deque.addLast("two");
        deque.addFirst("three");
        deque.addLast("four");
        deque.addLast("five");
        deque.addLast("six");
        deque.addFirst("seven");
        deque.addFirst("eight");
        deque.removeFirst();
        deque.removeLast();
        deque.addLast("nine");
        deque.addLast("ten");
        deque.addFirst("eleven");
        deque.addLast("twelve");

        while (deque.isEmpty() == false) {
            System.out.print(deque.removeFirst() + " ");
        }

    }

    public ArrayDeque() {
        this(ArrayDeque.CAPACITY);
    }

    public ArrayDeque(int capacity) {
        data = (E[]) new Object[capacity];
        b = data.length - 1;
    }

    public int size() {
        return sz;
    }

    /** Exposes the underlying array length so that it can be resized */
    public int dataLength() {
        return data.length;
    }

    public boolean isEmpty() {
        return sz == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return data[f];
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        int last = (f + sz) % data.length;
        return data[last];
    }

    public void addFirst(E e) {
        f = (f - 1 + data.length) % data.length;
        data[f] = e;
        sz++;
    }

    public void addLast(E e) {
        int last = (f + sz) % data.length;
        data[last] = e;
        sz++;

    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        }

        int last = (f + sz - 1) % data.length;
        E result = data[last];
        data[last] = null;
        sz--;
        return result;

    }

    /**Doubles the size of the underlying array. The array grows from the inside
     * out because it is used as a double ended stack. The array is copied in two steps:
     *  index 0  -- redIndex, blueIndex -- size - 1;
     * 
     *   | red tokens ------|<--------empty------>|blue tokens----------|
     */
    public void resize(int redIndex, int blueIndex){
        E[] temp = (E[])new Object[ data.length * 2 ];
        
        //copy the red tokens
        for(int k=0; k < redIndex; k++){
            temp[k] = data[k];
        }
        //copy the blue tokens
        for(int j = (data.length - blueIndex); j < data.length; j++ ){
            temp[ temp.length - j ] = data[j];
        }
        data = temp;
        

    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E result = data[f];
        data[f] = null;
        f = (f + 1) % data.length;
        sz--;
        return result;

    }

}
