package fr.Infuseting.map;

/**
 * Exception thrown when a place is unknown
 * @author Mattheo
 */
public class UnKnownPlaceException extends Exception {
    public UnKnownPlaceException(String message) {
        super(message);
    }
}