package com.example.Arena.creature;

public class Orc extends Creature {
    public Orc(String name, Integer strength, Integer dexterity, Integer initiative, Integer velocity, Integer endurance, Integer numberOfAttacks, Integer numberOfDodges, Integer lifePoints) {
        super(name, strength, dexterity, initiative, velocity, endurance, numberOfAttacks, numberOfDodges, lifePoints, CreatureType.ORC);
    }
}
