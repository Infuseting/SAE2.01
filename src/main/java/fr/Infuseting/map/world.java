package fr.Infuseting.map;

import org.jetbrains.annotations.NotNull;

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
        if (cache.containsKey(place)) {
            throw new LieuEnDoubleException("Le lieu '" + place.getName() + "' existe déjà dans le monde.");
            }
        place.setWorld(this);
        cache.put(place, new HashMap<>());
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

        if (cache.get(first).containsKey(path)) {
            throw new CheminEnDoubleException( "Le chemin entre '" + first.getName()+ "' et '" + second.getName() + "' existe déjà.");
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

    public void removePath(@NotNull Path path) throws UnKnownPlaceException {
        Place first = path.firstPlace;
        Place second = path.secondPlace;

        if (!cache.containsKey(first)) {
            throw new UnKnownPlaceException("Premier lieu non trouvé : " + first.getName());
        }
        if (!cache.containsKey(second)) {
            throw new UnKnownPlaceException("Deuxième lieu non trouvé : " + second.getName());
        }

        cache.get(first).remove(path);
        cache.get(second).remove(path);
    }

    public void removePlace(Place place) throws UnKnownPlaceException {
        if (!cache.containsKey(place)) {
            throw new UnKnownPlaceException("Lieu non trouvé : " + place.getName());
        }

        // Copier la liste des chemins pour éviter ConcurrentModificationException
        ArrayList<Path> attachedPaths = new ArrayList<>(cache.get(place).keySet());
        for (Path path : attachedPaths) {
            // removePath gère la suppression des deux côtés
            removePath(path);
        }

        cache.remove(place);
        place.setWorld(null);
    }

    public void checkPathExists(Path path) throws PathNotFoundException {
        Place first = path.firstPlace;
        Place second = path.secondPlace;
        if (!cache.containsKey(first)) {
            throw new PathNotFoundException(
                    "Lieu source non trouvé dans le monde : " + first.getName());
        }
        if (!cache.containsKey(second)) {
            throw new PathNotFoundException(
                    "Lieu destination non trouvé dans le monde : " + second.getName());
        }
        boolean inFirst = cache.get(first).containsKey(path);
        boolean inSecond = cache.get(second).containsKey(path);
        if (!(inFirst && inSecond)) {
            throw new PathNotFoundException("Le chemin entre '" + first.getName() + "' et '" + second.getName() + "' n'existe pas.");
        }
    }
}
