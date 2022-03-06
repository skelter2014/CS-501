
/** Custom Stack Interface to tracks two color stack */
public interface RedBlueStackInterface<E> {
    int size();

    boolean isEmpty();

    /**
     * Pushes a Red item onto the Red stack. Throws an exception if trying to push
     * another color
     */
    void pushRed(E e) throws IllegalStateException;

    /**
     * Pushes a Blue token onto the Blue stack. Throws an exception if trying to
     * push another color
     */
    void pushBlue(E e) throws IllegalStateException;

    /** Returns the top Red Token */
    E topRed();

    /** Returns the top Blue Token */
    E topBlue();

    /** Pops the top red token from the stack and returns it. */
    E popRed();

    /** Pops the top blue token from the stack and returns it. */
    E popBlue();
}

/**An enumeration of acceptible colors for the Red/Blue stack. */
enum Color {
    Red,
    Blue
}

/**
 * A custom class the encapsulates a color object put on the stack. Uses an
 * internal enum to allow only accepted colors.
 */
class StackColor {

    // Each instance of a stack color is given a unique id.
    static int counter = 0;

    public StackColor(Color color) {
        this.color = color;
        id = counter++;

    }

    private Color color;
    //each StackColor object is given a unique identifier.
    int id;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String toString() {
        return String.format("[%s]\t", color) + String.format("%03d", id);
    }

}
