package com.example.Arena;

import com.example.Arena.creature.Creature;
import com.example.Arena.creature.CreatureType;
import com.example.Arena.creature.CreaturesFactory;
import org.junit.*;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class FightServiceTest {

    private CreaturesFactory creaturesFactory = new CreaturesFactory();

    private FightService fightService = new FightService();

    @Test
    public void testGeneratePairsForFight_PasTwoCreatures_ReturnOnePair() {
        Creature creature1 = creaturesFactory.generate(CreatureType.HUMAN);
        Creature creature2 = creaturesFactory.generate(CreatureType.ELF);

        List<Creature> creatureList = Arrays.asList(creature1, creature2, creature1, creature2);

        List<FightPair> pairsToFight = fightService.generatePairsForFight(creatureList);

        List<FightPair> expectedResult = new ArrayList<>();
        expectedResult.add(new FightPair(creature1, creature2));
        assertEquals(1, pairsToFight.size());
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

        List<FightPair> pairsToFight = fightService.generatePairsForFight(creatureList);

        int size = new HashSet<>(creatureList).size();
        int expectedCount = factorial(size) / (2 * factorial(size - 2));

        assertEquals(pairsToFight.size(), expectedCount);
    }

    private int factorial(int number) {
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }

        return result;
    }

    @Test
    public void testFight_creatureOneWinsInFirstRound_shouldFinishFightAndReturnResult() {
        Creature creature1Mock = Mockito.mock(Creature.class);
        Creature creature2Mock = Mockito.mock(Creature.class);

        // 1 attacks 2; 2 loses
        mockCreatureAttackAndDodge(creature1Mock, creature2Mock, false);
        Mockito.when(creature1Mock.isAlive()).thenReturn(true);

        Mockito.when(creature2Mock.attack()).thenThrow(RuntimeException.class);

        FightResult fightResult = fightService.fight(creature1Mock, creature2Mock);
        assertEquals(1, fightResult.getRoundCount());
        assertEquals(creature1Mock, fightResult.getWinner());
        // todo any other checks?
    }

    @Test
    public void testFight_creatureTwoWinsInFirstRound_shouldFinishFightAndReturnResult() {
        Creature creature1Mock = Mockito.mock(Creature.class);
        Creature creature2Mock = Mockito.mock(Creature.class);

        // 1 attacks 2
        mockCreatureAttackAndDodge(creature1Mock, creature2Mock, true);

        // 2 attacks 1; 1 loses
        mockCreatureAttackAndDodge(creature2Mock, creature1Mock, false);

        FightResult fightResult = fightService.fight(creature1Mock, creature2Mock);
        assertEquals(1, fightResult.getRoundCount());
        assertEquals(creature2Mock, fightResult.getWinner());
    }

    private void mockCreatureAttackAndDodge(Creature creature1Mock, Creature creature2Mock, boolean isAlive)
    {
        AttackResult attackResult = new AttackResult(BodyPart.HEAD, 10, 1);
        Mockito.when(creature1Mock.attack()).thenReturn(attackResult);

        DodgeResult dodgeResult = new DodgeResult();
        dodgeResult.setSuccess(false);
        dodgeResult.setDamage(5);
        Mockito.when(creature2Mock.dodge(attackResult)).thenReturn(dodgeResult);
        Mockito.when(creature2Mock.isAlive()).thenReturn(isAlive);
    }

    @Test
    public void testFight_creaturesFightManyRounds_returnStatistics() {
        Creature creature1Mock = Mockito.mock(Creature.class);
        Creature creature2Mock = Mockito.mock(Creature.class);

        AttackResult attackResult = new AttackResult(BodyPart.HEAD, 10, 1);
        Mockito.when(creature1Mock.attack()).thenReturn(attackResult);

        DodgeResult dodgeResult = new DodgeResult();
        dodgeResult.setSuccess(false);
        dodgeResult.setDamage(5);
        Mockito.when(creature2Mock.dodge(attackResult)).thenReturn(dodgeResult);
        Mockito.when(creature2Mock.isAlive()).thenReturn(true, true, true, true, true, true);


        AttackResult attackResult2 = new AttackResult(BodyPart.TRUNK, 8, 1);
        Mockito.when(creature2Mock.attack()).thenReturn(attackResult2);

        DodgeResult dodgeResult2 = new DodgeResult();
        dodgeResult.setSuccess(false);
        dodgeResult.setDamage(7);
        Mockito.when(creature1Mock.dodge(attackResult2)).thenReturn(dodgeResult2);
        Mockito.when(creature1Mock.isAlive()).thenReturn(true, true, true, true, true, false);

        FightResult fightResult = fightService.fight(creature1Mock, creature2Mock);
        assertEquals(6, fightResult.getRoundCount());
        assertEquals(creature2Mock, fightResult.getWinner());
    }
}
