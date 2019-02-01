package com.example.Arena.creature;

import com.example.Arena.AttackResult;
import com.example.Arena.BodyPart;
import com.example.Arena.DodgeResult;
import com.example.Arena.util.RandomUtil;
import org.junit.*;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CreatureTest {

    private RandomUtil randomUtil;
    private CreaturesFactory creaturesFactory = new CreaturesFactory();
    private Creature creature;

    @Before
    public void beforeEachTest() {
        randomUtil = Mockito.mock(RandomUtil.class);
        creature = creaturesFactory.generate(CreatureType.HUMAN);
        creature.setRandomUtil(randomUtil);
    }

    @Test
    public void testAttack_dexterityLowerThanLuckValue_attackFailed() {
        when(randomUtil.random(1, 10)).thenReturn(creature.getDexterity() + 1);

        AttackResult attackResult = creature.attack();
        assertEquals(0, attackResult.getPotentialDamage());
    }

    @Test
    public void testAttack_dexterityHigherThanLuckValue_attackSuccess() {
        int addDamage = 2;
        when(randomUtil.random(1, 10)).thenReturn(creature.getDexterity() - 1);
        when(randomUtil.random(0, 3)).thenReturn(addDamage);
        int minPotentialDamage = creature.getStrength() + addDamage;

        AttackResult attackResult = creature.attack();
        assertTrue(minPotentialDamage <= attackResult.getPotentialDamage());
        assertTrue(0 < attackResult.getAttackCount());
    }

    @Test
    public void testAttack_addBodyPartHitBonusToPotentialDamage_attackSuccess() {
        // TODO check all body parts - parametrized test
        int addDamage = 2, bodyPartBonus = BodyPart.HEAD.getBonusPoints();

        when(randomUtil.random(1, 10)).thenReturn(creature.getDexterity() - 1);
        when(randomUtil.random(0, 3)).thenReturn(addDamage);
        when(randomUtil.random(1, 100)).thenReturn(4);

        int potentialDamage = creature.getStrength() + addDamage + bodyPartBonus;

        AttackResult attackResult = creature.attack();
        assertEquals(potentialDamage, attackResult.getPotentialDamage());
        assertTrue(0 < attackResult.getAttackCount());
    }

    @Test
    public void testDodge_ToLowInitiativeToDodgeAttack_ResultWithFalse() {
        int initiative = 2;
        int randomNumber = 5;
        int potentialDamage = 5;
        Creature dodgingCreature = new Human("1", 1,2, initiative,4,5,6,7,8);

        RandomUtil randomUtil = Mockito.mock(RandomUtil.class);
        when(randomUtil.random(1, 10)).thenReturn(randomNumber);

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
        when(randomUtil.random(1, 10)).thenReturn(randomNumber);

        dodgingCreature.setRandomUtil(randomUtil);

        AttackResult attackResult = new AttackResult(null, potentialDamage, 1);
        DodgeResult dodgeResult = dodgingCreature.dodge(attackResult);

        assertTrue(dodgeResult.isSuccess());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUniqueName_nameExists_throwsException() {
        String name = "John";

        creaturesFactory.generate(name, CreatureType.ELF);
        creaturesFactory.generate(name, CreatureType.DWARF);
    }

    @Test
    public void testUniqueName_nameNotExists_assignName() {
        String name = "John Doe";
        Creature creature = creaturesFactory.generate(name, CreatureType.HUMAN);

        creaturesFactory.generate("Sam", CreatureType.HUMAN);
        assertEquals(name, creature.getName());
    }
}
