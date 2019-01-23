package com.example.Arena.controller;

import com.example.Arena.creature.CreatureType;
import lombok.Value;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Value
class AddCreatureToTournamentDto {

    @NotNull(message = "life points cannot be null")
    @Min(value = 1, message = "lifePoints should not be less than 1")
    @Max(value = 10, message = "lifePoints should not be greater than 10")
    private Integer lifePoints;

    @NotNull(message = "strength cannot be null")
    @Min(value = 1, message = "strength should not be less than 1")
    @Max(value = 10, message = "strength should not be greater than 10")
    private Integer strength;

    @Min(value = 0, message = "dexterity should not be less than 0")
    @Max(value = 10, message = "dexterity should not be greater than 10")
    private Integer dexterity;

    @Min(value = 0, message = "initiative should not be less than 0")
    @Max(value = 10, message = "initiative should not be greater than 10")
    private Integer initiative;

    @Min(value = 0, message = "velocity should not be less than 0")
    @Max(value = 10, message = "velocity should not be greater than 10")
    private Integer velocity;

    @Min(value = 0, message = "endurance should not be less than 0")
    @Max(value = 10, message = "endurance should not be greater than 10")
    private Integer endurance;

    @Min(value = 1, message = "numberOfAttacks should not be less than 1")
    @Max(value = 10, message = "numberOfAttacks should not be greater than 10")
    private Integer numberOfAttacks;

    @Min(value = 0, message = "numberOfDodges should not be less than 0")
    @Max(value = 10, message = "numberOfDodges should not be greater than 10")
    private Integer numberOfDodges;

    @Enumerated(EnumType.STRING)
    private CreatureType type;
}
