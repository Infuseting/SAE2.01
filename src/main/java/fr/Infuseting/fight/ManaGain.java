package fr.Infuseting.fight;

import fr.Infuseting.entity.Entity;
import fr.Infuseting.entity.Player;

/**
 * Represents a spell that increases the player's current amount of mana.
 */
public class ManaGain extends Spell{
    public ManaGain(){
        super(1,1,25,5);
    }

    @Override
    public void specificEffect(Entity entity) {
        if(entity instanceof Player){         //Ce sort ne s'applique qu'au Player

            if (((Player) entity).currentMana + amount > ((Player) entity).maximumMana){ //Si son mana + le sort dépasse son mana max
                ((Player) entity).currentMana = ((Player) entity).maximumMana;          //alors son nouveau mana = manamax
            }else{
                ((Player) entity).currentMana += amount;                 //sinon son mana = mana + sort de gain de mana
            }
        }
    }

    @Override
    public boolean isSelfSpell() {
        return true;                // c'est un sort qui s'applique à soi même
    }
}
