package fr.Infuseting.map;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class world {
    private String name;
    public HashMap<Place, HashMap<Path, Place>> cache;

    public world(String name, HashMap<Place, HashMap<Path, Place>> cache) {
        this.name = name;
        this.cache = cache != null ? cache : new HashMap<>();
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

    public List<Place> getAdjacentsPlace(Place place) {
        if (!cache.containsKey(place)) return new ArrayList<>();
        return new ArrayList<>(cache.get(place).values());
    }
}
