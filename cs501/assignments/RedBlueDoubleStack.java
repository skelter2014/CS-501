/**
 * Assignment 2
* Design an ADT for a two-color, double-stack ADT that consists of two stacks— one red 
* and one blue — and has as its operations color-coded versions of the regular stack ADT operations. 
* For example, this ADT should support both a redPush operation and a bluePush operation. 
* Give an efficient implementation of this ADT using a single array whose capacity is set at some value N 
* that is assumed to always be larger than the sizes of the red and blue stacks combined;
* Chapter 6, project 37.
 */
public class RedBlueDoubleStack<E> implements RedBlueStackInterface<E> {

    /** Use a double ended queue Deque as a two-sided stack */

    E[] data;
    private final int INITIAL_CAPACITY = 1;

    private int blueSize = 0;
    private int redSize = 0;

    /** Driver method to Test a double sided stack */
    public static void main(String args[]) {
        RedBlueDoubleStack<StackColor> stack = new RedBlueDoubleStack<>();

        stack.pushRed(new StackColor(Color.Red));
        stack.pushBlue(new StackColor(Color.Blue));
        stack.pushRed(new StackColor(Color.Red));
        stack.pushRed(new StackColor(Color.Red));
        stack.pushBlue(new StackColor(Color.Blue));
        stack.pushRed(new StackColor(Color.Red));
        stack.pushBlue(new StackColor(Color.Blue));
        stack.pushRed(new StackColor(Color.Red));
        stack.pushBlue(new StackColor(Color.Blue));
        stack.pushRed(new StackColor(Color.Red));
        stack.pushRed(new StackColor(Color.Red));
        stack.pushRed(new StackColor(Color.Red));

        stack.printStack();

        System.out.println("\t\t<<-Pop 4 Red. >>Push 4 Blue.");
        stack.popRed();
        stack.popRed();
        stack.popRed();
        stack.popRed();
        stack.pushBlue(new StackColor(Color.Blue));
        stack.pushBlue(new StackColor(Color.Blue));
        stack.pushBlue(new StackColor(Color.Blue));
        stack.pushBlue(new StackColor(Color.Blue));
        stack.printStack();
        System.out.println("\t\t<<Pop 4 Red ");
        stack.popRed();
        stack.popRed();
        stack.popRed();
        stack.popRed();
        stack.printStack();

        System.out.println("\t\t---Create New Stack ---");
        stack = new RedBlueDoubleStack<>();
        System.out.println("\t\t>>Push 1 Blue ---");

        stack.pushBlue(new StackColor(Color.Blue));
        stack.printStack();
        System.out.println("\t\t>>Push 1 Red");
        stack.pushRed(new StackColor(Color.Red));
        stack.printStack();

        System.out.println("\t\tTest both top functions.");
        System.out.println("\t\t\tRed Top: " +stack.topRed() + " Blue Top: " + stack.topBlue());

        System.out.println("\n\t\t<<Pop 1 Blue; <<Pop 1 Red");
        stack.popBlue();
        stack.popRed();
        stack.printStack();


        System.out.println("\t\tPush Wrong Color to Stack - <<Should Throw Exception>>");
        try{
        stack.pushBlue(new StackColor(Color.Red));
        }catch (IllegalStateException e){
            System.out.println("\t\tCaptured expected Exception: " + e);
        }



    }

    /**A method to display a visual representation of the double stack ADT */
    private void printStack() {
        System.out.println("----------------------------------------");
        System.out.println("---------------------------");
        System.out.println("Total Stack Size:\t" + size());
        System.out.println("Red Stack Size:  \t" + redSize);
        System.out.println("Blue Stack Size: \t" + blueSize);
        System.out.println("---------------------------");

        if (redSize > 0) {
            for (int i = 0; i < redSize; i++) {
                // E[] d = stack.data;
                StackColor c = (StackColor) data[i];
                System.out.println( c );
            }
        } else {
            System.out.println("[empty]");
        }
        System.out.println("-------red stack top------");
        System.out.println("-------blue stack top-----");

        if (blueSize > 0) {
            for (int j = data.length - blueSize; j < data.length; j++) {
                StackColor c = (StackColor) data[j];
                System.out.println( c );
            }
        } else {
            System.out.println("[empty]");
        }
    }

    public RedBlueDoubleStack() {
        data = (E[]) new Object[INITIAL_CAPACITY];
    }

    public int size() {
        return redSize + blueSize;
    }

    public boolean isEmpty() {
        return redSize + blueSize == 0;
    }

    public void pushRed(E e) throws IllegalStateException {
        if (((StackColor) e).getColor() != Color.Red) {
            throw new IllegalStateException("Cannot push color to Red Stack: " + ((StackColor) e).getColor());
        }

        checkSize();

        data[redSize] = e;
        redSize++;
    }

    public void pushBlue(E e) throws IllegalStateException {
        if (((StackColor) e).getColor() != Color.Blue) {
            throw new IllegalStateException("Cannot push color to Blue Stack: " + ((StackColor) e).getColor());
        }
        checkSize();

        data[(data.length - 1) - blueSize] = e;
        blueSize++;
    }

    /**
     * Checks the size of the stack and doubles it if it is currently at capacity
     */
    private void checkSize() {
        if (redSize + blueSize == data.length) {

            int newSize = 2 * data.length;

            E[] temp = (E[]) new Object[newSize];

            //Copy any red tokens
            if (redSize > 0) {
                for (int i = 0; i < redSize; i++) {
                    temp[i] = data[i];
                }
            }
            //copy any blue tokens.
            if (blueSize > 0) {
                for (int j = data.length - blueSize; j < data.length; j++) {
                    temp[j + newSize / 2] = data[j];
                }
            }
            data = temp;
        }
    }

    public E topRed() {
        if (redSize == 0) {
            return null;
        }
        return data[redSize - 1];
    }

    public E topBlue() {
        if (blueSize == 0) {
            return null;
        }
        return data[data.length - blueSize];
    }

    public E popRed() {
        if (redSize == 0) {
            return null;
        }
        E red = data[redSize - 1];
        redSize--;
        return red;
    }

    public E popBlue() {
        if (blueSize == 0) {
            return null;
        }
        E blue = data[data.length - blueSize];
        blueSize--;
        return blue;
    }

}
