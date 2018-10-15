package com.example.Arena.controller;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
class CreateTournamentDto {

    @NotNull
    private Integer points;

    @NotNull
    private Integer capacity;
}
