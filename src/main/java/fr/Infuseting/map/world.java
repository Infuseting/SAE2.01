package fr.Infuseting.map;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class world {
    private String name;
    public HashMap<Place, HashMap<Path, Place>> cache = new HashMap<>();

    public world(String name) {
        this.name = name;
    }

    public String getString() {
        return name;
    }

    public void addPlace(Place place) {
        if (!cache.containsKey(place)) {
            place.setWorld(this);
            cache.put(place, new HashMap<>());

        }
    }


    public void addPath(Path path) throws UnKnownPlaceException {
        Place first = path.firstPlace;
        Place second = path.secondPlace;

        if (!cache.containsKey(first)) {
            throw new UnKnownPlaceException("Premier lieu non trouvé : " + first.getName());
        }
        if (!cache.containsKey(second)) {
            throw new UnKnownPlaceException("Deuxième lieu non trouvé : " + second.getName());
        }

        cache.get(first).put(path, second);
        cache.get(second).put(path, first);
    }

    public Place getPlaceFromName(String name) {
        for (Place p : cache.keySet()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public Place getPlaceFromId(int id) {
        for (Place p : cache.keySet()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public HashMap<Path, Place> getPathsFrom(Place place) {
        return cache.getOrDefault(place, new HashMap<>());
    }

    public Place getPlaceIfAdjacent(Place from, Place to) throws EstPasAdjacent {
        if (!cache.containsKey(from)) {
            throw new EstPasAdjacent("Le lieu source '" + from.getName() + "' n'existe pas dans le monde.");
        }

        Place found = null;
        for (Place p : cache.get(from).values()) {
            if (p.equals(to)) {
                found = p;
                break;
            }
        }

        if (found == null) {
            throw new EstPasAdjacent("Le lieu '" + to.getName() + "' n'est pas adjacent à '" + from.getName() + "'");
        }

        return found;
    }
}
