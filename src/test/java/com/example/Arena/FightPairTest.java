package com.example.Arena;

import org.junit.Test;

import static org.junit.Assert.*;

public class FightPairTest {

    private CreaturesFactory creaturesFactory = new CreaturesFactory();

    @Test
    public void equals_samePairs_shouldReturnTrue() {

        Creature creature1 = creaturesFactory.generate(CreatureType.DWARF);
        Creature creature2 = creaturesFactory.generate(CreatureType.ELF);
        FightPair fightPair1 = new FightPair(creature1, creature2);
        FightPair fightPair2 = new FightPair(creature1, creature2);
        FightPair fightPair3 = new FightPair(creature2, creature1);

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
            creaturesFactory.generate(CreatureType.DWARF),
            creaturesFactory.generate(CreatureType.ELF)
        );

        FightPair fightPair2 = new FightPair(
            creaturesFactory.generate(CreatureType.HUMAN),
            creaturesFactory.generate(CreatureType.ELF)
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
}