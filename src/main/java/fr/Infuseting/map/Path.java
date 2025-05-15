package fr.Infuseting.map;

public class Path {
    public Place firstPlace;
    public Place secondPlace;
    public int length;

    public Path(Place firstPlace, Place secondPlace, int length) {
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
        this.length = length;
    }
}
