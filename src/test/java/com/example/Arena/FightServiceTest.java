package com.example.Arena;

import com.example.Arena.creature.Creature;
import com.example.Arena.creature.CreatureType;
import com.example.Arena.creature.CreaturesFactory;
import org.junit.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class FightServiceTest {

    private CreaturesFactory creaturesFactory = new CreaturesFactory();

    @Test
    public void testGeneratePairsForFight_PasTwoCreatures_ReturnOnePair() {
        Creature creature1 = creaturesFactory.generate(CreatureType.HUMAN);
        Creature creature2 = creaturesFactory.generate(CreatureType.ELF);

        List<Creature> creatureList = Arrays.asList(creature1, creature2, creature1, creature2);

        FightService fightService = new FightService();
        List<FightPair> pairsToFight = fightService.generatePairsForFight(creatureList);

        List<FightPair> expectedResult = new ArrayList<>();
        expectedResult.add(new FightPair(creature1, creature2));
        assertTrue(pairsToFight.size() == 1);
        assertEquals(
            expectedResult,
            pairsToFight
        );
    }


    @Test
    public void testGeneratePairsForFight_PasManyCreatures_ReturnsCorrectPair() {

        List<Creature> creatureList = new ArrayList<>();
        Creature creature1 = creaturesFactory.generate(CreatureType.HUMAN);
        creatureList.add(creature1);
        creatureList.add(creature1);
        creatureList.add(creature1);
        creatureList.add(creature1);
        creatureList.add(creaturesFactory.generate(CreatureType.HUMAN));
        creatureList.add(creaturesFactory.generate(CreatureType.DWARF));
        creatureList.add(creaturesFactory.generate(CreatureType.ELF));
        creatureList.add(creaturesFactory.generate(CreatureType.HALFING));
        creatureList.add(creaturesFactory.generate(CreatureType.ORC));
        creatureList.add(creaturesFactory.generate(CreatureType.TROLL));

        FightService fightService = new FightService();

        List<FightPair> pairsToFight = fightService.generatePairsForFight(creatureList);

        int size = new HashSet<>(creatureList).size();
        int expectedCount = factorial(size) / (2 * factorial(size - 2));

        assertTrue(pairsToFight.size() == expectedCount);
    }

    private int factorial(int number) {
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }

        return result;
    }
}
