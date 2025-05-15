package fr.Infuseting.map;

/**
 * Exception thrown when a path is not found
 * @author Mattheo
 */
public class PathNotFoundException extends Exception {
    public PathNotFoundException(String message) {
        super(message);
    }
}
