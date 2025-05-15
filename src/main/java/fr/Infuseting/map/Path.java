package fr.Infuseting.map;

/**
 * Class representing a path between two places
 * @author Mattheo
 */
public class Path {
    public Place firstPlace;
    public Place secondPlace;
    public int length;

    public Path(Place firstPlace, Place secondPlace, int length) {
        if (firstPlace == null || secondPlace == null) {
            throw new IllegalArgumentException("Places cannot be null");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
        this.length = length;
    }
}
