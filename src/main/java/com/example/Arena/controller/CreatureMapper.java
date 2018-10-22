package com.example.Arena.controller;

import com.example.Arena.Creature;
import com.example.Arena.CreaturesFactory;
import org.springframework.stereotype.Component;

@Component
public class CreatureMapper {

    private CreaturesFactory creaturesFactory;

    public CreatureMapper(CreaturesFactory creaturesFactory) {
        this.creaturesFactory = creaturesFactory;
    }

    Creature map(AddCreatureToTournamentDto addCreatureToTournamentDto) {
        return creaturesFactory.createCreature(
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
