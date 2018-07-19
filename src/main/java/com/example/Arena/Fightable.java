package com.example.Arena;

public interface Fightable {
    /**
     * Returns amount of damaged points
     * @param Creature creature
     * @return int amount of damaged points
     */
    int attack(Creature creature);

    /**
     *
     * @param potentialDamage
     * @param attackingCreature
     */
    void dodge(int potentialDamage, Creature attackingCreature);
}
