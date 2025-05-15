package fr.Infuseting.fight;

import fr.Infuseting.entity.Entity;
import fr.Infuseting.entity.Player;

/**
 * Represents a spell that is used by the player to increase their attack power.
 */
public class Strength extends Spell{

    public Strength(){
        super(5,5,25,10, "Strength");
    }

    @Override
    public boolean isSelfSpell() {
        return true; // ce sort s'applique à l'utilisateur
    }

    @Override
    public void specificEffect(Entity entity) {
        if (duration == nbTurnLeft) {
            entity.setAttack(entity.getAttack()+amount);
            System.out.println(entity.getAttack());
        }
        else if (nbTurnLeft == 0) {
            entity.setAttack(entity.getAttack()-amount); // Si le sort n'est plus actif, on retire l'attaque
        }
        this.nbTurnLeft--;
        // Le joueur qui utilise ce sort voit son attaque augmentée

    }
}
