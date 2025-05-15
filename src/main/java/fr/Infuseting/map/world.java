package fr.Infuseting.map;

import java.nio.file.Path;
import java.util.HashMap;

public class world {
    private String name;
    public HashMap<Place,Hashmap<Path,Place>> cache;

    public world(String name, Hashmap<Place,HashMap<Path,Place>> cache){
        this.name = name;
        this.cache = cache;
    }




    public String getString(){
        return name;
    }

    public void addPlace(Place place){
        cache.add(place);
    }
    public void addPath(Path path){

    }

    public Place getPlaceFromName(String name){
        return new Place();
    }

    public Place getPlaceFromId(int id){
        return new Place();
    }

    public Hashmap<Path,Place> getPathsFrom(Place place){
        return new HashMap<Path,Place>();
    }
}
