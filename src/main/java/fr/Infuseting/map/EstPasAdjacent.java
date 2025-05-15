package fr.Infuseting.map;

/**
 * Exception thrown when a path is not adjacent to the specified place
 * @author Mattheo
 */
public class EstPasAdjacent extends Exception {
    public EstPasAdjacent(String message) {
        super(message);
    }
}
