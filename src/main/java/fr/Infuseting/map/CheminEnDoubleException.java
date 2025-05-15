package fr.Infuseting.map;

/**
 * Exception thrown when a path is already present in the world
 * @author Mattheo
 */
public class CheminEnDoubleException  extends RuntimeException {
    public CheminEnDoubleException(String message) {
        super(message);
    }
}
