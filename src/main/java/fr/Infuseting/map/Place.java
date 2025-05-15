package fr.Infuseting.map;

import fr.Infuseting.entity.Monster;

import java.util.*;

public class Place implements List<Place> {
    private int id;
    private String name;
    private Monster monster;
    private String description;
    private boolean isEnd;
    private boolean isStart;
    private boolean isDefeat;
    private world world;

    public Place(int id, String name, Monster monster, String description, boolean end, boolean start, boolean defeat) {
        this.id = id;
        this.name = name;
        this.monster = monster;
        this.description = description;
        this.isEnd = end;
        this.isStart = start;
        this.isDefeat = defeat;
    }

    public void setWorld(world world) {
        this.world = world;
    }

    public world getWorld() {
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
        if (world != null) {
            return world.getPathsFrom(this);
        }
        return new HashMap<>();
    }

    public List<Place> getAdjacentsPlace() {
        if (world != null) {
            try {
                return world.getPlaceIfAdjacent(this,this);
            } catch (EstPasAdjacent e) {
                throw new RuntimeException(e);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Place> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(Place place) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Place> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends Place> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Place)) return false;
        Place other = (Place) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public Place get(int i) {
        return null;
    }

    @Override
    public Place set(int i, Place place) {
        return null;
    }

    @Override
    public void add(int i, Place place) {

    }

    @Override
    public Place remove(int i) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Place> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Place> listIterator(int i) {
        return null;
    }

    @Override
    public List<Place> subList(int i, int i1) {
        return null;
    }
}
