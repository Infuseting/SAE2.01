package fr.Infuseting.fight;

import fr.Infuseting.entity.Entity;

/**
 * Represents a spell that is used by the player to inflict on a monster the poison effect.
 * @author Achille
 */
public class Poison extends Spell{
    public Poison(){
        super(5,5,25,5, "Poison");
    }

    @Override
    public boolean isSelfSpell() {
        return false; // ce sort s'applique à l'adversaire
    }

    @Override
    public void specificEffect(Entity entity) {
        entity.setCurrentHP(entity.getCurrentHP()-amount); // Les degats du poison sont soustrait au currentHp du joueur
        this.nbTurnLeft--;
    }
}
