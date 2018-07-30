package com.example.Arena;

import org.junit.*;
import org.mockito.Mockito;
import static org.junit.Assert.*;

public class CreatureTest {

    @Test
    public void testDodge_ToLowInitiativeToDodgeAttack_ResultWithFalse() {
        int initiative = 2;
        int randomNumber = 5;
        int potentialDamage = 5;
        Creature dodgingCreature = new Human(1,2, initiative,4,5,6,7,8);

        RandomUtil randomUtil = Mockito.mock(RandomUtil.class);
        Mockito.when(randomUtil.random(1, 10)).thenReturn(randomNumber);

        dodgingCreature.setRandomUtil(randomUtil);

        DodgeResult dodgeResult = dodgingCreature.dodge(potentialDamage);

        assertFalse(dodgeResult.isSuccess());
    }

    @Test
    public void testDodge_HighInitiativeDodgeAttack_ResultWithSuccess() {
        int initiative = 10;
        int randomNumber = 5;
        int potentialDamage = 5;
        Creature dodgingCreature = new Human(1,2, initiative,4,5,6,7,8);

        RandomUtil randomUtil = Mockito.mock(RandomUtil.class);
        Mockito.when(randomUtil.random(1, 10)).thenReturn(randomNumber);

        dodgingCreature.setRandomUtil(randomUtil);

        DodgeResult dodgeResult = dodgingCreature.dodge(potentialDamage);

        assertTrue(dodgeResult.isSuccess());
    }

}
