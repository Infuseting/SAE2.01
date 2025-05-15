package fr.Infuseting.map;

/**
 * Exception thrown when a place is already present in the world
 * @author Mattheo
 */
public class LieuEnDoubleException extends RuntimeException {
    public LieuEnDoubleException(String message) {
        super(message);
    }
}
