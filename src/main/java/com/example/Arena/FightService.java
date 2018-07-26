package com.example.Arena;

public class FightService {

    void fight(Creature creature1, Creature creature2) {
        System.out.println("Fight between two creatures: ");
        System.out.println("creature1: " + creature1);
        System.out.println("creature2: " + creature2);

        int potentialDamage;
        while (true) {
            potentialDamage = creature1.attack(creature2);
            creature2.dodge(potentialDamage, creature1);
            if (creature2.getLifePoints() <= 0) {
                System.out.println("Creature 1 won");
                break;
            }

            potentialDamage = creature2.attack(creature1);
            creature1.dodge(potentialDamage, creature2);
            if (creature1.getLifePoints() <= 0) {
                System.out.println("Creature 2 won");
                break;
            }
        }
    }
}
