package com.example.Arena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreaturesFactory {

    Creature generate(CreatureType type) {
        Creature creature = null;

        int strength = random(1, 10);
        int dexterity = random(1, 10);
        int initiative = random(1, 10);
        int velocity = random(1, 10);
        int endurance = random(1, 10);
        int numberOfAttacks = random(1, 10);
        int numberOfDodges = random(1, 10);
        int lifePoints = random(1, 10);

        switch (type) {
            case ELF:
                creature = new Elf(strength, dexterity, initiative, velocity, endurance, numberOfAttacks, numberOfDodges, lifePoints);
            break;
            case DWARF:
                creature = new Dwarf(strength, dexterity, initiative, velocity, endurance, numberOfAttacks, numberOfDodges, lifePoints);
            break;
            case HALFING:
                creature = new Halfing(strength, dexterity, initiative, velocity, endurance, numberOfAttacks, numberOfDodges, lifePoints);
            break;
            case ORC:
                creature = new Orc(strength, dexterity, initiative, velocity, endurance, numberOfAttacks, numberOfDodges, lifePoints);
            break;
            case TROLL:
                creature = new Troll(strength, dexterity, initiative, velocity, endurance, numberOfAttacks, numberOfDodges, lifePoints);
            break;
            case HUMAN:
                creature = new Human(strength, dexterity, initiative, velocity, endurance, numberOfAttacks, numberOfDodges, lifePoints);
                break;
        }

        return creature;
    }

    CreatureType randomCreatureType() {
        Random r = new Random();
        CreatureType[] types = CreatureType.values();

        return types[r.nextInt(types.length)];
    }

    int random(int min, int max) {
        Random r = new Random();

        return r.nextInt((max - min) + 1) + min;
    }

    Creature randomCreature() {
        CreatureType type = randomCreatureType();

        return generate(type);
    }

    List<Creature> randomCreatureList(int listSize) {

        List<Creature> result = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            result.add(randomCreature());
        }

        return result;
    }
}
