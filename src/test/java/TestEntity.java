import fr.Infuseting.entity.Entity;
import fr.Infuseting.entity.Monster;
import fr.Infuseting.entity.Player;
import fr.Infuseting.fight.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestEntity {

    //Test de la Classe Joueur
    @Test
    public void testConstructeurJoueur1(){
        Player joueur = new Player();
        assertTrue(joueur instanceof  Entity);
    }
    @Test
    public void testConstructeurJoueur2(){
        Entity joueur = new Player();
        assertTrue(joueur instanceof  Player);
    }

    @Test
    public void testConstructeurJoueur3(){
        Player joueur = new Player(60,20,10,50,80,120);
        assertTrue(joueur.getCurrentHP() == 60);
        assertTrue(joueur.getMaximumHP() == 20);
        assertTrue(joueur.getArmor() == 10);
        assertTrue(joueur.getAttack() == 50);
        assertTrue(joueur.currentMana == 80);
        assertTrue(joueur.maximumMana == 120);

    }
    @Test
    public void testConstructeurJoueur4(){

        List<Spell> test = List.of(new Heal());
        Player joueur = new Player(60,20,10,50,80,120,test);
        assertTrue(joueur.getCurrentHP() == 60);
        assertTrue(joueur.getMaximumHP() == 20);
        assertTrue(joueur.getArmor() == 10);
        assertTrue(joueur.getAttack() == 50);
        assertEquals(joueur.spells,test);


    }

    @Test
    public void testListeSpellPlayer(){
        Player player = new Player();
        boolean heal = false;
        boolean shield = false;
        boolean poison = false;
        boolean mana = false;
        boolean attaque = false;
        for (Spell spl : player.spells){
            if(spl instanceof Heal) heal = true;
            if(spl instanceof Shield) shield = true;
            if(spl instanceof Poison) poison = true;
            if(spl instanceof ManaGain) mana = true;
            if(spl instanceof Strength) attaque = true;

        }
        assertTrue(heal && shield && poison && mana && attaque);
    }


    @Test
    public void testIsDead1(){
        Player joueur = new Player(15,20,10,50,80,120);
        assertFalse(joueur.isDead());
    }

    @Test

    public void testIsDead2(){
        Player joueur = new Player(0,20,10,50,80,120);
        assertTrue(joueur.isDead());
    }


    @Test
    public void testIsAlive1(){
        Player joueur = new Player(0,20,10,50,80,120);
        assertFalse(joueur.isAlive());
    }

    @Test
    public void testIsAlive2(){
        Player joueur = new Player(1,20,10,50,80,120);
        assertTrue(joueur.isAlive());
    }



    //Test de la classe Monster
    @Test
    public void testConstructeurMonster1(){
        Monster monster = new Monster();
        assertTrue(monster instanceof  Entity);
    }

    @Test
    public void testConstructeurMonster2(){
        Entity monster = new Monster();
        assertTrue(monster instanceof  Monster);
    }
}


