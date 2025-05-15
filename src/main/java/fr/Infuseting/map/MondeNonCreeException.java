package fr.Infuseting.map;

/**
 * Exception thrown when the world is not created
 * @author Mattheo
 */
public class MondeNonCreeException extends IllegalStateException {
    public MondeNonCreeException(String message) {
        super(message);
    }
}
