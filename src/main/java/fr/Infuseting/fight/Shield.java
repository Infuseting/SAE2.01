package fr.Infuseting.fight;

import fr.Infuseting.entity.Entity;

/**
 * Represents a spell that is used by the player to increase their armor stat.
 */
public class Shield extends Spell{
    public Shield(){
        super(1,1,25,5, "Shield");
    }

    @Override
    public boolean isSelfSpell() {
        return true;            //le sort s'applique à l'utilisateur
    }

    @Override
    public void specificEffect(Entity entity) {

        entity.setArmor(entity.getArmor() + amount); //l'armure augmente en fonction du sort
        this.nbTurnLeft--;

    }


}
