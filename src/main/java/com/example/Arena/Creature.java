package com.example.Arena;

import java.util.Random;

public abstract class Creature implements Fightable {
    private Integer strength;
    private Integer dexterity;
    private Integer initiative;
    private Integer velocity;
    private Integer endurance;
    private Integer numberOfAttacks;
    private Integer numberOfDodges;
    private Integer lifePoints;
    private CreatureType type;

    private RandomUtil randomUtil;

    public Creature(Integer strength, Integer dexterity, Integer initiative, Integer velocity, Integer endurance, Integer numberOfAttacks, Integer numberOfDodges, Integer lifePoints, CreatureType type) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.initiative = initiative;
        this.velocity = velocity;
        this.endurance = endurance;
        this.numberOfAttacks = numberOfAttacks;
        this.numberOfDodges = numberOfDodges;
        this.lifePoints = lifePoints;
        this.type = type;
    }

    public Integer getStrength() {
        return strength;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public Integer getVelocity() {
        return velocity;
    }

    public Integer getEndurance() {
        return endurance;
    }

    public Integer getNumberOfAttacks() {
        return numberOfAttacks;
    }

    public Integer getNumberOfDodges() {
        return numberOfDodges;
    }

    public Integer getLifePoints() {
        return lifePoints;
    }

    public CreatureType getType() {
        return type;
    }

    public void setType(CreatureType type) {
        this.type = type;
    }

    public void setRandomUtil(RandomUtil randomUtil) {
        this.randomUtil = randomUtil;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "strength=" + strength +
                ", dexterity=" + dexterity +
                ", initiative=" + initiative +
                ", velocity=" + velocity +
                ", endurance=" + endurance +
                ", numberOfAttacks=" + numberOfAttacks +
                ", numberOfDodges=" + numberOfDodges +
                ", lifePoints=" + lifePoints +
                ", type='" + type + '\'' +
                '}' + super.toString();
    }

    @Override
    public AttackResult attack() {
        int potentialDamage = 0;
        int attackCount = 1;
        BodyPart bodyPart;
        AttackResult attackResult = new AttackResult();

        int luckValue = random(1, 10);

        int bonus = 0;
        String bodyPartInfo = "None";
        try {
            bodyPart = getBodyPartForHit();
            bonus = bodyPart.getBonusPoints();
            attackResult.setBodyPart(bodyPart);
            bodyPartInfo = bodyPart.toString();
        } catch (Exception e) {
            // TODO try second time
        }

        String msgAttackInfo = "Body part: " + bodyPartInfo +
                ", Potential damage: " + potentialDamage +
                ", attack count: " + attackCount;
        if (getDexterity() > luckValue) {
            potentialDamage = getStrength() + random(0, 3) + bonus;

            displayText("Attack ended with success. " + msgAttackInfo);
        } else {
            displayText("Attack ended with failure. " + msgAttackInfo);
        }

        attackResult.setAttackCount(attackCount);
        attackResult.setPotentialDamage(potentialDamage);
        return attackResult;
    }

    @Override
    public DodgeResult dodge(int potentialDamage) {
        DodgeResult result = new DodgeResult();

        int luckValue = random(1, 10);
        if (getInitiative() > luckValue) {
            result.setSuccess(true);
            displayText("Dodge ended with success.");
        } else {
            result.setSuccess(false);
            int actualDamage = potentialDamage > 0 ? potentialDamage - getEndurance() : 0;

            if (actualDamage > 0) {
                result.setDamage(actualDamage);
                lifePoints -= actualDamage;
            }

            displayText("Dodge ended with failure. Damage: " + actualDamage + ". Life points left: " + lifePoints);
            if (lifePoints <= 0) {
                displayText("Creature DEAD!!!");
            }
        }

        return result;
    }

    private int random(int min, int max) {
        if (randomUtil == null) {
            Random r = new Random();
            return r.nextInt((max - min) + 1) + min;
        }

        return randomUtil.random(min, max);
    }

    private void displayText(String text) {
        System.out.println(text);
    }

    private BodyPart getBodyPartForHit() throws Exception {
        int number = random(1, 100);

        int sum = 0;
        for (BodyPart bodyPart : BodyPart.values()) {
            sum += bodyPart.getProbability();
            if (number <= sum) {
                return bodyPart;
            }
        }

        throw new Exception("No body part to hit");
    }

    @Override
    public boolean isAlive() {
        return lifePoints > 0;
    }
}
