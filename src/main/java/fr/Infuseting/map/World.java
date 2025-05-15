package fr.Infuseting.map;

import fr.Infuseting.entity.Monster;
import fr.Infuseting.util.JSONArray;
import fr.Infuseting.util.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents the universe where the story takes place
 * Provides methods to create
 * @author Mattheo
 */
public class World {

    private String name;

    public HashMap<Place, HashMap<Path, Place>> cache = new HashMap<>();

    /**
     * Constructs a world with the specified name
     * @param name the name of the World
     */
    public World(String name) {
        this.name = name;
    }

    public String getString() {
        return name;
    }

    public void setString(String name) {
        this.name = name;
    }

    /**
     * Indicates the adjacent places of the specified place
     * @param place The selected place
     * @return the List of places that are adjacent to the specified place
     */

    public List<Place> getAdjacentsPlace(Place place) {
        if (!cache.containsKey(place)) return new ArrayList<>();
        return new ArrayList<>(cache.get(place).values());
    }

    /**
     * Converts the World into the json format.
     * @return the World in the json format.
     */
    public String toJson() {
        JSONObject json = new JSONObject();
        json.put("world", name);
        json.put("places", new JSONArray());
        for (Place place : cache.keySet()) {
            JSONObject placeJson = new JSONObject();
            placeJson.put("id", place.getId());
            placeJson.put("name", place.getName());
            placeJson.put("description", place.getDescription());
            placeJson.put("end", place.isEnd());
            placeJson.put("start", place.isStart());
            placeJson.put("defeat", place.isDefeat());
            placeJson.put("monster", place.getMonster() != null ? place.getMonster().toString() : null);
            json.getJSONArray("places").add(placeJson);
        }
        json.put("paths", new JSONArray());
        for (Place place : cache.keySet()) {
            for (Path path : cache.get(place).keySet()) {
                JSONObject pathJson = new JSONObject();
                pathJson.put("firstPlace", place.getId());
                pathJson.put("secondPlace", cache.get(place).get(path).getId());
                pathJson.put("length", path.length);
                json.getJSONArray("paths").add(pathJson);
            }
        }
        return json.toString();

    }

    /**
     * Loads a world from a json object
     * @param json the json object which the world is loaded from.
     */
    public static World loadJson(JSONObject json) {
        World newWorld = new World(json.getString("world"));
        JSONArray placesJson = json.getJSONArray("places");

        for (Object placeObject : placesJson.getData()) {
            JSONObject placeJson = (JSONObject) placeObject;
            Monster monster;
            try {
                monster = Monster.createMonsterFromJSON(placeJson.getJSONObject("monster"));
            } catch (ClassCastException e) {
                monster = null;
            }
            Place place = new Place(
                    (Integer) placeJson.getNumber("id"),
                    placeJson.getString("name"),
                    monster,
                    placeJson.getString("description"),
                    placeJson.getBoolean("end"),
                    placeJson.getBoolean("start"),
                    placeJson.getBoolean("defeat")
            );
            newWorld.addPlace(place);
        }

        JSONArray pathsJson = json.getJSONArray("paths");
        for (Object pathObject : pathsJson.getData()) {
            JSONObject pathJson = (JSONObject) pathObject;
            Place firstPlace = newWorld.getPlaceFromId((Integer) pathJson.getNumber("firstPlace"));
            Place secondPlace = newWorld.getPlaceFromId((Integer) pathJson.getNumber("secondPlace"));
            Path path = new Path(firstPlace, secondPlace, (Integer) pathJson.getNumber("distance"));
            try {
                newWorld.addPath(path);
            } catch (UnKnownPlaceException e) {
                e.printStackTrace();
            }
        }
        return newWorld;
    }

    /**
     * Adds a place to the place List.
     * @param place the place that is being added to the List.
     */
    public void addPlace(Place place) {
        if (cache.containsKey(place)) {
            throw new LieuEnDoubleException("Le lieu '" + place.getName() + "' existe déjà dans le monde.");
            }
        if (getPlaceFromId(place.getId()) != null) {
            throw new LieuEnDoubleException("Le lieu avec l'ID '" + place.getId() + "' existe déjà dans le monde.");
        }
        place.setWorld(this);
        cache.put(place, new HashMap<>());
    }

    /**
     * Adds a path between the first place and the second place
     * @param path the specified path that is being added
     * @throws UnKnownPlaceException
     */
    public void addPath(Path path) throws UnKnownPlaceException {
        if (path == null) {
            throw new IllegalArgumentException("Le chemin ne peut pas être nul.");
        }
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

    /**
     * Gets a Place object from a specified name
     * @param name The name of the Place that is specified
     * @return the Place object that corresponds to the specified name.
     */
    public Place getPlaceFromName(String name) {
        for (Place p : cache.keySet()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Gets a Place object from a specified id
     * @param id The id of the Place that is specified
     * @return the Place object that corresponds to the specified id.
     */
    public Place getPlaceFromId(int id) {
        for (Place p : cache.keySet()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Gets a Path object from a specified Place
     * @param place The Place that is specified
     * @return the Path object that corresponds to the specified Place.
     */
    public HashMap<Path, Place> getPathsFrom(Place place) {
        return cache.getOrDefault(place, new HashMap<>());
    }

    /**
     * This methods returns the Place
     * @param from the first place that is being checked
     * @param to the last place that is being checked
     * @return the place if it is Adjacent to one of the places between the from Place and the to Place
     * @throws EstPasAdjacent
     */
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

    /**
     * Removes the specified path
     * @param path the path that is to be destroyed
     * @throws UnKnownPlaceException
     */
    public void removePath(Path path) throws UnKnownPlaceException {
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

    /**
     * Removes the specified place
     * @param place the place that is to be destroyed
     * @throws UnKnownPlaceException
     */
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

    /**
     * Checks if the specified path exists or not
     * @param path the path that is to be checked
     * @throws PathNotFoundException
     */
    public void checkPathExists(Path path) throws PathNotFoundException {
        if (path == null) {
            throw new IllegalArgumentException("Le chemin ne peut pas être nul.");
        }
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
