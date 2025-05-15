import fr.Infuseting.entity.Player;
import fr.Infuseting.fight.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSpell {

    @Test
    public void testSelfSpell() {
        Poison spl = new Poison();
        assertFalse(spl.isSelfSpell());
        Heal heal = new Heal();
        assertTrue(heal.isSelfSpell());
        Shield shld = new Shield();
        assertTrue(shld.isSelfSpell());
        ManaGain mana = new ManaGain();
        assertTrue(mana.isSelfSpell());
        Strength force = new Strength();
        assertTrue(force.isSelfSpell());

    }


    @Test //verifie si le mana du joueur se met bien à jour
    public void testApplyMana(){
        Player pl = new Player(50,50,35,10,100,100);
        Spell spl = new Poison();
        spl.apply(pl);
        assertEquals(pl.currentMana,95);




    }

    @Test //verifie si le joueur ne peut pas lancer son sort si il n'a pas assez de mana
    public void testApplyNoEnoughMana(){
        Player pl = new Player(50,50,35,10,2,100);
        Spell spl = new Poison();
        assertFalse(spl.apply(pl));


    }
    @Test //verifie si le joueur ne perd pas de mana si le sort a déja été lancé
    public void testApplyMana2(){
        Player pl = new Player(50,50,35,10,10,100);
        Spell spl = new Poison();
        spl.apply(pl);
        spl.apply(pl);
        spl.apply(pl);

        assertEquals(pl.currentMana, 0);

    }

    @Test //verifie que le nombre de tour decremente
    public void testApplyTourLeft(){
        Player pl = new Player(50,50,35,10,10,100);
        Spell spl = new Poison();
        spl.apply(pl);
        spl.apply(pl);

        assertEquals(spl.getNbTurnLeft(),0);

    }

    @Test //verifie que le nombre de tour une fois a 0 retourne false
    public void testApplyTourLeft2(){
        Player pl = new Player(50,50,35,10,10,100);
        Spell spl = new Poison();
        spl.apply(pl);
        spl.apply(pl);
        spl.apply(pl);
        spl.apply(pl);
        spl.apply(pl);
        assertFalse(spl.apply(pl));


    }


    @Test //verifie que le poison est bien appliqué
    public void testSpecificEffectPoison(){

        Player pl = new Player(50,50,35,10,10,100);
        Spell spl = new Poison();
        spl.specificEffect(pl);
        assertEquals(pl.getCurrentHP(),25);


    }
    @Test //verifie que le poison est bien appliqué au premier et deuxieme tour
    public void testSpecificEffectPoison2(){

        Player pl = new Player(500,500,35,10,10,100);
        Spell spl = new Poison();
        spl.specificEffect(pl);
        spl.specificEffect(pl);
        assertEquals(pl.getCurrentHP(),450);


    }

    @Test //verifie que le heal est bien appliqué
    public void testSpecificEffectHeal(){

        Player pl = new Player(10,50,35,10,10,100);
        Spell spl = new Heal();
        spl.specificEffect(pl);
        assertEquals(pl.getCurrentHP(),35);


    }

    @Test //verifie que le heal est bien appliqué avec un heal qui depasse le max d'hp
    public void testSpecificEffectHeal2(){

        Player pl = new Player(40,50,35,10,10,100);
        Spell spl = new Heal();
        spl.specificEffect(pl);
        assertEquals(pl.getCurrentHP(),50);


    }

    @Test //verifie que le force est bien appliqué avec un heal qui depasse le max d'hp
    public void testSpecificEffectStrength(){

        Player pl = new Player(40,50,35,10,10,100);
        Spell spl = new Strength();
        spl.specificEffect(pl);
        assertEquals(pl.getAttack(),35);


    }


}