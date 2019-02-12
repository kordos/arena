package com.example.Arena;

import com.example.Arena.creature.Creature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor
@Value
class FightResult {
    private Creature creature1, creature2;
    private int roundCount;

    Creature getWinner() {
        if (!creature1.isAlive()) {
            return creature2;
        }

        if (!creature2.isAlive()) {
            return creature1;
        }

        return null;
    }
}
