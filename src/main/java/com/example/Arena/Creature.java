package com.example.Arena;

import lombok.Getter;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Getter
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

    private Set<ArmourType> armour;

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

    public void setType(CreatureType type) {
        this.type = type;
    }

    public void setRandomUtil(RandomUtil randomUtil) {
        this.randomUtil = randomUtil;
    }

    public Set<ArmourType> getArmour() {
        return armour;
    }

    public void addArmour(Set<ArmourType> armour) {
        this.armour = armour;
    }

    @Override
    public String toString() {
        return "CreatureEntity{" +
                "strength=" + strength +
                ", dexterity=" + dexterity +
                ", initiative=" + initiative +
                ", velocity=" + velocity +
                ", endurance=" + endurance +
                ", numberOfAttacks=" + numberOfAttacks +
                ", numberOfDodges=" + numberOfDodges +
                ", lifePoints=" + lifePoints +
                ", type=" + type +
                ", armour" + armour +
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

        bodyPart = getBodyPartForHit().orElse(
            getBodyPartForHit().orElse(null)
        );
        if (bodyPart != null) {
            bonus = bodyPart.getBonusPoints();
            attackResult.setBodyPart(bodyPart);
            bodyPartInfo = bodyPart.toString();
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
    public DodgeResult dodge(AttackResult attackResult) {
        int potentialDamage = attackResult.getPotentialDamage();

        DodgeResult result = new DodgeResult();

        int luckValue = random(1, 10);
        if (getInitiative() > luckValue) {
            result.setSuccess(true);
            displayText("Dodge ended with success.");
        } else {
            result.setSuccess(false);
            // get armour number
            int armourProtection = getArmourProtection(attackResult.getBodyPart());
            int actualDamage = potentialDamage > 0 ? potentialDamage - getEndurance() - armourProtection : 0;

            if (actualDamage > 0) {
                result.setDamage(actualDamage);
                lifePoints -= actualDamage;
            }

            displayText(
                "Dodge ended with failure. Armour protection: " + armourProtection + ", Damage: " + actualDamage +
                ". Life points left: " + lifePoints
            );
            if (lifePoints <= 0) {
                displayText("CreatureEntity DEAD!!!");
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

    private Optional<BodyPart> getBodyPartForHit() {
        int number = random(1, 100);

        int sum = 0;
        for (BodyPart bodyPart : BodyPart.values()) {
            sum += bodyPart.getProbability();
            if (number <= sum) {
                return Optional.of(bodyPart);
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean isAlive() {
        return lifePoints > 0;
    }

    private int getArmourProtection(BodyPart bodyPartToHit) {
        // simple for implementation
        int protection = 0;
        for (ArmourType armourEntry : armour) {
            if (armourEntry.getProtectedBodyPart().contains(bodyPartToHit)) {
                protection += random(armourEntry.getMinProtection(), armourEntry.getMaxProtection());
            }
        }

        // stream implementation
        protection = armour
            .stream()
            .filter(item -> item.getProtectedBodyPart().contains(bodyPartToHit))
            .mapToInt(item -> random(item.getMinProtection(), item.getMaxProtection()))
            .sum();

        return protection;
    }
}
