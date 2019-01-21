package com.example.Arena;

import org.junit.Test;

import static org.junit.Assert.*;

public class FightPairTest {

    private CreaturesFactory creaturesFactory = new CreaturesFactory();

    @Test
    public void equals_samePairs_shouldReturnTrue() {
        Creature creature1 = createCreature(CreatureType.DWARF, 10, 1);
        Creature creature2 = createCreature(CreatureType.ELF, 10, 2);

        Creature creature3 = createCreature(CreatureType.DWARF, 10, 3);
        Creature creature4 = createCreature(CreatureType.ELF, 10, 4);

        FightPair fightPair1 = new FightPair(creature1, creature2);
        FightPair fightPair2 = new FightPair(creature2, creature1);
        FightPair fightPair3 = new FightPair(creature3, creature4);

        assertTrue(fightPair1.equals(fightPair1));
        assertTrue(fightPair1.equals(fightPair2));
        assertTrue(fightPair1.equals(fightPair3));

        assertTrue(fightPair2.equals(fightPair1));
        assertTrue(fightPair2.equals(fightPair2));
        assertTrue(fightPair2.equals(fightPair3));

        assertTrue(fightPair3.equals(fightPair1));
        assertTrue(fightPair3.equals(fightPair2));
        assertTrue(fightPair3.equals(fightPair3));
    }

    @Test
    public void equals_differentPars_shouldReturnFalse() {
        FightPair fightPair1 = new FightPair(
            createCreature(CreatureType.DWARF, 10, 1),
            createCreature(CreatureType.ELF, 10, 3)
        );

        FightPair fightPair2 = new FightPair(
            createCreature(CreatureType.ELF, 9, 4),
            createCreature(CreatureType.DWARF, 8, 6)
        );

        FightPair fightPair3 = new FightPair(
            creaturesFactory.generate(CreatureType.ELF),
            creaturesFactory.generate(CreatureType.ORC)
        );

        assertFalse(fightPair1.equals(fightPair2));
        assertFalse(fightPair1.equals(fightPair3));

        assertFalse(fightPair2.equals(fightPair1));
        assertFalse(fightPair2.equals(fightPair3));

        assertFalse(fightPair3.equals(fightPair1));
        assertFalse(fightPair3.equals(fightPair2));
    }

    private Creature createCreature(CreatureType creatureType, int strength, int otherValues) {
        return creaturesFactory.createCreature(
                creatureType, strength, otherValues, otherValues, otherValues, otherValues, otherValues, otherValues, otherValues
        );
    }
}