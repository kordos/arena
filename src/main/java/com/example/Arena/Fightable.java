package com.example.Arena;

public interface Fightable {

    AttackResult attack(Creature creature);

    void dodge(int potentialDamage, Creature attackingCreature);
}
