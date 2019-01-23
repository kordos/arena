package com.example.Arena.creature;

import com.example.Arena.ArmourType;
import com.example.Arena.creature.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CreaturesFactory {

    public Creature generate(CreatureType type) {

        int strength = random(1, 10);
        int dexterity = random(1, 10);
        int initiative = random(1, 10);
        int velocity = random(1, 10);
        int endurance = random(1, 10);
        int numberOfAttacks = random(1, 10);
        int numberOfDodges = random(1, 10);
        int lifePoints = random(1, 10);

        return createCreature(type, strength, dexterity, initiative, velocity, endurance, numberOfAttacks, numberOfDodges, lifePoints);
    }

    public Creature generate(String name, CreatureType type) {
        Creature creature = generate(type);
        creature.setName(name);

        return creature;
    }

    public Creature createCreature(CreatureType type, Integer strength, Integer dexterity, Integer initiative, Integer velocity, Integer endurance, Integer numberOfAttacks, Integer numberOfDodges, Integer lifePoints) {
        Creature creature = null;

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

        creature.addArmour(generateArmour());

        return creature;
    }

    public Creature createCreature(String name, CreatureType type, Integer strength, Integer dexterity, Integer initiative, Integer velocity, Integer endurance, Integer numberOfAttacks, Integer numberOfDodges, Integer lifePoints) {
        Creature creature = createCreature(type, strength, dexterity, initiative, velocity, endurance, numberOfAttacks, numberOfDodges, lifePoints);
        creature.setName(name);

        return creature;
    }

    private CreatureType randomCreatureType() {
        Random r = new Random();
        CreatureType[] types = CreatureType.values();

        return types[r.nextInt(types.length)];
    }

    private int random(int min, int max) {
        Random r = new Random();

        return r.nextInt((max - min) + 1) + min;
    }

    public Creature randomCreature() {
        CreatureType type = randomCreatureType();

        return generate(type);
    }

    public List<Creature> randomCreatureList(int listSize) {

        List<Creature> result = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            result.add(randomCreature());
        }

        return result;
    }

    private Set<ArmourType> generateArmour() {
        int armourMinNumber = 0;
        ArmourType[] armourTypes = ArmourType.values();
        int armourMaxNumber = armourTypes.length;
        HashSet<ArmourType> result = new HashSet<>();

        int armourCount = random(armourMinNumber, armourMaxNumber);
        for (int i = 0; i < armourCount; i++) {
            int armourNumber = random(0, armourMaxNumber - 1);

            if (result.contains(armourTypes[armourNumber])) {
                armourCount ++;
                continue;
            }

            result.add(armourTypes[armourNumber]);
        }

        return result;
    }
}
