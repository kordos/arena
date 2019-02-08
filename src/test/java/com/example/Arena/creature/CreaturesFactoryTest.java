package com.example.Arena.creature;

import com.example.Arena.ArmourType;
import com.example.Arena.util.RandomUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CreaturesFactoryTest {

    private CreaturesFactory creaturesFactory;
    private RandomUtil randomUtil;

    @Before
    public void beforeEachTest() {
        randomUtil = Mockito.mock(RandomUtil.class);
        creaturesFactory = new CreaturesFactory();
    }

    @Test
    public void generateCreatureByType() {
        for (CreatureType type : CreatureType.values()) {
            Creature creature = creaturesFactory.generate(type);
            assertEquals(type, creature.getType());
            assertCreatureFields(creature);
        }
    }

    private void assertCreatureFields(Creature creature) {
        assertTrue(creature.getStrength() >= 1 && creature.getStrength() <= 10);
        assertTrue(creature.getLifePoints() >= 1 && creature.getLifePoints() <= 10);
        assertTrue(creature.getDexterity() >= 1 && creature.getDexterity() <= 10);
        assertTrue(creature.getEndurance() >= 1 && creature.getEndurance() <= 10);
        assertTrue(creature.getInitiative() >= 1 && creature.getInitiative() <= 10);
        assertTrue(creature.getVelocity() >= 1 && creature.getVelocity() <= 10);
        assertTrue(creature.getNumberOfAttacks() >= 1 && creature.getNumberOfAttacks() <= 10);
        assertTrue(creature.getNumberOfDodges() >= 1 && creature.getNumberOfDodges() <= 10);
    }

    @Test
    public void generateCreatureByTypeAndName() {
        for (CreatureType type : CreatureType.values()) {
            String name = type.name();
            Creature creature = creaturesFactory.generate(name, type);
            assertEquals(type, creature.getType());
            assertCreatureFields(creature);
        }
    }

    @Test
    public void createCreature() {
        String name = "some name" + "s".hashCode();
        CreatureType creatureType = CreatureType.DWARF;
        int strength = 2;
        int dexterity = 3;
        int initiative = 4;
        int velocity = 5;
        int endurance = 6;
        int numberOfAttacks = 7;
        int numberOfDodges = 8;
        int lifePoints = 9;

        Creature creature = creaturesFactory.createCreature(name, creatureType, strength, dexterity, initiative, velocity, endurance, numberOfAttacks, numberOfDodges, lifePoints);

        assertTrue(creature instanceof Dwarf);
        assertEquals(creature.getName(), name);
        assertEquals(creature.getType(), creatureType);
        assertEquals((int) creature.getStrength(), strength);
        assertEquals((int) creature.getDexterity(), dexterity);
        assertEquals((int) creature.getInitiative(), initiative);
        assertEquals((int) creature.getVelocity(), velocity);
        assertEquals((int) creature.getEndurance(), endurance);
        assertEquals((int) creature.getNumberOfAttacks(), numberOfAttacks);
        assertEquals((int) creature.getNumberOfDodges(), numberOfDodges);
        assertEquals((int) creature.getLifePoints(), lifePoints);
    }

    @Test
    public void randomCreature() {
        Creature creature = creaturesFactory.randomCreature();
        assertTrue(Arrays.asList(CreatureType.values()).contains(creature.getType()));
        assertCreatureFields(creature);
    }


    @Test
    public void randomCreature_checkGeneratingArmourCount_SetProperArmourCount() {
        creaturesFactory.setRandomUtil(randomUtil);
        when(randomUtil.random(0, ArmourType.values().length - 1)).thenCallRealMethod();
        when(randomUtil.random(10000, Integer.MAX_VALUE)).thenCallRealMethod();

        for (int i = 0; i < ArmourType.values().length; i++) {
            when(randomUtil.random(0, ArmourType.values().length)).thenReturn(i);

            Creature creature = creaturesFactory.randomCreature();
            assertEquals(creature.getArmour().size(), i);
        }
    }

    @Test
    public void randomCreatureList() {
        Random r = new Random();
        int listSize = r.nextInt(20) + 5;

        List<Creature> creatures = creaturesFactory.randomCreatureList(listSize);
        assertEquals(creatures.size(), listSize);
        creatures.forEach(this::assertCreatureFields);
    }
}