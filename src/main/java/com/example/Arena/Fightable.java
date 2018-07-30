package com.example.Arena;

public interface Fightable {

    AttackResult attack();

    DodgeResult dodge(int potentialDamage);

    boolean isAlive();
}
