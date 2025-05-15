package fr.Infuseting.fight;

import fr.Infuseting.entity.Entity;

public class Poison extends Spell{
    public Poison(){
        super(5,5,25,5);
    }

    @Override
    public boolean isSelfSpell() {
        return false; // ce sort s'applique à l'adversaire
    }

    @Override
    public void specificEffect(Entity entity) {
        entity.setCurrentHP(entity.getCurrentHP()-amount); // Les degats du poison sont soustrait au currentHp du joueur
    }
}
