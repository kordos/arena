package com.example.Arena.creature;

import com.example.Arena.*;
import com.example.Arena.util.RandomUtil;
import lombok.Getter;
import java.util.*;

@Getter
public abstract class Creature implements Fightable {
    static private Set usedNames = new HashSet();

    private String name = "Unknown";
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

    public Creature(String name, Integer strength, Integer dexterity, Integer initiative, Integer velocity, Integer endurance, Integer numberOfAttacks, Integer numberOfDodges, Integer lifePoints, CreatureType type) {
        this.setName(name);
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

    private void setName(String name) throws IllegalArgumentException {
        if (Creature.usedNames.contains(name)) {
            throw new IllegalArgumentException("Provided name already exists!");
        }

        this.name = name;
        Creature.usedNames.add(name);
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
                "name=" + name +
                ", strength=" + strength +
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
        int potentialDamage = 0, attackCount = 1, bodyPartBonus = 0;
        AttackResult attackResult = new AttackResult();
        String bodyPartInfo = "None";

        BodyPart bodyPart = getBodyPartForHit().orElse(
            getBodyPartForHit().orElse(null)
        );
        if (bodyPart != null) {
            bodyPartBonus = bodyPart.getBonusPoints();
            attackResult.setBodyPart(bodyPart);
            bodyPartInfo = bodyPart.toString();
        }

        int luckValue = random(1, 10);
        if (getDexterity() > luckValue) {
            potentialDamage = getStrength() + random(0, 3) + bodyPartBonus;
        }

        displayText(
            "Attack details: " +
            "Body part: " + bodyPartInfo +
            ", Potential damage: " + potentialDamage +
            ", attack count: " + attackCount
        );

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
        if (armour == null || armour.size() == 0) {
            return 0;
        }

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
