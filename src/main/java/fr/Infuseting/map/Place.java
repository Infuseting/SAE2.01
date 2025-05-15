package fr.Infuseting.map;

import fr.Infuseting.entity.Monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a Place in a World.
 * A World can have multiple Places but a Place can only have one World.
 * @author Mattheo
 */
public class Place {
    private int id;
    private String name;

    private Monster monster;

    private String description;

    private boolean isEnd;

    private boolean isStart;

    private boolean isDefeat;
    private World world;

    /**
     * Creates a Place with the specified parameters.
     * @param id the identification number of the Place.
     * @param name the name of the Place.
     * @param monster The monster that is present in the Place. (Can be null)
     * @param description The description of the Place.
     * @param end The Place where the player needs to be in order to win.
     * @param start the Place where the player begins the game.
     * @param defeat a Place where the player dies and loses (GAME OVER !!).
     */
    public Place(int id, String name, Monster monster, String description, boolean end, boolean start, boolean defeat) {
        this.id = id;
        this.name = name;
        this.monster = monster;
        this.description = description;
        this.isEnd = end;
        this.isStart = start;
        this.isDefeat = defeat;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
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

    public HashMap<Path, Place> getPaths() {
        if (world == null) {
            throw new MondeNonCreeException("Le lieu '" + name + "' n'appartient à aucun monde."
         );
      }
    return world.getPathsFrom(this);
    }

    /**
     * Returns the List of adjacent places.
     * @return the List of adjacent places
     */
    public List<Place> getAdjacentsPlace() {
        if (world != null) {
            return world.getAdjacentsPlace(this);
        }
        return new ArrayList<>();
    }

    /**
     * Checks whether or not the specified object is identical to this Place.
     * @param obj The specified object.
     * @return true if both objects are equal and false if they are not.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Place)) return false;
        Place other = (Place) obj;
        return this.id == other.id;
    }

    /**
     * Returns the hash code value for this list.
     * @return the hash code value for this list.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}