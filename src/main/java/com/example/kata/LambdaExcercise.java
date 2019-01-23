package com.example.kata;

import com.example.Arena.creature.Creature;
import com.example.Arena.creature.CreatureType;

import java.util.*;
import java.util.stream.Collectors;

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

        // old approach
        for (Creature creature : creatures) {
            Set<Creature> creatureList = new HashSet<>();
            if (creatureMap.containsKey(creature.getType())) {
                creatureList = creatureMap.get(creature.getType());
            }

            creatureList.add(creature);
            creatureMap.put(creature.getType(), creatureList);
        }

        // new approach: stream and lambda
        creatureMap = creatures.stream()
            .collect(
                Collectors.groupingBy(Creature::getType, Collectors.toSet())
            );

        return creatureMap;
    }

    void displayCreatures(List<Creature> list) {
        list.forEach(System.out::println);
    }
}
