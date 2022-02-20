package cs501;

import java.util.Arrays;

public class ArrayStack<E> implements StackInterface<E> {
    private static final int CAPACITY =20;
    private E[] data;
    private int t = -1; // starting index is negative 1

    public static void main(String[] args){
        ArrayStack<Integer> stack = new ArrayStack<>();
        int[] a = new int[]{1,2,3,4,5};
        System.out.println(Arrays.toString(a));
        for (int i : a) {
            stack.push(i);
        }
        System.out.println("top of stack: " + stack.top());
        for (int i=0; i < a.length; i++) {
            a[i] = stack.pop();
        }
        System.out.println(Arrays.toString(a));


        System.out.println("isMatched: " + isMatched("(a+(b*c){a-c}[a]+[b]*d)"));
    }

    public static boolean isMatched(String expression){
        final String opening = "({[";
        final String closing = ")}]";

        ArrayStack<Character> buffer = new ArrayStack<>();
        for (char c: expression.toCharArray()){
            if (opening.indexOf(c) != -1){
                buffer.push(c);
            }
            else if (closing.indexOf(c)!= -1){
                if (buffer.isEmpty()) {return false;}
                if (closing.indexOf(c) != opening.indexOf(buffer.pop())){
                    return false;
                }
            }
        }
        return buffer.isEmpty();
    }

    public ArrayStack() {
        this(CAPACITY);
    }

    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public int size() {
        return (t + 1);
    }

    public boolean isEmpty() {
        return (t == -1);
    }

    public void push(E e) throws IllegalStateException {
        if (size() == data.length) {
            throw new IllegalStateException("Stack is full");
        }
        data[++t] = e;
    }

    public E top() {
        if (isEmpty()) {
            return null;
        }
        return data[t];
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E result = data[t];
        data[t] = null;
        t--;
        return result;
    }

}
