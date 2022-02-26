

/** Custom Stack Interface to tracks two color stack */
public interface RedBlueStackInterface<E> {
    int size();

    boolean isEmpty();

    /**Pushes a Red item onto the Red stack. Throws an exception if trying to push another color */
    void pushRed(E e) throws IllegalStateException;

    void pushBlue(E e) throws IllegalStateException;

    E topRed();

    E topBlue();

    E popRed();

    E popBlue();
}
enum Color {
    Red,
    Blue
}
class StackColor {

    //Each instance of a stack color is given a unique id.
    static int counter = 0;

    public StackColor(Color color){
        this.color = color;
        id = counter ++;

    }


    private Color color;
    int id;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;

    }

    public String toString(){
        return String.format("[%s]\t", color) + String.format("%03d",id);
    }

    }

