package com.example.kata;

import com.example.Arena.creature.Creature;
import com.example.Arena.creature.CreatureType;
import com.example.Arena.creature.CreaturesFactory;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class LambdaExcerciseTest {

    @Test
    public void sortByLifePoints_fixedList_sortAsc() {
        CreaturesFactory creaturesFactory = new CreaturesFactory();

        Creature creature1 = creaturesFactory.createCreature(
            "11111", CreatureType.DWARF, 1, 2, 3, 4, 5, 6, 8, 1
        );
        Creature creature2 = creaturesFactory.createCreature(
            "22222", CreatureType.DWARF, 1, 2, 3, 4, 5, 6, 8, 2
        );

        Creature creature3 = creaturesFactory.createCreature(
            "33333", CreatureType.DWARF, 1, 2, 3, 4, 5, 6, 8, 3
        );
        Creature creature4 = creaturesFactory.createCreature(
            "44444", CreatureType.DWARF, 1, 2, 3, 4, 5, 6, 8, 4
        );

        List<Creature> creatures = Arrays.asList(
            creature4, creature2, creature3, creature1
        );


        LambdaExcercise lambdaExcercise = new LambdaExcercise();
        List sortedCreatures = lambdaExcercise.sortByLifePoints(creatures);

        assertEquals(creatures.size(), sortedCreatures.size());
        assertEquals(creature1.getLifePoints(), ((Creature)sortedCreatures.get(0)).getLifePoints());
        assertEquals(creature2.getLifePoints(), ((Creature)sortedCreatures.get(1)).getLifePoints());
        assertEquals(creature3.getLifePoints(), ((Creature)sortedCreatures.get(2)).getLifePoints());
        assertEquals(creature4.getLifePoints(), ((Creature)sortedCreatures.get(3)).getLifePoints());

        //lambdaExcercise.displayCreatures(sortedCreatures);
    }

    @Test
    public void sortByLifePoints_dynamicList_sortAsc() {
        CreaturesFactory creaturesFactory = new CreaturesFactory();

        List<Creature> creatures = creaturesFactory.randomCreatureList(20);

        LambdaExcercise lambdaExcercise = new LambdaExcercise();
        List sortedCreatures = lambdaExcercise.sortByLifePoints(creatures);


        creatures.sort(Comparator.comparingInt(Creature::getLifePoints));

        assertEquals(creatures.size(), sortedCreatures.size());
        assertTrue(creatures.equals(sortedCreatures));
    }

    @Test
    public void sortBySumOfValues_fixedList_sortAsc() {
        CreaturesFactory creaturesFactory = new CreaturesFactory();

        Creature creature1 = creaturesFactory.createCreature(
            "111", CreatureType.DWARF, 1, 1, 1, 1, 1, 1, 1, 1
        );
        Creature creature2 = creaturesFactory.createCreature(
            "222", CreatureType.DWARF, 2, 2, 2, 2, 2, 2, 2, 2
        );

        Creature creature3 = creaturesFactory.createCreature(
            "333", CreatureType.DWARF, 3, 2, 2, 2, 2, 2, 2, 2
        );
        Creature creature4 = creaturesFactory.createCreature(
            "444", CreatureType.DWARF, 2, 4, 2, 2, 2, 2, 2, 2
        );

        List<Creature> creatures = Arrays.asList(
            creature4, creature2, creature3, creature1
        );


        LambdaExcercise lambdaExcercise = new LambdaExcercise();
        List sortedCreatures = lambdaExcercise.sortBySumOfValues(creatures);

        assertEquals(creatures.size(), sortedCreatures.size());
        assertEquals(Arrays.asList(creature1, creature2, creature3, creature4), sortedCreatures);
    }

    @Test
    public void sortByCreatureType_fixedList_sortByType() {
        CreaturesFactory creaturesFactory = new CreaturesFactory();

        List<Creature> creatures = Arrays.asList(
            creaturesFactory.generate(CreatureType.DWARF),
            creaturesFactory.generate(CreatureType.DWARF),
            creaturesFactory.generate(CreatureType.DWARF),
            creaturesFactory.generate(CreatureType.ORC),
            creaturesFactory.generate(CreatureType.ORC),
            creaturesFactory.generate(CreatureType.TROLL),
            creaturesFactory.generate(CreatureType.HUMAN),
            creaturesFactory.generate(CreatureType.HUMAN),
            creaturesFactory.generate(CreatureType.HUMAN),
            creaturesFactory.generate(CreatureType.HUMAN),
            creaturesFactory.generate(CreatureType.ELF),
            creaturesFactory.generate(CreatureType.HALFING)
        );

        LambdaExcercise lambdaExcercise = new LambdaExcercise();
        Map<CreatureType, Set<Creature>> mapCreatures = lambdaExcercise.sortByCreatureType(creatures);

        assertEquals(6, mapCreatures.size());
        assertEquals(3, mapCreatures.get(CreatureType.DWARF).size());
        assertEquals(2, mapCreatures.get(CreatureType.ORC).size());
        assertEquals(1, mapCreatures.get(CreatureType.TROLL).size());
        assertEquals(4, mapCreatures.get(CreatureType.HUMAN).size());
        assertEquals(1, mapCreatures.get(CreatureType.ELF).size());
        assertEquals(1, mapCreatures.get(CreatureType.HALFING).size());

        System.out.println(mapCreatures);
    }
}