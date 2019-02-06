package com.example.Arena.creature;

import com.example.Arena.AttackResult;
import com.example.Arena.BodyPart;
import com.example.Arena.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class CreatureAttackWithSuccessTest {

    private RandomUtil randomUtil;
    private CreaturesFactory creaturesFactory = new CreaturesFactory();
    private Creature creature;

    private int addDamage, bodyPartLuck, bodyPartPoints;

    public CreatureAttackWithSuccessTest(int addDamage, int bodyPartLuck, int bodyPartPoints) {
        this.addDamage = addDamage;
        this.bodyPartLuck = bodyPartLuck;
        this.bodyPartPoints = bodyPartPoints;

        randomUtil = Mockito.mock(RandomUtil.class);
        creature = creaturesFactory.generate(CreatureType.HUMAN);
        creature.setRandomUtil(randomUtil);
    }

    @Test
    public void testAttack_addBodyPartHitBonusToPotentialDamage_attackSuccess() {
        when(randomUtil.random(1, 10)).thenReturn(creature.getDexterity() - 1);
        when(randomUtil.random(0, 3)).thenReturn(addDamage);
        when(randomUtil.random(1, 100)).thenReturn(bodyPartLuck);

        int potentialDamage = creature.getStrength() + addDamage + bodyPartPoints;

        AttackResult attackResult = creature.attack();
        assertEquals(potentialDamage, attackResult.getPotentialDamage());
        assertTrue(0 < attackResult.getAttackCount());
    }

    @Parameters(name = "{index}: BodyPart addDamage={0}, bodyPartLuck={1}, bodyPartPoints={2}")
    public static Iterable<Object[]> data() {
        // [addDamage, bodyPartLuck, bodyPartPoints
        return Arrays.asList(new Object[][] {
            { 0,  1, BodyPart.HEAD.getBonusPoints()},
            { 0,  2, BodyPart.HEAD.getBonusPoints()},
            { 0,  3, BodyPart.HEAD.getBonusPoints()},
            { 0,  4, BodyPart.HEAD.getBonusPoints()},
            { 0,  5, BodyPart.HEAD.getBonusPoints()},
            { 1, 6, BodyPart.LEFT_ARM.getBonusPoints()},
            { 1, 15, BodyPart.LEFT_ARM.getBonusPoints()},
            { 2, 16, BodyPart.RIGHT_ARM.getBonusPoints()},
            { 2, 25, BodyPart.RIGHT_ARM.getBonusPoints()},
            { 3, 26, BodyPart.TRUNK.getBonusPoints()},
            { 2, 35, BodyPart.TRUNK.getBonusPoints()},
            { 0, 55, BodyPart.TRUNK.getBonusPoints()},
            { 3, 56, BodyPart.LEFT_LEG.getBonusPoints()},
            { 1, 60, BodyPart.LEFT_LEG.getBonusPoints()},
            { 2, 61, BodyPart.RIGHT_LEG.getBonusPoints()},
            { 0, 65, BodyPart.RIGHT_LEG.getBonusPoints()},
            { 3, 66, 0},
            { 0, 76, 0},
            { 3, 86, 0},
            { 2, 99, 0},
            { 1, 100, 0},
        });
    }
}
