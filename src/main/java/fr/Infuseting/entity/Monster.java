package fr.Infuseting.entity;

import fr.Infuseting.util.JSONObject;

public class Monster extends Entity {
    public String name;

    public static Monster createMonsterFromJSON(JSONObject json) {
        Monster monster = new Monster();
        monster.name = json.getString("name");
        monster.maximumHP = (Integer) json.getNumber("HP");
        monster.armor = (Integer) json.getNumber("Armor");
        monster.attack = (Integer)json.getNumber("Attack");
        return monster;
    }

    public String asJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("HP", maximumHP);
        json.put("Armor", armor);
        json.put("Attack", attack);
        return json.toString();
    }


    @Override
    public void attack(Entity other) {

    }

    @Override
    public void isAlive() {

    }

    @Override
    public void isDead() {

    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("HP", maximumHP);
        json.put("Armor", armor);
        json.put("Attack", attack);
        return null;
    }

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
