package fr.Infuseting.entity;

import fr.Infuseting.util.JSONObject;

/**
 * extends the Entity class
 * Represents a monster with a name and its Entity stats
 * @author Antoine
 */
public class Monster extends Entity {
    public String name;

    /**
     * Creates a monster from a json object.
     * @param json The json object which contains the monster data. (name, HP, attack, armor...)
     * @return the created monster.
     */
    public static Monster createMonsterFromJSON(JSONObject json) {
        Monster monster = new Monster();
        monster.name = json.getString("name");
        monster.maximumHP = (Integer) json.getNumber("HP");
        monster.currentHP = monster.maximumHP;
        monster.armor = (Integer) json.getNumber("Armor");
        monster.attack = (Integer)json.getNumber("Attack");
        return monster;
    }

    /**
     * Convert monster data into String json.
     * @return monster data in json String format.
     */
    public String asJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("HP", maximumHP);
        json.put("Armor", armor);
        json.put("Attack", attack);
        return json.toString();
    }

    /**
     * Convert monster data into String json.
     * @return monster data in json String format.
     */
    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("HP", maximumHP);
        json.put("Armor", armor);
        json.put("Attack", attack);
        return json.toString();
    }
}
