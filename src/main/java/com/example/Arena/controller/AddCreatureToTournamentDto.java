package com.example.Arena.controller;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
class AddCreatureToTournamentDto {

    @NotNull
    private Integer lifePoints;

    @NotNull
    private Integer strength;

    private Integer dexterity;
    private Integer initiative;
    private Integer velocity;
    private Integer endurance;
    private Integer numberOfAttacks;
    private Integer numberOfDodges;
}
