package com.example.Arena;

public interface Fightable {

    AttackResult attack();

    DodgeResult dodge(AttackResult attackResult);

    boolean isAlive();
}
