package com.example.Arena;

import lombok.Value;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        return  Objects.equals(getFieldsToCompare(creature1), getFieldsToCompare(fightPair.creature1)) &&
                Objects.equals(getFieldsToCompare(creature2), getFieldsToCompare(fightPair.creature2))
                ||
                Objects.equals(getFieldsToCompare(creature1), getFieldsToCompare(fightPair.creature2)) &&
                Objects.equals(getFieldsToCompare(creature2), getFieldsToCompare(fightPair.creature1));
    }

    private List<Object> getFieldsToCompare(Creature creature) {
        return Collections.singletonList(
            creature.getType()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(creature1, creature2);
    }
}
