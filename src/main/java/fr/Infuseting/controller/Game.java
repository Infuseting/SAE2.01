package fr.Infuseting.controller;

import fr.Infuseting.entity.Monster;
import fr.Infuseting.entity.Player;
import fr.Infuseting.fight.Fight;
import fr.Infuseting.fight.Spell;
import fr.Infuseting.map.Place;
import fr.Infuseting.map.World;
import fr.Infuseting.map.WorldIO;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Main class for the game logic.
 * Handles the game flow, player actions, and interactions with the world.
 */
public class Game {
    /**
     * Entry point of the application.
     * Starts the game by calling the play method.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        play();

    }
    /**
     * Main game loop.
     * Handles the game flow, including map selection, player actions, and interactions with the world.
     */
    public static void play() {
        //TODO : Demander au joueur de choisir une carte

        World world = chooseMap();




        //Presentation

        //LOOP TANT QU'ON EST PAS SUR LA FIN.

        boolean fin = false;
        System.out.println("Welcome in " + world.getString());
        Place st = null;
        for( Place p : world.cache.keySet()){
            if(p.isStart()){
                System.out.println("You are in " + p.getName());
                st = p;
                System.out.println(p.getDescription());

            }
        }
        Player player = new Player(100, 100, 20, 10, 100, 100);
        do {
            chooseUserAction();
            st = choosePlace(world, st);
            Monster monster = managerPlace(world, st);
            if (st.isDefeat()) {
                fin = true;
            }
            if (monster != null) {
                player = fightMonster(player, monster);
                if (player.isDead()) {
                    System.out.println("You are defeated");
                    System.out.println("Game Over");
                    fin = true;
                } else {
                    System.out.println("You won the fight!");
                    System.out.println("You can continue your adventure");
                    st.setMonster(null);
                }
            }
            if (st.isEnd()) {
                System.out.println("You have finished the game");
                System.out.println("Congratulations!");
                fin = true;
            }


        }
        while (!fin);


        System.exit(0);






        //TODO : Demander au joueur de choisir où il veut aller parmis une liste
        //TODO : Si y a un combat on lance le combat et on fait le combat dans l'ordre

    }
    /**
     * Prompts the user to choose an action.
     * The user can either move to another place or exit the game.
     */
    private static void chooseUserAction() {
        System.out.println("What do you want to do ?");
        System.out.println("0 - Move to another place");
        System.out.println("1 - Exit the game");
        int choice = -1;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Please choose a valid action");
                choice = -1;
            }
        } while (choice < 0 || choice > 1);


    }
    /**
     * Handles the fight between the player and a monster.
     *
     * @param player  The player participating in the fight.
     * @param monster The monster to fight.
     * @return The updated player after the fight.
     */
    private static Player fightMonster(Player player, Monster monster) {
        Fight fight = new Fight(player, monster);
        while (!fight.isEnd()) {
            fightPrint(fight);
            fight.newTurn();
            Spell spell = chooseSpell(fight.getPlayer());
            fight.turn(spell);
        }
        return fight.getPlayer();

    }
    /**
     * Prints the current state of the fight, including player and monster stats.
     *
     * @param fight The current fight instance.
     */
    private static void fightPrint(Fight fight) {
        System.out.println(String.format("Player : \n HP : [%s/%s], ARM : %s, ATT : %s, MAN : [%s/%s] PAE : %s",
                fight.getPlayer().getCurrentHP(), fight.getPlayer().getMaximumHP(), fight.getPlayer().getArmor(),
                fight.getPlayer().getAttack(), fight.getPlayer().currentMana, fight.getPlayer().maximumMana,
                fight.getPlayer().getEffects()));
        System.out.println(String.format("%s : \n HP : [%s/%s], ARM : %s, ATT : %s, PAE : %s",
                fight.getMonster().name, fight.getMonster().getCurrentHP(), fight.getMonster().getMaximumHP(), fight.getMonster().getArmor(),
                fight.getMonster().getAttack(), fight.getMonster().getEffects()));
    }
    /**
     * Prompts the player to choose a spell to use during the fight.
     *
     * @param player The player choosing the spell.
     * @return The chosen spell, or null for a basic attack.
     */
    private static Spell chooseSpell(Player player) {
        List<Spell> spellsPlayer = new ArrayList<>();
        spellsPlayer.add(null);
        spellsPlayer.addAll(player.getUsableSpells());
        int choice = -1;
        do {
            try {
                System.out.println("What do you want to do :");
                for (Spell spell : spellsPlayer) {
                    System.out.println(String.format("%s - %s", spellsPlayer.indexOf(spell), spell != null ? spell.getName() : "Basic Attack" ));
                }
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Please choose a valid action");
                choice = -1;
            }


        } while (choice < 0 || choice >= spellsPlayer.size());

        if (spellsPlayer.get(choice) == null) {
            return null; // Return null for "Basic Attack"
        }
        try {
            return spellsPlayer.get(choice).getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Manages the player's interaction with the current place.
     * Displays the place's description and handles any monster encounters.
     *
     * @param world The game world.
     * @param st    The current place.
     * @return The monster in the place, if any.
     */
    private static Monster managerPlace(World world, Place st) {
        clearConsole();

        System.out.println("You are in " + st.getName());
        System.out.println(st.getDescription());
        if(st.isDefeat()){
            System.out.println("You are defeated");
            System.out.println("Game Over");
        }
        if(st.getMonster() !=  null){
            System.out.println("You are attacked by " + st.getMonster().name);
            System.out.println("You have to fight him");
            return st.getMonster();
        }
        return null;
    }

    /**
     * Prompts the player to choose a destination from the adjacent places.
     *
     * @param world The game world.
     * @param st    The current place.
     * @return The chosen destination place.
     */
    private static Place choosePlace( World world, Place st) {
        System.out.println("You can go to : ");
        List<Place> place = world.getAdjacentsPlace(st);
        for (Place p : place) {

            System.out.println(String.format("%s - %s", place.indexOf(p), p.getName()));
        }
        int choice = -1;
        do {
            try {
                System.out.println("Please choose a destination");
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                if (choice >= place.size()) {
                    System.out.println("Please choose a valid destination");
                } else {
                    return place.get(choice);
                }
            } catch (Exception e) {
                System.out.println("Please choose a valid destination");
            }
        } while (choice < 0 || choice >= place.size());


        st = place.get(choice);
        return st;
    }



    /**
     * Prompts the player to choose a map to play.
     * Allows the player to add a new map if none are available.
     *
     * @return The chosen game world.
     */
        private static World chooseMap () {
            System.out.println("You need to choose a map to play :");
            List<File> maps = WorldIO.getMaps();
            for (File map : maps) {
                System.out.println(String.format("%s - %s", maps.indexOf(map), map.getName()));
            }
            System.out.println(String.format("%s - %s", maps.size(), "Rajouter une carte"));
            int choice = -1;
            do {
                try {
                    Scanner scanner = new Scanner(System.in);
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice == maps.size()) {
                        try {
                            Desktop.getDesktop().open(WorldIO.getFolder());
                        } catch (IOException e) {
                            System.err.println("Error opening the folder: " + e.getMessage());
                        }
                        System.out.println("Please add a map and restart the game. (PRESS ENTER FOR RESTART)");

                        scanner.nextLine();


                        clearConsole();
                        play();
                    }
                    File selectedMap = maps.get(choice);
                    World world = null;
                    try (InputStream inputStream = new FileInputStream(selectedMap)) {
                        world = WorldIO.loadWorld(inputStream);
                    } catch (Exception e) {
                        System.err.println("Error loading the selected map: " + e.getMessage());
                    }
                    if (world == null) {
                        System.err.println("Failed to load the map.");
                        clearConsole();
                        play();
                    }
                    return world;
                 } catch (Exception e) {
                    System.out.println("Please choose a valid map");
                    choice = -1;
                }

            } while (choice < 0 || choice >= maps.size());

            return null;
        }
    /**
     * Clears the console screen.
     * This method uses ANSI escape codes and may not work on all systems.
     */
        private static void clearConsole () {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

