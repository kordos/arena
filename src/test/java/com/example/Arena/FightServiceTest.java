package com.example.Arena;

import org.junit.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FightServiceTest {

    private CreaturesFactory creaturesFactory = new CreaturesFactory();

    @Test
    public void test() {
        System.out.println("x" == "x");
        System.out.println(new String("x") == new String("x"));
        System.out.println(new String("x").equals(new String("x")));
        System.out.println(new String("x") == "x");
        System.out.println(new Integer(7) == new Integer(7));
        System.out.println(new Integer(7) == 7);
    }

    @Test
    public void testGeneratePairsForFight_PasTwoCreatures_ReturnOnePair() {

        Creature creature1 = creaturesFactory.generate(CreatureType.HUMAN);
        Creature creature2 = creaturesFactory.generate(CreatureType.ELF);

        List<Creature> creatureList = new ArrayList<>();
        creatureList.add(creature1);
        creatureList.add(creature2);


        FightService fightService = new FightService();

        List<List<Creature>> pairsToFight = fightService.generatePairsForFight(creatureList);


        List<List<Creature>> expectedResult = new ArrayList<>();
        expectedResult.add(creatureList);
        assertTrue(pairsToFight.size() == 1);
        assertEquals(
            expectedResult,
            pairsToFight
        );
    }


    @Test
    public void testGeneratePairsForFight_PasManyCreatures_ReturnsCorrectPair() {

        List<Creature> creatureList = new ArrayList<>();
        creatureList.add(creaturesFactory.generate(CreatureType.HUMAN));
        creatureList.add(creaturesFactory.generate(CreatureType.DWARF));
        creatureList.add(creaturesFactory.generate(CreatureType.ELF));
        creatureList.add(creaturesFactory.generate(CreatureType.HALFING));
        creatureList.add(creaturesFactory.generate(CreatureType.ORC));
        creatureList.add(creaturesFactory.generate(CreatureType.TROLL));

        FightService fightService = new FightService();

        List<List<Creature>> pairsToFight = fightService.generatePairsForFight(creatureList);


        int expectedCount = factorial(creatureList.size()) / (2 * factorial(creatureList.size() - 2));

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
