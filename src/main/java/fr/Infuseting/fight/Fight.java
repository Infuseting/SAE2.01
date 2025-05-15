package fr.Infuseting.fight;

import fr.Infuseting.entity.Entity;
import fr.Infuseting.entity.Monster;
import fr.Infuseting.entity.Player;

public class Fight {
    private final Player player;
    private final Monster monster;

    public Fight() {
        player = new Player();
        monster = new Monster();


    }



    public Entity turn(Spell spell){
        if(spell==null) player.attack(monster);
        else player.castSpell(spell, monster);

        if(monster.isDead()) {
            return player;
        }

        monster.attack(player);

        if(player.isDead()) return monster;
        else return null;
    }

    public Entity newTurn(){
        return null;
    }
}
