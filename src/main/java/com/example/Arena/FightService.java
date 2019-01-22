package com.example.Arena;

import java.util.*;

public class FightService {
    private Map<BodyPart, Integer> bodyPartHit = new HashMap<>();

    private int strongestHit = 0;
    private String strongestHitInfo;

    public void fight(Creature creature1, Creature creature2) {
        System.out.println("Fight between two creatures: ");
        System.out.println("creature1: " + creature1);
        System.out.println("creature2: " + creature2);

        AttackResult attackResult;
        DodgeResult dodgeResult;
        int roundCount = 0;
        while (true) {
            roundCount++;

            attackResult = creature1.attack();
            dodgeResult = creature2.dodge(attackResult);
            updateStatistics(attackResult, dodgeResult, creature1);
            if (creature2.getLifePoints() <= 0) {
                System.out.println("CreatureEntity 1 won");
                break;
            }

            attackResult = creature2.attack();
            dodgeResult = creature1.dodge(attackResult);
            updateStatistics(attackResult, dodgeResult, creature2);
            if (creature1.getLifePoints() <= 0) {
                System.out.println("CreatureEntity 2 won");
                break;
            }
        }

        System.out.println("Round count: " + roundCount);
        printStatistics();
    }

    private void updateStatistics(AttackResult attackResult, DodgeResult dodgeResult, Creature attackingCreature) {
        if (dodgeResult.isSuccess()) {
            return;
        }

        int damage = dodgeResult.getDamage();
        if (damage > strongestHit) {
            strongestHit = damage;
            strongestHitInfo = "Strongest hit: " + damage + " by: " + attackingCreature;
        }

        int bodyPartHitCount = bodyPartHit.getOrDefault(attackResult.getBodyPart(), 0);
        bodyPartHit.put(attackResult.getBodyPart(), ++bodyPartHitCount);
    }

    private void printStatistics() {
        System.out.println("Statistics of fight:");
        System.out.println(strongestHitInfo);
        System.out.println("Body parts statistics:");
        int hitMost = 0;
        BodyPart hitMostBodyPart = null;
        for (Map.Entry bodyPartEntry : bodyPartHit.entrySet()) {
            System.out.println(bodyPartEntry.getKey() + ", hit count: " + bodyPartEntry.getValue());
            if ((int) bodyPartEntry.getValue() > hitMost) {
                hitMost = (int) bodyPartEntry.getValue();
                hitMostBodyPart = (BodyPart) bodyPartEntry.getKey();
            }
        }
        System.out.println("Body part with most hit: " + hitMostBodyPart + ", count: " + hitMost);
    }

    List<FightPair> generatePairsForFight(List<Creature> creatureList) {
        List<FightPair> result = new ArrayList<>();

        for (Creature creature1 : creatureList) {
            for (Creature creature2 : creatureList) {
                if (creature1.getType().equals(creature2.getType())) {
                    continue;
                }

                // check if exist, add or continue
                FightPair fightPair = new FightPair(creature1, creature2);
                if (!result.contains(fightPair)) {
                    result.add(fightPair);
                }
            }
        }

        return result;
    }
}
