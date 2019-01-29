package com.example.Arena.creature;

import com.example.Arena.AttackResult;
import com.example.Arena.DodgeResult;
import com.example.Arena.util.RandomUtil;
import org.junit.*;
import org.mockito.Mockito;
import static org.junit.Assert.*;

public class CreatureTest {

    @Test
    public void testDodge_ToLowInitiativeToDodgeAttack_ResultWithFalse() {
        int initiative = 2;
        int randomNumber = 5;
        int potentialDamage = 5;
        Creature dodgingCreature = new Human("1", 1,2, initiative,4,5,6,7,8);

        RandomUtil randomUtil = Mockito.mock(RandomUtil.class);
        Mockito.when(randomUtil.random(1, 10)).thenReturn(randomNumber);

        dodgingCreature.setRandomUtil(randomUtil);

        AttackResult attackResult = new AttackResult(null, potentialDamage, 1);
        DodgeResult dodgeResult = dodgingCreature.dodge(attackResult);

        assertFalse(dodgeResult.isSuccess());
    }

    @Test
    public void testDodge_HighInitiativeDodgeAttack_ResultWithSuccess() {
        int initiative = 10;
        int randomNumber = 5;
        int potentialDamage = 5;
        Creature dodgingCreature = new Human("2", 1,2, initiative,4,5,6,7,8);

        RandomUtil randomUtil = Mockito.mock(RandomUtil.class);
        Mockito.when(randomUtil.random(1, 10)).thenReturn(randomNumber);

        dodgingCreature.setRandomUtil(randomUtil);

        AttackResult attackResult = new AttackResult(null, potentialDamage, 1);
        DodgeResult dodgeResult = dodgingCreature.dodge(attackResult);

        assertTrue(dodgeResult.isSuccess());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetName_nameExists_throwsException() {
        String name = "John";
        CreaturesFactory creaturesFactory = new CreaturesFactory();

        creaturesFactory.generate(name, CreatureType.ELF);
        creaturesFactory.generate(name, CreatureType.DWARF);
    }

    @Test
    public void testSetName_nameNotExists_assignName() {
        String name = "John";
        CreaturesFactory creaturesFactory = new CreaturesFactory();
        Creature creature = creaturesFactory.generate(name, CreatureType.HUMAN);

        creaturesFactory.generate("Sam", CreatureType.HUMAN);
        assertEquals(name, creature.getName());
    }
}
