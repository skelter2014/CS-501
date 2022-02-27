package cs501;

import java.util.Arrays;


import cs501.interfaces.StackInterface;

public class LinkedListStack<E> implements StackInterface<E> {

    private SinglyLinkedList<E> list;
    public LinkedListStack() {
        list = new SinglyLinkedList<>();
    }


    public static void main(String[] args){
        LinkedListStack<Integer> stack = new LinkedListStack<>();
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
        
        LinkedListStack<String>stack2 = new LinkedListStack<>();
        String[] b = new String[]{"John", "Julie","Greta","Luke"};
        System.out.println(Arrays.toString(b));
        for (String s : b) {
            stack2.push(s);
        }
        System.out.println("top of stack: " + stack2.top());
        for (int i=0; i < b.length; i++) {
            b[i] = stack2.pop();
        }
        System.out.println(Arrays.toString(b));     


        
    }


    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E e) {
        list.addFirst(e);
    }

    public E top() {
        return list.first().getElement();
    }

    public E pop() {
        E element = list.first().getElement();
        list.removeFirst();
        return element;
    }
    
}
