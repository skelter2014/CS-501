import java.util.Stack;

/**
 * A two ended queue that functions as a double stack to hold Color objects. Red
 * objects
 * are pushed to the left side (front) of the queue and Blue objects are pushed
 * to the right side(end) of the queue.
 */
public class RedBlueDoubleStack<E> implements RedBlueStackInterface<E> {

    /** Use a double ended queue Deque as a two-sided stack */

    E[] data;
    private final int INITIAL_CAPACITY = 1;

    private int blueSize = 0;
    private int redSize = 0;

    /** Test a double sided stack */
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

        stack.popBlue();
        stack.popRed();

        stack.printStack();
        stack.popBlue();
        stack.popBlue();
        stack.popBlue();
        stack.printStack();

    }

    private void printStack() {
        System.out.println("----------------------------------------");
        System.out.println("Total Stack Size:\t" + size());
        System.out.println("Red Stack Size:  \t" + redSize);
        System.out.println("Blue Stack Size: \t" + blueSize);
        System.out.println("----------------------------------------");

        if (redSize > 0) {
            for (int i = 0; i < redSize; i++) {
                // E[] d = stack.data;
                StackColor c = (StackColor) data[i];
                System.out.println(c);
            }
        }
        System.out.println("------top------");

        if (blueSize > 0) {
            for (int j = data.length - blueSize; j < data.length; j++) {
                StackColor c = (StackColor) data[j];
                System.out.println(c);
            }
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

            if (redSize > 0) {
                for (int i = 0; i < redSize; i++) {
                    temp[i] = data[i];
                }
            }
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
