package com.example.kata;

import com.example.Arena.Creature;
import com.example.Arena.CreatureType;

import java.util.*;

class LambdaExcercise {

    List<Creature> sortByLifePoints(List<Creature> creatures) {

        creatures.sort(Comparator.comparingInt(Creature::getLifePoints));

        return creatures;
    }

    List<Creature> sortBySumOfValues(List<Creature> creatures) {

        creatures.sort(Comparator.comparingInt(
            creature -> creature.getStrength() + creature.getLifePoints() + creature.getDexterity() +
                    creature.getEndurance() + creature.getInitiative() + creature.getNumberOfAttacks() +
                    creature.getNumberOfDodges() + creature.getVelocity()
        ));

        return creatures;
    }

    Map<CreatureType, Set<Creature>> sortByCreatureType(List<Creature> creatures) {
        Map<CreatureType, Set<Creature>> creatureMap = new HashMap<>();

        for (Creature creature : creatures) {
            Set<Creature> creatureList = new HashSet<>();
            if (creatureMap.containsKey(creature.getType())) {
                creatureList = creatureMap.get(creature.getType());
            }

            creatureList.add(creature);
            creatureMap.put(creature.getType(), creatureList);
        }

        return creatureMap;
    }

    void displayCreatures(List<Creature> list) {
        list.forEach(System.out::println);
    }
}
