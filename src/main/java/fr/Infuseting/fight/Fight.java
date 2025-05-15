package fr.Infuseting.fight;

import fr.Infuseting.entity.Entity;
import fr.Infuseting.entity.Monster;
import fr.Infuseting.entity.Player;

/**
 * Represents a fight between the player and monster that get stored into that class.
 * Provides methods to simulate a turn-by-turn type fight between the player and the monster where the player strikes first
 * @author Achille
 */
public class Fight {
    private final Player player;
    private final Monster monster;

    /**
     * Constructs a fight between the specified player and monster
     */
    public Fight(Player player, Monster monster) {
        if (player == null || monster == null) {
            throw new IllegalArgumentException("Player and monster cannot be null");
        }
        this.player = player;
        this.monster = monster;
    }


    /**
     * Starts a turn of the fight where the player starts and casts a spell. If the monster did not die from the attack, then it will strike back the player.
     * @param spell Specifies the spell that is used by the player. If the spell is null, the player will use his base attack.
     * @return the winner of this turn. If nobody died, null is returned.
     */
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

    /**
     * Applies the effects of the spells present in the effects List in the Entity class before the turn started.
     * @return  the winner of this turn. If nobody died, null is returned.
     */
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

    /**
     *
     * @return the player of this Fight.
     */
    public boolean isEnd() {
        return player.isDead() || monster.isDead();
    }

    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @return the monster of this Fight.
     */
    public Monster getMonster() {
        return monster;
    }
}
