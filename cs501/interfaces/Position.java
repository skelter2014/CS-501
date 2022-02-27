package cs501.interfaces;

/**
 * Returns the element stored at this position
 */
public interface Position<E> {
    E getElement() throws IllegalStateException;
}

