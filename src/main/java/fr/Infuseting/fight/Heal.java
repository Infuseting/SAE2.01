package fr.Infuseting.fight;

import fr.Infuseting.entity.Entity;
import fr.Infuseting.entity.Player;

public class Heal extends Spell{
    public Heal(){
        super(1,1,25,20, "Heal");
    }


    @Override
    public boolean isSelfSpell() {
        return true;
    }


    @Override
    public void specificEffect(Entity entity) {

        if(entity instanceof Player){         //Ce sort ne s'applique qu'au Player

            if(entity.getCurrentHP() + amount > entity.getMaximumHP()){ // Si les hp du joueur plus le sort son supérieur a son maximum d'hp
                entity.setCurrentHP(entity.getMaximumHP()); // alors son nombre d'hp est desormais son maximum
            }
            else {
                entity.setCurrentHP(entity.getCurrentHP()+amount); //sinon le sort est ajouté au montant d'hp
            }


        }

        this.nbTurnLeft--;

    }
}
