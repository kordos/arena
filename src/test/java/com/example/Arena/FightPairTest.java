package com.example.Arena;

import com.example.Arena.creature.Creature;
import com.example.Arena.creature.CreatureType;
import com.example.Arena.creature.CreaturesFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class FightPairTest {

    private CreaturesFactory creaturesFactory = new CreaturesFactory();

    @Test
    public void equals_samePairs_shouldReturnTrue() {
        Creature creature1 = createCreature("one", CreatureType.DWARF);
        Creature creature2 = createCreature("two", CreatureType.ELF);

        FightPair fightPair1 = new FightPair(creature1, creature2);
        FightPair fightPair2 = new FightPair(creature2, creature1);

        assertTrue(fightPair1.equals(fightPair1));
        assertTrue(fightPair1.equals(fightPair2));

        assertTrue(fightPair2.equals(fightPair1));
        assertTrue(fightPair2.equals(fightPair2));
    }

    @Test
    public void equals_differentPars_shouldReturnFalse() {
        FightPair fightPair1 = new FightPair(
            createCreature(CreatureType.DWARF),
            createCreature(CreatureType.ELF)
        );

        FightPair fightPair2 = new FightPair(
            createCreature(CreatureType.HUMAN),
            createCreature(CreatureType.DWARF)
        );

        FightPair fightPair3 = new FightPair(
            createCreature(CreatureType.ELF),
            createCreature(CreatureType.ORC)
        );

        assertFalse(fightPair1.equals(fightPair2));
        assertFalse(fightPair1.equals(fightPair3));

        assertFalse(fightPair2.equals(fightPair1));
        assertFalse(fightPair2.equals(fightPair3));

        assertFalse(fightPair3.equals(fightPair1));
        assertFalse(fightPair3.equals(fightPair2));
    }

    private Creature createCreature(CreatureType creatureType) {
        return creaturesFactory.generate(creatureType);
    }

    private Creature createCreature(String name, CreatureType creatureType) {
        return creaturesFactory.generate(name, creatureType);
    }
}