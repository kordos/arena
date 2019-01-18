package com.example.Arena;

import lombok.Value;

import java.util.Objects;

@Value
public class FightPair {
    private Creature creature1;

    private Creature creature2;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        FightPair fightPair = (FightPair) o;
        return  Objects.equals(creature1.getType(), fightPair.creature1.getType()) &&
                Objects.equals(creature2.getType(), fightPair.creature2.getType())
                ||
                Objects.equals(creature1.getType(), fightPair.creature2.getType()) &&
                Objects.equals(creature2.getType(), fightPair.creature1.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(creature1, creature2);
    }
}
