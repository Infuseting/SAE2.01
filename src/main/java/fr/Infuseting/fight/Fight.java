package fr.Infuseting.fight;

import fr.Infuseting.entity.Entity;
import fr.Infuseting.entity.Monster;
import fr.Infuseting.entity.Player;

import java.util.List;
import java.util.Scanner;

public class Fight {
    private final Player player;
    private final Monster monster;

    public Fight(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }



    public Entity turn(Spell spell){


        if(spell==null) player.attack(monster);
        else if (spell.isSelfSpell()) player.castSpell(spell, player);
        else player.castSpell(spell, monster);

        if(monster.isDead()) {
            return player;
        }

        monster.attack(player);

        if(player.isDead()) return monster;
        else return null;
    }

    public Entity newTurn(){

        for ( int i = 0 ; i < player.getEffects().size(); i++){
            if (player.getEffects().get(i).nbTurnLeft == 0) {
                player.getEffects().remove(i);
                i--;
            }
        }
        for(Spell spell : player.getEffects()){
            spell.specificEffect(player);
        }

        if(player.isDead()) return monster;
        for(Spell spell : monster.getEffects()){
            spell.specificEffect(monster);

        }

        if(monster.isDead()) return player;




        return null;
    }

    public boolean isEnd() {
        return player.isDead() || monster.isDead();
    }

    public Player getPlayer() {
        return player;
    }
    public Monster getMonster() {
        return monster;
    }
}
