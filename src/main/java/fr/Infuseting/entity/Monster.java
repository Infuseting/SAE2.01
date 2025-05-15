package fr.Infuseting.entity;

import org.json.JSONObject;

public class Monster extends Entity {
    public String name;

    public Monster(){
        super();
    };
    public Monster(String name){
        super();
        this.name = name;
    }

    public void createMonsterFromJSON(JSONObject json) {

    }
    public String asJson() {
        return "";
    }




}
