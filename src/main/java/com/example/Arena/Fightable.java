package com.example.Arena;

public interface Fightable {

    AttackResult attack(Creature creature);

    DodgeResult dodge(int potentialDamage, Creature attackingCreature);
}
