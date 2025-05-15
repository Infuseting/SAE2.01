package fr.Infuseting.map;

import fr.Infuseting.entity.Monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Place {
    private int id;
    private String name;

    private Monster monster;

    private String description;

    private boolean isEnd;

    private boolean isStart;

    private boolean isDefeat;

    public Place (int id, String name, Monster monster, String description, boolean end, boolean start, boolean defeat){
        this.id = id;
        this.name = name;
        this.monster = monster;
        this.description = description;
        this.isEnd = end;
        this.isStart = start;
        this.isDefeat = defeat;
    }




    public List<Place> getAdjacentsPlace(){

        return new ArrayList<Place>();
    }

    public HashMap<Path,Place> getPaths(){

        return new HashMap<Path,Place>();
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Monster getMonster() {

        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public boolean isStart() {

        return isStart;
    }

    public void setStart(boolean start) {

        isStart = start;
    }

    public boolean isDefeat() {
        return isDefeat;
    }

    public void setDefeat(boolean defeat) {
        isDefeat = defeat;
    }
}
