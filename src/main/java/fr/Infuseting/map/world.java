package fr.Infuseting.map;

import fr.Infuseting.map.Path;

import java.util.HashMap;

public class world {
    private String name;
    public HashMap<Place, HashMap<Path,Place>> cache;

    public world(String name, HashMap<Place,HashMap<Path,Place>> cache){
        this.name = name;
        this.cache = cache;
    }

    public String getString(){
        return name;
    }

    public void addPlace(Place place){
    }

    //rajoute un chemin nouvellement crée en se basant qur des lieux déjà existant(Exception)
    public void addPath (Path path) throws UnKnownPlaceException {
            Place first = path.firstPlace;
            Place second = path.secondPlace;
            //erreur du premier lieu qui ne serait pas trouvé
            if (!cache.containsKey(first)) {
                throw new UnKnownPlaceException("Premier lieu non trouvé : " + first.getName());
            }
            //erreur du deuxième lieu qui ne serait pas trouvé
            if (!cache.containsKey(second)) {
                throw new UnKnownPlaceException("Deuxième lieu non trouvé : " + second.getName());
            }
            cache.get(first).put(path, second);
            cache.get(second).put(path, first);
    }

    public Place getPlaceFromName(String name){
        return null;
    }

    public Place getPlaceFromId(int id){
        return null;
    }

    public HashMap<Path,Place> getPathsFrom(Place place){
        return new HashMap<Path,Place>();
    }
}
