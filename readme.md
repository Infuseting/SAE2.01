# Projet LDVEH - Livre Dont Vous Êtes le Héros

## Introduction

Ce projet a pour objectif de développer un Livre Dont Vous Êtes le Héros (LDVEH) en version simplifiée. Le programme sera développé en Java en respectant les principes de qualité et de développement orienté objet, ainsi que les conventions syntaxiques de Java.

## Structure du Projet

Le projet est divisé en trois livrables :

1. **Développement de la partie modèle** : Création des classes et des structures de données nécessaires pour représenter le monde du jeu.
2. **Exploration de graphes** : Implémentation d'algorithmes pour explorer les chemins et les lieux du monde.
3. **Réalisation d'une interface graphique** : Développement d'une interface utilisateur pour interagir avec le jeu.

## Fonctionnalités

À la fin de ce projet, votre programme devra proposer les fonctionnalités suivantes :

- Afficher un message indiquant la position actuelle du joueur.
- Permettre au joueur de choisir une destination.
- Gérer les combats entre le joueur et les monstres.
- Indiquer si le joueur gagne ou perd.

## Développement

Le projet doit être un projet Maven. Vous devez reproduire l'UML fourni pour répondre aux besoins du projet. Les constructeurs, accesseurs et mutateurs doivent être créés même s'ils ne sont pas représentés dans l'UML. Vous êtes libre d'ajouter toute fonction ou classe supplémentaire qui vous semble utile.

### Monde

- Chaque lieu a un nom, un texte à afficher, et peut être marqué comme lieu de départ, de victoire ou de défaite.
- Les lieux sont reliés par des chemins et peuvent contenir des monstres.
- Le monde doit pouvoir être sauvegardé et chargé depuis un fichier JSON.

### Entités et Combats

- Les monstres et le joueur ont des points de vie, d'armure, et d'attaque.
- Le joueur possède également des points de mana pour lancer des sorts.
- Les sorts incluent des soins, des poisons, et des boosts de caractéristiques.

### JSON

- Implémentez les classes `JSONObject` et `JSONArray` pour gérer les données JSON.
- Créez un `JSONParser` pour lire et écrire des fichiers JSON.

## Classe Game

Créez une classe `Game` dans le package `controller` pour gérer le jeu, les déplacements, et les combats.

## Rendu

À la fin de la journée, déposez votre travail sur la branche `main` de votre dépôt GIT. Assurez-vous d'inclure toutes les classes demandées ainsi que les tests unitaires.

## Notation

Le projet sera noté sur les critères suivants :

- Reproduction de l'UML : 5 points
- Classe WorldIO : 2 points
- JSONParser : 3 points
- JSONObject & JSONArray : 1 point
- Jouabilité et affichage : 3 points
- Passage des tests unitaires : 2 points
- Travail en équipe : 2 points

---

Lien vers la javadocs (https://infuseting.fr/SAE2.01/javadocs/)