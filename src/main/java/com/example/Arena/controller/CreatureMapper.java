package com.example.Arena.controller;

import com.example.Arena.creature.Creature;
import com.example.Arena.creature.CreaturesFactory;
import org.springframework.stereotype.Component;

@Component
public class CreatureMapper {

    private CreaturesFactory creaturesFactory;

    public CreatureMapper(CreaturesFactory creaturesFactory) {
        this.creaturesFactory = creaturesFactory;
    }

    Creature map(AddCreatureToTournamentDto addCreatureToTournamentDto) {
        return creaturesFactory.createCreature(
                "Unknown", // todo add fiel
                addCreatureToTournamentDto.getType(),
                addCreatureToTournamentDto.getStrength(),
                addCreatureToTournamentDto.getDexterity(),
                addCreatureToTournamentDto.getInitiative(),
                addCreatureToTournamentDto.getVelocity(),
                addCreatureToTournamentDto.getEndurance(),
                addCreatureToTournamentDto.getNumberOfAttacks(),
                addCreatureToTournamentDto.getNumberOfDodges(),
                addCreatureToTournamentDto.getLifePoints()
        );
    }
}
